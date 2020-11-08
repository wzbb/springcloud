package com.rb.util;

import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.messaging.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020-11-5.
 */
@Component
public class RabbitReceive {


//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "queue-2", durable = "${spring.rabbitmq.listener.order.exchange.durable}"),
//            exchange = @Exchange(name = "${spring.rabbitmq.listener.order.exchange.name}",
//                    durable = "${spring.rabbitmq.listener.order.exchange.durable}",
//                    type = "${spring.rabbitmq.listener.order.exchange.type}",
//                    ignoreDeclarationExceptions = "true"),
//            key = "${spring.rabbitmq.listener.order.exchange.key}"
//    )
//    )

    private AtomicInteger num = new AtomicInteger(0);


    /**
     * 	组合使用监听
     * 	@RabbitListener @QueueBinding @Queue @Exchange
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1", durable = "true"),
            exchange = @Exchange(name = "exchange-1",
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    )
    )

        public void onMessage(Message message, Channel channel) throws Exception {
            //	1. 收到消息以后进行业务端消费处理
            //System.err.println("onMessage1-----------------------");
            //System.err.println("消费消息onMessage1:" + message.getPayload());

            if( num.incrementAndGet() <= 100){
                System.err.println("消费消息onMessage1:" + message.getPayload() + "成功消费:+++++" + num);
            }else{
                System.out.println("消费消息onMessage1:" + message.getPayload() + "库存不足消费失败");
            }

            //  2. 处理成功之后 获取deliveryTag 并进行手工的ACK操作, 因为我们配置文件里配置的是 手工签收
            //	spring.rabbitmq.listener.simple.acknowledge-mode=manual
            Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag, false);
        }

    @RabbitListener(bindings = @QueueBinding(
                value = @Queue(value = "queue-2", durable = "true"),
                exchange = @Exchange(name = "order-exchange",
                        durable = "true",
                        type = "topic",
                        ignoreDeclarationExceptions = "true"),
                key = "order.*"
        )
        )
    public void onMessage2(Message message, Channel channel) throws Exception {
        //	1. 收到消息以后进行业务端消费处理
        System.err.println("onMessage2-----------------------");
        System.err.println("消费消息onMessage2:" + message.getPayload());

        //  2. 处理成功之后 获取deliveryTag 并进行手工的ACK操作, 因为我们配置文件里配置的是 手工签收
        //	spring.rabbitmq.listener.simple.acknowledge-mode=manual
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }


}
