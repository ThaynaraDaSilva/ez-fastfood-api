package br.com.fiap.ez.fastfood.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderItemTest {

    private OrderItem orderItem;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double price;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        product = mock(Product.class);
        quantity = 2;
        price = 5.50;

        orderItem = new OrderItem(order, product, quantity, price);
    }

    @Test
    void testOrderItemConstructor() {
        assertEquals(order, orderItem.getOrder());
        assertEquals(product, orderItem.getProduct());
        assertEquals(quantity, orderItem.getQuantity());
        assertEquals(price, orderItem.getPrice());
    }

    @Test
    void testGetTotalPrice() {
        Double totalPrice = orderItem.getTotalPrice();

        assertEquals(11.00, totalPrice);
    }

    @Test
    void testSettersAndGetters() {
        Order newOrder = mock(Order.class);
        Product newProduct = mock(Product.class);
        Integer newQuantity = 3;
        Double newPrice = 7.99;

        orderItem.setOrder(newOrder);
        orderItem.setProduct(newProduct);
        orderItem.setQuantity(newQuantity);
        orderItem.setPrice(newPrice);

        assertEquals(newOrder, orderItem.getOrder());
        assertEquals(newProduct, orderItem.getProduct());
        assertEquals(newQuantity, orderItem.getQuantity());
        assertEquals(newPrice, orderItem.getPrice());
    }

    @Test
    void testGetTotalPriceWithDifferentValues() {
        orderItem.setQuantity(5);
        orderItem.setPrice(12.99);

        Double totalPrice = orderItem.getTotalPrice();

        assertEquals(64.95, totalPrice);
    }

    @Test
    void testGetId() {
        Long id = 1L;
        orderItem.setId(id);

        Long returnedId = orderItem.getId();

        assertEquals(id, returnedId);
    }

    @Test
    void testSetId() {
        Long id = 1L;

        orderItem.setId(id);

        assertEquals(id, orderItem.getId());
    }

    @Test
    void testDefaultConstructor() {
        OrderItem defaultOrderItem = new OrderItem();

        assertNull(defaultOrderItem.getId());
        assertNull(defaultOrderItem.getOrder());
        assertNull(defaultOrderItem.getProduct());
        assertNull(defaultOrderItem.getQuantity());
        assertNull(defaultOrderItem.getPrice());
    }
}
