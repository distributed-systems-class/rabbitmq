package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Receive {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        // Try to create a connection to server and a channel on localhost
        // -> Also create channel on this side so that we can instantiate both independently
        // -> This time without try-catch to prevent automatically closing the channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declare the queue from which we want to consume
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // Tell RabbitMQ to deliver message from the queue via callback (asynchronous)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }

}
