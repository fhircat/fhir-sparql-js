#syntax=docker/dockerfile:1.4

# Base node version
FROM node:20 as node_base

# The different stages of this Dockerfile are meant to be built into separate images
# https://docs.docker.com/develop/develop-images/multistage-build/#stop-at-a-specific-build-stage
# https://docs.docker.com/compose/compose-file/#target

RUN mkdir -p /home/node/app/node_modules && chown -R node:node /home/node/app

WORKDIR /home/node/app

COPY package.json package-lock.json* tsconfig.json ./

USER node

COPY --chown=node:node . .
# RUN mkdir -p /home/node/fhir-sparql-common/src/test/ && chown -R node:node /home/node/fhir-sparql-common/*
# COPY --chown=node:node ../fhir-sparql-common/src/test/resources ../fhir-sparql-common/src/test/resources

RUN npm ci
RUN npm run build
RUN npm run test

EXPOSE 8080