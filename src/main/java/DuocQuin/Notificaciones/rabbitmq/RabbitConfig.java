package DuocQuin.Notificaciones.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RabbitConfig {

    //Buzon
    @Bean
    public Queue colaHorarios() {
        return new Queue("cola_horarios", true);
    }

    @Bean
    public Queue colaPagos(){
        return new Queue("cola_pagos", true);
    }



    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("duocquin.exchange");
    }

    //binding es la relacion entre el exchange y una cola
    @Bean
    public Binding horarioBinding (
        @Qualifier("colaHorarios") Queue colaHorarios,
         DirectExchange exchange){

    
        return BindingBuilder
            .bind(colaHorarios)
            .to(exchange)
            .with("horario.creado");
    }

    @Bean
    public Binding pagoBinding(
        @Qualifier("colaPagos") Queue colaPagos,
         DirectExchange exchange){

        return BindingBuilder
            .bind(colaPagos)
            .to(exchange)
            .with("pago.generado");
    }
    

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}