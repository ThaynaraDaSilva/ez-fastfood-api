package br.com.fiap.ez.fastfood.application.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
public class PaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PaymentUseCase paymentUseCase;

    @Test
    public void testRegisterPayment_Success() {
        Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(Double.valueOf(100));
        
        
        order.setCustomer(new Customer("John Doe", "test@test.com", "12345678900"));

        paymentUseCase.registerPayment(order);

        verify(paymentRepository).registerPayment(any(Payment.class));
    }

//    @Test
//    void testRegisterPaymentStatus_Success() {
//        Payment payment = new Payment();
//        payment.setId(1L);
//        payment.setPaymentStatus(PaymentStatus.PENDING);
//        payment.setOrder(new Order(1L, null, null, null, null, null, null, null, null));
//
//        PaymentDTO paymentDTO = new PaymentDTO();
//        paymentDTO.setPaymentId(1L);
//        paymentDTO.setPaymentStatus("OK");
//
//        when(paymentRepository.findPaymentById(1L)).thenReturn(payment);
//        when(orderRepository.findOrderById(1L)).thenReturn(payment.getOrder());
//
//        paymentUseCase.registerPaymentStatus(paymentDTO);
//
//        verify(paymentRepository).registerPaymentStatus(payment);
//        verify(orderRepository).save(payment.getOrder());
//
//        assertEquals("OK", payment.getPaymentStatus());
//    }

    @Test
    void testRegisterPaymentStatus_NotFound() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(1L);
        paymentDTO.setPaymentStatus("OK");

        when(paymentRepository.findPaymentById(1L)).thenReturn(null);

        BusinessException exception = assertThrows(BusinessException.class, 
            () -> paymentUseCase.registerPaymentStatus(paymentDTO));
            
        assertEquals("Não existe pagamento com este id", exception.getMessage());
    }

//    @Test
//    public void testRegisterPaymentStatus_AlreadyProcessed() {
//        Payment payment = new Payment();
//        payment.setId(1L);
//        payment.setPaymentStatus(PaymentStatus.OK);
//
//        PaymentDTO paymentDTO = new PaymentDTO();
//        paymentDTO.setPaymentId(1L);
//        paymentDTO.setPaymentStatus("OK");
//
//        when(paymentRepository.findPaymentById(1L)).thenReturn(payment);
//
//        BusinessException exception = assertThrows(BusinessException.class, 
//            () -> paymentUseCase.registerPaymentStatus(paymentDTO));
//
//        assertEquals("Este pagamento já foi confirmado ou recusado.", exception.getMessage());
//    }

    @Test
    public void testCheckPaymentStatus_Success() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setPaymentStatus(PaymentStatus.OK);

        when(paymentRepository.findPaymentById(1L)).thenReturn(payment);

        PaymentDTO result = paymentUseCase.checkPaymentStatus(1L);

        assertNotNull(result);
        assertEquals(payment.getId(), result.getPaymentId());
    }

    @Test
    public void testCheckPaymentStatus_NotFound() {
        when(paymentRepository.findPaymentById(1L)).thenReturn(null);

        assertThrows(BusinessException.class, () -> paymentUseCase.checkPaymentStatus(1L));
    }
}