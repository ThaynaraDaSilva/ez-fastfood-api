package br.com.fiap.ez.fastfood.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTOTest {

	@Test
	public void testConstructorWithCustomerNameOnly() {
	  String customerName = "Jane Doe";

	  CreateOrderDTO createOrderDTO = new CreateOrderDTO();
	  createOrderDTO.setCustomerName(customerName); 

	  assertEquals(customerName, createOrderDTO.getCustomerName());
	  assertEquals("", createOrderDTO.getCustomerCpf());
	  assertNull(createOrderDTO.getOrderItems());
	}

    @Test
    public void testAllGettersAndSetters() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();

        String customerName = "Michael Smith";
        String customerCpf = "98765432100";
        List<OrderItemDTO> orderItems = new ArrayList<>();

        createOrderDTO.setCustomerName(customerName);
        createOrderDTO.setCustomerCpf(customerCpf);
        createOrderDTO.setOrderItems(orderItems);

        assertEquals(customerName, createOrderDTO.getCustomerName());
        assertEquals(customerCpf, createOrderDTO.getCustomerCpf());
        assertEquals(orderItems, createOrderDTO.getOrderItems());
    }

    @Test
    public void testNullOrEmptyCustomerCpf() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();

        createOrderDTO.setCustomerCpf(null);
        createOrderDTO.setCustomerCpf("");

        assertEquals("", createOrderDTO.getCustomerCpf());

        createOrderDTO.setCustomerCpf(" ");
        assertEquals(" ", createOrderDTO.getCustomerCpf());
    }

}