#syntax=docker/dockerfile:1.4

# Base node version
FROM node:20 as node_base

# The different stages of this Dockerfile are meant to be built into separate images
# https://docs.docker.com/develop/develop-images/multistage-build/#stop-at-a-specific-build-stage
# https://docs.docker.com/compose/compose-file/#target

RUN mkdir -p /home/node/app/node_modules && chown -R node:node /home/node/app

WORKDIR /home/node/app/fhir-sparql/

COPY ./package.json ./package-lock.json* ./tsconfig.json ./

USER root

COPY --chown=node:node . .

RUN npm ci
RUN npm run build
RUN npm run test

EXPOSE 8080

ENTRYPOINT ["./bin/canned-server", "-u", "http://0.0.0.0:8080/hapi/fhir/", "-r", "./fhir-sparql-test/src/test/resources/org/uu3/fhirServerResources/", "-d", "trace"]