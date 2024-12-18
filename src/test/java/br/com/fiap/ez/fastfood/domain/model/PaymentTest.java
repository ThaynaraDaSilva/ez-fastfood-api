package br.com.fiap.ez.fastfood.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {

    private Payment payment;
    private Order order;
    private Customer customer;

    @BeforeEach
    void setUp() {
        order = new Order();
        customer = new Customer("John Doe", "john.doe@example.com", "123.456.789-00");
        payment = new Payment(1L, order, customer, ZonedDateTime.now(), 100.0, PaymentStatus.PENDING);
    }

    @Test
    void testNoArgsConstructor() {
        Payment newPayment = new Payment();
        assertNull(newPayment.getId());
        assertNull(newPayment.getOrder());
        assertNull(newPayment.getCustomer());
        assertNull(newPayment.getPaymentDate());
        assertNull(newPayment.getPaymentPrice());
        assertNull(newPayment.getPaymentStatus());
    }

    @Test
    void testAllArgsConstructor() {
        assertEquals(1L, payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals(customer, payment.getCustomer());
        assertNotNull(payment.getPaymentDate());
        assertEquals(100.0, payment.getPaymentPrice());
        assertEquals(PaymentStatus.PENDING, payment.getPaymentStatus());
    }

    @Test
    void testSettersAndGetters() {
        ZonedDateTime newPaymentDate = ZonedDateTime.now().plusDays(1);

        payment.setId(2L);
        payment.setOrder(null);
        payment.setCustomer(null);
        payment.setPaymentDate(newPaymentDate);
        payment.setPaymentPrice(150.0);
        payment.setPaymentStatus(PaymentStatus.OK);

        assertEquals(2L, payment.getId());
        assertNull(payment.getOrder());
        assertNull(payment.getCustomer());
        assertEquals(newPaymentDate, payment.getPaymentDate());
        assertEquals(150.0, payment.getPaymentPrice());
        assertEquals(PaymentStatus.OK, payment.getPaymentStatus());
    }
}
