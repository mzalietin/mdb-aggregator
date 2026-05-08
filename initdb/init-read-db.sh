#!/usr/bin/env bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER query_service_user WITH PASSWORD 'jw8s0F4';
	CREATE DATABASE read_db OWNER query_service_user;
EOSQL
