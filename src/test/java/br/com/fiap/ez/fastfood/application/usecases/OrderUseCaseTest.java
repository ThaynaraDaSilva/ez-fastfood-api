package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderItemDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.*;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderUseCaseTest {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private PaymentUseCase paymentUseCase;
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        customerRepository = mock(CustomerRepository.class);
        paymentUseCase = mock(PaymentUseCase.class);
        orderUseCase = new OrderUseCase(orderRepository, productRepository, customerRepository, paymentUseCase);
    }

    @Test
    void testRegisterOrder_Success() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setCustomerCpf("12345678900");
        createOrderDTO.setCustomerName("John Doe");
        createOrderDTO.setOrderItems(List.of(new OrderItemDTO(1L, 2)));

        Product product = new Product();
        product.setId(1L);
        product.setName("Burger");
        product.setDescription("Delicious Burger");
        product.setPrice(Double.valueOf(10.00));
        
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCpf("12345678900");
        customer.setName("John Doe");


        when(customerRepository.findByCpf("12345678900")).thenReturn(customer);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.findLastOrder()).thenReturn(null);
        when(orderRepository.save(Mockito.any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderResponseDTO response = orderUseCase.registerOrder(createOrderDTO);

        assertNotNull(response);
        assertEquals("John Doe", response.getCustomerName());
        verify(paymentUseCase).registerPayment(any(Order.class));
    }

    @Test
    void testRegisterOrder_ProductNotFound() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setCustomerCpf("12345678900");
        createOrderDTO.setCustomerName("John Doe");
        createOrderDTO.setOrderItems(List.of(new OrderItemDTO(1L, 2)));

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> orderUseCase.registerOrder(createOrderDTO));
    }

//    @Test
//    void testUpdateOrderStatus_Success() {
//        Order order = new Order();
//        order.setId(1L);
//        order.setStatus(OrderStatus.RECEIVED);
//
//        when(orderRepository.findOrderById(1L)).thenReturn(order);
//        when(orderRepository.save(order)).thenReturn(order);
//
//        OrderResponseDTO response = orderUseCase.updateOrderStatus(1L);
//
//        assertEquals(OrderStatus.IN_PREPARATION, response.getOrderStatus());
//    }

    @Test
    void testUpdateOrderStatus_WaitingPayment() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        when(orderRepository.findOrderById(1L)).thenReturn(order);

        assertThrows(BusinessException.class, () -> orderUseCase.updateOrderStatus(1L));
    }

    @Test
    void testListAllOrders_Empty() {
        when(orderRepository.findAll()).thenReturn(List.of());

        assertThrows(BusinessException.class, () -> orderUseCase.listAllOrders());
    }

//    @Test
//    void testListAllOrders_Success() {
//        Order order = new Order();
//        order.setId(1L);
//
//        when(orderRepository.findAll()).thenReturn(List.of(order));
//
//        List<OrderResponseDTO> orders = orderUseCase.listAllOrders();
//        assertEquals(1, orders.size());
//    }
}
