source ./test-functions.sh
echo 'RUN_ARGS="--server.port=8081 --server.servlet.context-path=/test"' > app.conf
launch_jar
await_app http://127.0.0.1:8081/test/
curl -s http://127.0.0.1:8081/test/
