package br.com.fiap.ez.fastfood.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentDTOTest {

    @Test
    public void testDefaultConstructor() {
        PaymentDTO paymentDTO = new PaymentDTO();

        assertNull(paymentDTO.getPaymentId());
        assertNull(paymentDTO.getPaymentStatus());
    }

    @Test
    public void testAllGettersAndSetters() {
        PaymentDTO paymentDTO = new PaymentDTO();

        Long paymentId = 123L;
        String paymentStatus = "APPROVED";

        paymentDTO.setPaymentId(paymentId);
        paymentDTO.setPaymentStatus(paymentStatus);

        assertEquals(paymentId, paymentDTO.getPaymentId());
        assertEquals(paymentStatus, paymentDTO.getPaymentStatus());
    }
}