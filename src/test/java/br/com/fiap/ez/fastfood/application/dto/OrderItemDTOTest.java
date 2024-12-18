package br.com.fiap.ez.fastfood.application.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class OrderItemDTOTest {
	
	@Test
	public void testDefaultConstructor() {
	    OrderItemDTO orderItemDTO = new OrderItemDTO();

	    Assertions.assertNull(orderItemDTO.getProductId());
	    Assertions.assertNull(orderItemDTO.getQuantity());
	}

    @Test
    public void testConstructorAndGettersSetters() {
        Long productId = 10L;
        Integer quantity = 2;

        OrderItemDTO orderItemDTO = new OrderItemDTO(productId, quantity);

        Assertions.assertEquals(productId, orderItemDTO.getProductId());
        Assertions.assertEquals(quantity, orderItemDTO.getQuantity());

        // Test setters
        orderItemDTO.setProductId(20L);
        orderItemDTO.setQuantity(3);

        Assertions.assertEquals(20L, orderItemDTO.getProductId());
        Assertions.assertEquals(3, orderItemDTO.getQuantity());
    }

    @Test
    public void testNegativeQuantity() {
        OrderItemDTO orderItemDTO = new OrderItemDTO(10L, -1);
        Assertions.assertEquals(-1, orderItemDTO.getQuantity());
    }

    @Test
    public void testZeroQuantity() {
        OrderItemDTO orderItemDTO = new OrderItemDTO(10L, 0);
        Assertions.assertEquals(0, orderItemDTO.getQuantity());
    }
}