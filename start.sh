#!/usr/bin/env bash
set -euo pipefail

cd backend
exec mvn spring-boot:run
