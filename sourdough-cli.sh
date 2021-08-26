#!/usr/bin/env bash
# Runs the cortex cli
./gradlew --quiet ":cli:installDist" && "./cli/build/install/cli/bin/cli" "$@"
