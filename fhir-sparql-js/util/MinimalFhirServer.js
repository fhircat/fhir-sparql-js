/**
 * This is a minimal FHIR server used to field the subset of FHIR API calls that FhirSparql invokes.
 * This is NOT a real FHIR server.
 */

const Fs = require('fs');
const Path = require('path');
const Http = require('http');
const Util = require('util');

class EmptyLog {
  
}

class MinimalFhirServer {
  constructor (host, port, fhirRoot, cannedRespDir, resourceIndex, log) {
    this.host = host;
    this.port = port;
    this.fhirRoot = fhirRoot;
    this.cannedRespDir = cannedRespDir;
    this.resourceIndex = resourceIndex;
    log.trace(`serving FHIR with ${Util.inspect(this)}`);
    this.log = log;
    this._theServer = null;
  }

  start (callback) {
    const requestListener = (req, res) => {
      const url = new URL(`http://${this.host}:${this.port}${req.url}`);
      const body = this.handleFhirApiReq(url);
      res.writeHead(200);
      res.end(body);
    };

    this._theServer = Http.createServer(requestListener);
    return new Promise((resolve, reject) => {
      this._theServer.listen(this.port, this.host, resolve);
    });
  }

  stop () {
    this._theServer.close();
    this._theServer = null;
  }

  handleFhirApiReq (url) {
    this.log.info(`handling request for ${url}`);
    if (!url.pathname.startsWith(this.fhirRoot))
      throw Error(`expected FHIR query for ${url.pathname} to start with ${this.fhirRoot}`);

    const resourcePath = url.pathname.substring(this.fhirRoot.length);
    const [resourceType, resourceName] = resourcePath.split('/');
    this.log.trace(`resourceType: ${resourceType}, resourceName: ${resourceName}`);
    // resourceName will be undefined for e.g. Observation?code=1234

    if (resourceName) {

      // this.fhirRoot '/Observation/123'
      const filename = Path.join(this.cannedRespDir, resourceType);
      try {
        console.log(`GET <${url.href}>`);
        const body = Fs.readFileSync(filename, 'utf-8');
        return body;
      } catch (e) {
        e.message += ' on ' + filename;
        throw e;
      }
    } else {

      // this.fhirRoot '/Observation' ?search...
      const resourceBase = new URL(resourceType + '/', url);
      const resourceDir = Path.join(this.cannedRespDir, resourceType);
      let candidates = Fs.readdirSync(resourceDir).map(fn => fn.substring(0, fn.lastIndexOf('.')) || fn);
      const index = this.resourceIndex[resourceType];
      for (const [attr, value] of Array.from(url.searchParams.entries())) {
        const hits = ((this.resourceIndex[resourceType] || [])[attr] || [])[value];
        if (hits !== undefined) {
          for (let i = 0; i < candidates.length; ++i) {
            if (hits.indexOf(candidates[i]) === -1) {
              candidates.splice(i--, 1);
            }
          }
        }
      }

      // write bundle
      const resp = {
        type: 'Bundle',
        entry: candidates.map(filename => createEntry(resourceBase, resourceDir, filename))
      };
      return JSON.stringify(resp, null, 2);
    }
  };

}

function createEntry (resourceBase, resourceDir, filename) {
  const fullUrl = new URL(filename, resourceBase).href;
  const resource = JSON.parse(Fs.readFileSync(Path.join(resourceDir, filename + '.json'), 'utf-8'));
  return { fullUrl, resource }
}

module.exports = {MinimalFhirServer};
