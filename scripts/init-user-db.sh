#!/usr/bin/env bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE movie_data;
	CREATE DATABASE movie_review_data;
	CREATE DATABASE app_user_data;
	GRANT ALL PRIVILEGES ON DATABASE movie_data TO dev_user;
	GRANT ALL PRIVILEGES ON DATABASE movie_review_data TO dev_user;
	GRANT ALL PRIVILEGES ON DATABASE app_user_data TO dev_user;
EOSQL
