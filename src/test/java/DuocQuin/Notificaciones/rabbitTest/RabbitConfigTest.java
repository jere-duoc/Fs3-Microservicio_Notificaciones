package DuocQuin.Notificaciones.rabbitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Queue;
import DuocQuin.Notificaciones.rabbitmq.RabbitConfig;

class RabbitConfigTest {

    RabbitConfig config = new RabbitConfig();

    @Test
    void colaHorariosDebeCrearse() {
        Queue queue = config.colaHorarios();

        assertEquals("cola_horarios", queue.getName());
    }

    @Test
    void colaPagosDebeCrearse() {
        Queue queue = config.colaPagos();

        assertEquals("cola_pagos", queue.getName());
    }

    @Test
    void exchangeDebeCrearse() {
        DirectExchange exchange = config.exchange();

        assertEquals("duocquin.exchange", exchange.getName());
    }

}
