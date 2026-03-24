#!/usr/bin/env bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE movie_data;
	CREATE DATABASE movie_review_data;
	CREATE DATABASE app_user_data;
	GRANT ALL PRIVILEGES ON DATABASE movie_data TO mdb_service_user;
	GRANT ALL PRIVILEGES ON DATABASE movie_review_data TO mdb_service_user;
	GRANT ALL PRIVILEGES ON DATABASE app_user_data TO mdb_service_user;
	CREATE USER query_service_user WITH PASSWORD 'jw8s0F4';
	CREATE DATABASE read_db;
	GRANT ALL PRIVILEGES ON DATABASE read_db TO query_service_user;

EOSQL
