FROM jboss/apiman-wildfly
MAINTAINER Arun Gupta <arungupta@redhat.com>

# Create organization
RUN curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "everest", "description": "Microservice Everest Organization"}' http://192.168.99.103:8082/apiman/organizations/

# Create user service
curl --user admin:admin123! -H "Content-Type: application/json" -H "Accept: application/json" --data '{"name": "user", "description": "user service", "initialVersion": "1.0.0-SNAPSHOT"}' http://192.168.99.103:8082/apiman/organizations/everest/services

# Add user service URI
