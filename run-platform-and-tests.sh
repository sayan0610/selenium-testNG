#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR/test-platform" || exit 1
npm start &
PLATFORM_PID=$!

# Wait for the server to start (adjust as needed)
echo "Waiting for test-platform to start..."
sleep 8

# Go back to project root and run tests
cd "$SCRIPT_DIR"

# Run Selenium TestNG tests
mvn clean test

# Kill the test-platform server
kill $PLATFORM_PID

echo "Test-platform server stopped. Tests complete."
