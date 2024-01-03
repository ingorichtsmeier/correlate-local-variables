# Camunda 7 Example about message correlation with local variables

This is a playground to test message correlation with local variable propagation.

It consists of two parts:

1. An example process engine pre-configured
2. A REST client to send multiple message correlation requests in parallel or sequentially.

## Requirements

1. A running PostgresQL database. It can be started from the docker-compose file in the process engine directory.

## Playing with the example

Starting the Spring Boot application with the process engine will start a single process instance containing a user task with a non-interrupting message boundary event.

The Rest client sends on startup requests to correlate the message.

In the first try, 3 requests in parallel with the help of a Flux containing 3 elements.

Afterwards, 5 messages will be correlated sequentially.

Use the Cockpit to inspect the process instance.

Use Tasklist to clean up the tasks and process instance.

## Restrictions

The message is correlated without a correlation key, only a single process instance is supported.

## Researches

- https://reflectoring.io/getting-started-with-spring-webflux/
- https://reflectoring.io/spring-webclient/
- https://www.baeldung.com/spring-5-webclient
- https://www.baeldung.com/spring-webclient-simultaneous-calls
