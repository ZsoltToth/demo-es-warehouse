#!/usr/bin/env bash

DOCKER_ARGS=()
DOCKER_ARGS+=(--name postgres)
DOCKER_ARGS+=(--rm)
DOCKER_ARGS+=(--publish 5432:5432)
DOCKER_ARGS+=(--env POSTGRES_USER=postgres)
DOCKER_ARGS+=(--env POSTGRES_PASSWORD=secret)
DOCKER_ARGS+=(--env POSTGRES_DB=warehouse)
DOCKER_ARGS+=(--detach)
DOCKER_ARGS+=()

docker run "${DOCKER_ARGS[@]}" postgres:16.2
