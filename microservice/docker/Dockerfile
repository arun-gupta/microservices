FROM jboss/apiman-wildfly
MAINTAINER Arun Gupta <arungupta@redhat.com>

# Create organization
RUN curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "everest", "description": "Microservice Everest Organization"}' http://192.168.99.103:8082/apiman/organizations/

# Create catalog service
RUN curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "catalog", "description": "catalog service", "initialVersion": "1.0.0-SNAPSHOT"}' http://192.168.99.103:8082/apiman/organizations/everest/services

# Add catalog service URI
RUN curl -v --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" -X PUT --data '{ "endpointType": "rest", "publicService": true, "endpoint": "http://localhost:8080/catalog/resources/catalog"}' http://192.168.99.103:8082/apiman/organizations/everest/services/catalog/versions/1.0.0-SNAPSHOT

# Create user service
RUN curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "user", "description": "user service", "initialVersion": "1.0.0-SNAPSHOT"}' http://192.168.99.103:8082/apiman/organizations/everest/services

# Add user service URI
RUN curl -v --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" -X PUT --data '{ "endpointType": "rest", "publicService": true, "endpoint": "http://localhost:8080/user/resources/user"}' http://192.168.99.103:8082/apiman/organizations/everest/services/user/versions/1.0.0-SNAPSHOT

# Create order service
RUN curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "order", "description": "order service", "initialVersion": "1.0.0-SNAPSHOT"}' http://192.168.99.103:8082/apiman/organizations/everest/services

# Add order service URI
RUN curl -v --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" -X PUT --data '{ "endpointType": "rest", "publicService": true, "endpoint": "http://localhost:8080/order/resources/order"}' http://192.168.99.103:8082/apiman/organizations/everest/services/order/versions/1.0.0-SNAPSHOT

# Get all services
# curl --user admin:admin123! -H "Accept: application/json" http://192.168.99.103:8082/apiman/organizations/everest/services

