#syntax=docker/dockerfile:1.4

# Base node version
FROM node:20 as node_base

# The different stages of this Dockerfile are meant to be built into separate images
# https://docs.docker.com/develop/develop-images/multistage-build/#stop-at-a-specific-build-stage
# https://docs.docker.com/compose/compose-file/#target

RUN mkdir -p /home/node/app/node_modules && chown -R node:node /home/node/app

COPY fhir-sparql-common /home/node/app/fhir-sparql-common

WORKDIR /home/node/app/fhir-sparql-js/

COPY fhir-sparql-js/package.json fhir-sparql-js/package-lock.json* fhir-sparql-js/tsconfig.json ./fhir-sparql-js/

USER root

COPY --chown=node:node fhir-sparql-js .

RUN npm ci
RUN npm run build
RUN npm run test

#RUN ./bin/canned-server -u http://localhost:8080/hapi/fhir/ \
#  -r ../fhir-sparql-common/src/test/resources/org/uu3/fhirServerResources/ \
#  -d trace | ./node_modules/.bin/bunyan

EXPOSE 8080

ENTRYPOINT ["./bin/canned-server", "-u", "http://0.0.0.0:8080/hapi/fhir/", "-r", "../fhir-sparql-common/src/test/resources/org/uu3/fhirServerResources/", "-d", "trace"]