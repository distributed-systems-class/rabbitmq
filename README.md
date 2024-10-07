# Simple RabbitMQ example for a message oriented middleware (mom)

Build the maven project. Afterwards:
1. Run a RabbitMQ server - handles the message management
   ```
   docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
   ```
2. Run the sender - sends a message to the queue via RabbitMQ server
3. Run the receiver - receives the message from the queue via RabbitMQ server