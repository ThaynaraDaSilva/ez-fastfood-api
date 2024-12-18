package br.com.fiap.ez.fastfood.domain.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OrderStatusTest {

    @Test
    void testEnumValues() {
        assertAll(
            () -> assertEquals(OrderStatus.WAITING_PAYMENT, OrderStatus.valueOf("WAITING_PAYMENT")),
            () -> assertEquals(OrderStatus.CANCELLED, OrderStatus.valueOf("CANCELLED")),
            () -> assertEquals(OrderStatus.RECEIVED, OrderStatus.valueOf("RECEIVED")),
            () -> assertEquals(OrderStatus.IN_PREPARATION, OrderStatus.valueOf("IN_PREPARATION")),
            () -> assertEquals(OrderStatus.READY, OrderStatus.valueOf("READY")),
            () -> assertEquals(OrderStatus.COMPLETED, OrderStatus.valueOf("COMPLETED"))
        );
    }

    @Test
    void testEnumCount() {
        assertEquals(6, OrderStatus.values().length, "OrderStatus should have 6 constants");
    }

    @Test
    void testEnumToString() {
        assertEquals("WAITING_PAYMENT", OrderStatus.WAITING_PAYMENT.toString());
        assertEquals("CANCELLED", OrderStatus.CANCELLED.toString());
        assertEquals("RECEIVED", OrderStatus.RECEIVED.toString());
        assertEquals("IN_PREPARATION", OrderStatus.IN_PREPARATION.toString());
        assertEquals("READY", OrderStatus.READY.toString());
        assertEquals("COMPLETED", OrderStatus.COMPLETED.toString());
    }
}
