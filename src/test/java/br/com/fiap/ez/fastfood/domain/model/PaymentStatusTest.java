package br.com.fiap.ez.fastfood.domain.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PaymentStatusTest {

    @Test
    void testEnumValues() {
        assertAll(
            () -> assertEquals(PaymentStatus.PENDING, PaymentStatus.valueOf("PENDING")),
            () -> assertEquals(PaymentStatus.CANCELLED, PaymentStatus.valueOf("CANCELLED")),
            () -> assertEquals(PaymentStatus.OK, PaymentStatus.valueOf("OK"))
        );
    }

    @Test
    void testEnumCount() {
        assertEquals(3, PaymentStatus.values().length, "PaymentStatus should have 3 constants");
    }

    @Test
    void testEnumToString() {
        assertEquals("PENDING", PaymentStatus.PENDING.toString());
        assertEquals("CANCELLED", PaymentStatus.CANCELLED.toString());
        assertEquals("OK", PaymentStatus.OK.toString());
    }
}
