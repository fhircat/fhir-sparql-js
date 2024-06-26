#syntax=docker/dockerfile:1.4

# Base node version
FROM node:20 as node_base

# The different stages of this Dockerfile are meant to be built into separate images
# https://docs.docker.com/develop/develop-images/multistage-build/#stop-at-a-specific-build-stage
# https://docs.docker.com/compose/compose-file/#target

RUN mkdir -p /home/node/app/node_modules && chown -R node:node /home/node/app

WORKDIR /home/node/app

COPY fhir-sparql-js/package.json package-lock.json* tsconfig.json ./
COPY fhir-sparql-common /home/node

USER node

COPY --chown=node:node fhir-sparql-js .
# RUN mkdir -p /home/node/fhir-sparql-common/src/test/ && chown -R node:node /home/node/fhir-sparql-common/*
# COPY --chown=node:node ../fhir-sparql-common/src/test/resources ../fhir-sparql-common/src/test/resources

RUN npm ci
RUN npm run build
RUN npm run test

RUN ./bin/canned-server -u http://localhost:8080/hapi/fhir/ \
  -r ../fhir-sparql-common/src/test/resources/org/uu3/fhirServerResources/ \
  -d trace | ./node_modules/.bin/bunyan

EXPOSE 8080