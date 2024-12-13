//package com.example.tianshu.config;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//public class RabbitBindingConfig {
//    @Bean
//    public Queue queue() {
//        return new Queue("queue1");
//    }
//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange("exchange1");
//    }
//
//
//    @Bean
//    public Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("routingKey");
//    }
//}
