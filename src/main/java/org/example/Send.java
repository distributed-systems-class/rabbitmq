package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class Send
{
    // create a queue
    private final static String QUEUE_NAME = "hello";

    public static void main( String[] args ) throws Exception
    {
        // Create a connection factory with host and port
        // -> manages socket connection for us
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        // Try to create a connection to server and a channel on localhost
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

                // Create a queue on the channel
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                // Send text message hello world
                String message = "Hello World!";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
