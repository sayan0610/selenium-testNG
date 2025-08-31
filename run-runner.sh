#!/bin/bash
# Run tests and generate Allure report using CLI

# Start test-platform server
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR/sample-app" || exit 1
npm i && npm start &
PLATFORM_PID=$!

# Wait for the server to start
echo "Waiting for test-platform to start..."
sleep 8

# Go back to project root and run tests
cd "$SCRIPT_DIR"

mvn clean test

# Generate Allure report using CLI
if command -v allure >/dev/null 2>&1; then
  allure generate ./allure-results/* --clean -o ./allure-report
else
  echo "Allure CLI not found. Install with: brew install allure"
fi

# Kill the test-platform server
kill $PLATFORM_PID

echo "Test-platform server stopped. Tests complete."
