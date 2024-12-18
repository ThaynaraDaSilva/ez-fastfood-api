package br.com.fiap.ez.fastfood.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    private Customer customer;
    private List<OrderItem> orderItems;
    private Order order;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe", "john.doe@example.com", "123.456.789-00");

        Product product1 = new Product((long) 1, "Burger", "Delicious burger", 10.0, null);
        Product product2 = new Product((long) 2, "Fries", "Crispy fries", 5.0, null);

        OrderItem item1 = new OrderItem(order, product1, 2, null);
        OrderItem item2 = new OrderItem(order, product2, 1, null);

        orderItems = List.of(item1, item2);

        order = new Order(
                1L,
                "0001 John Doe",
                customer,
                ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")),
                null,
                null,
                OrderStatus.WAITING_PAYMENT,
                customer.getName(),
                orderItems
        );
    }

    @Test
    void testCalculateTotalPrice() {
        double expectedTotal = 2 * 10.0 + 1 * 5.0; 
        double actualTotal = Order.calculateTotalPrice(orderItems);

        assertThat(actualTotal).isEqualTo(expectedTotal);
    }

    @Test
    void testCalculateAndSetTotalPrice() {
        order.calculateAndSetTotalPrice();
        assertThat(order.getTotalPrice()).isEqualTo(25.0);
    }

    @Test
    void testCalculateOrderWaitedTimeWithCompletedTime() {
        ZonedDateTime completedTime = order.getOrderTime().plusHours(1).plusMinutes(30);
        String waitedTime = order.calculateOrderWaitedTime(order.getOrderTime(), completedTime);

        assertThat(waitedTime).isEqualTo("01h30");
    }

    @Test
    void testCalculateOrderWaitedTimeWithoutCompletedTime() {
        String waitedTime = order.calculateOrderWaitedTime(order.getOrderTime(), null);
        assertThat(waitedTime).isNotNull();
    }

    @Test
    void testGenerateOrderNumberSameDay() {
        Order lastOrder = new Order();
        lastOrder.setOrderNumber("0001 John Doe");
        lastOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

        String newOrderNumber = order.generateOrderNumber(lastOrder);
        assertThat(newOrderNumber).isEqualTo("0002 John Doe");
    }

    @Test
    void testGenerateOrderNumberNewDay() {
        Order lastOrder = new Order();
        lastOrder.setOrderNumber("0001 John Doe");
        lastOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).minusDays(1));

        String newOrderNumber = order.generateOrderNumber(lastOrder);
        assertThat(newOrderNumber).isEqualTo("0001 John Doe");
    }

    @Test
    void testGettersAndSetters() {
        order.setId(2L);
        assertThat(order.getId()).isEqualTo(2L);

        order.setOrderNumber("0003 Jane Doe");
        assertThat(order.getOrderNumber()).isEqualTo("0003 Jane Doe");

        order.setCustomerName("Jane Doe");
        assertThat(order.getCustomerName()).isEqualTo("Jane Doe");

        order.setStatus(OrderStatus.COMPLETED);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }
    
    @Test
    void testGetCustomer() {
        order.setCustomer(null);
        assertThat(order.getCustomer()).isNull();
    }

    @Test
    void testSetCustomer() {
        Customer newCustomer = new Customer("Jane Doe", "jane.doe@example.com", "987.654.321-00");
        order.setCustomer(newCustomer);
        assertThat(order.getCustomer()).isEqualTo(newCustomer);
    }

    @Test
    void testGetAndSetCompletedTime() {
        ZonedDateTime completedTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        order.setCompletedTime(completedTime);
        assertThat(order.getCompletedTime()).isEqualTo(completedTime);
    }

    @Test
    void testSetTotalPrice() {
        order.setTotalPrice(100.0);
        assertThat(order.getTotalPrice()).isEqualTo(100.0);
    }

    @Test
    void testGetAndSetOrderItems() {
        List<OrderItem> newOrderItems = List.of(
            new OrderItem(order, new Product(null, "Pizza", "Cheese Pizza", 20.0, null), 2, null)
        );
        order.setOrderItems(newOrderItems);
        assertThat(order.getOrderItems()).isEqualTo(newOrderItems);
    }

    @Test
    void testGenerateOrderNumberWhenLastOrderIsNull() {
        order.setCustomerName("John Doe");
        String orderNumber = order.generateOrderNumber(null);
        assertThat(orderNumber).isEqualTo("0001 John Doe");
    }

    @Test
    void testGenerateOrderNumberWithSameDayOrder() {
        order.setCustomerName("Jane Doe");

        Order lastOrder = new Order();
        lastOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        lastOrder.setOrderNumber("0001 Jane Doe");

        String orderNumber = order.generateOrderNumber(lastOrder);

        assertThat(orderNumber).isEqualTo("0002 Jane Doe");
    }

    @Test
    void testGenerateOrderNumberWithDifferentDayOrder() {
        order.setCustomerName("Alex Doe");

        Order lastOrder = new Order();
        lastOrder.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).minusDays(1));
        lastOrder.setOrderNumber("0010 Alex Doe");

        String orderNumber = order.generateOrderNumber(lastOrder);

        assertThat(orderNumber).isEqualTo("0001 Alex Doe");
    }


}