package com.rb.util;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * Created by admin on 2020-11-5.
 */
@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String s) {
            System.err.println("消息ACK结果:" + ack + ", correlationData: " + correlationData.getId());
        }
    };

    public void send(Object message, Map<String, Object> properties) throws Exception{
        MessageHeaders mhs = new MessageHeaders(properties);
        Message<?> msg = MessageBuilder.createMessage(message, mhs);
        rabbitTemplate.setConfirmCallback(confirmCallback);

        // 	指定业务唯一的iD
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        MessagePostProcessor mpp = new MessagePostProcessor() {

            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message)
                    throws AmqpException {
                System.err.println("---> post to do: " + message);
                return message;
            }
        };

        rabbitTemplate.convertAndSend("exchange-1", "springboot.rabbit", msg, mpp, correlationData);
        rabbitTemplate.convertAndSend("order-exchange", "order.rabbit", msg, mpp, correlationData);
    }
}
