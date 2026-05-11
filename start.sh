#!/usr/bin/env bash
set -euo pipefail

cd backend
exec ./mvnw spring-boot:run
