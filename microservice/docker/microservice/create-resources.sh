JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh

function wait_for_server() {
  until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
    sleep 1
  done
}

echo "=> Starting WildFly server"
$JBOSS_HOME/bin/standalone.sh -b 0.0.0.0 -c standalone-apiman.xml &

echo "=> Waiting for the server to boot"
wait_for_server

HOST=localhost
PORT=8080

# Create organization
echo "Creating organization ..."
curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "everest", "description": "Microservice Everest Organization"}' http://$HOST:$PORT/apiman/organizations/

# Create catalog service
echo "Creating catalog service ..."
curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "catalog", "description": "catalog service", "initialVersion": "1.0.0-SNAPSHOT"}' http://$HOST:$PORT/apiman/organizations/everest/services

# Add catalog service URI
echo "Adding catalog service URI ..."
curl -v --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" -X PUT --data '{ "endpointType": "rest", "publicService": true, "endpoint": "http://localhost:8080/catalog/resources/catalog"}' http://$HOST:$PORT/apiman/organizations/everest/services/catalog/versions/1.0.0-SNAPSHOT

# Create user service
echo "Creating user service ..."
curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "user", "description": "user service", "initialVersion": "1.0.0-SNAPSHOT"}' http://$HOST:$PORT/apiman/organizations/everest/services

# Add user service URI
echo "Adding user service URI ..."
curl -v --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" -X PUT --data '{ "endpointType": "rest", "publicService": true, "endpoint": "http://localhost:8080/user/resources/user"}' http://$HOST:$PORT/apiman/organizations/everest/services/user/versions/1.0.0-SNAPSHOT

# Create order service
echo "Creating order service ..."
curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "order", "description": "order service", "initialVersion": "1.0.0-SNAPSHOT"}' http://$HOST:$PORT/apiman/organizations/everest/services

# Add order service URI
echo "Adding order service URI ..."
curl -v --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" -X PUT --data '{ "endpointType": "rest", "publicService": true, "endpoint": "http://localhost:8080/order/resources/order"}' http://$HOST:$PORT/apiman/organizations/everest/services/order/versions/1.0.0-SNAPSHOT

# Get all services
curl --user admin:admin123! -H "Accept: application/json" http://$HOST:$PORT/apiman/organizations/everest/services

