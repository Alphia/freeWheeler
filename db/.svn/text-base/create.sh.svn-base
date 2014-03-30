#!/bin/bash

function createdb() {
  DATABASE=$1
  PGPASSWORD=postgres psql -U postgres -h localhost -c "drop database ${DATABASE}"
  PGPASSWORD=postgres psql -U postgres -h localhost -c "create database ${DATABASE}"
  PGPASSWORD=postgres psql -U postgres -h localhost -d ${DATABASE} < db/baseline.sql
  PGPASSWORD=postgres psql -U postgres -h localhost -d ${DATABASE} < db/backup.sql
}

createdb "trailblazers"

