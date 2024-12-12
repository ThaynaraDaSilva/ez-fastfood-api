package br.com.fiap.ez.fastfood.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCustomerDTOTest {

    @Test
    public void testConstructorWithAllFields() {
        String cpf = "12345678900";
        String name = "John Doe";
        String email = "johndoe@example.com";

        CreateCustomerDTO customerDTO = new CreateCustomerDTO(cpf, name, email);

        assertEquals(cpf, customerDTO.getCpf());
        assertEquals(name, customerDTO.getName());
        assertEquals(email, customerDTO.getEmail());
    }

    @Test
    public void testAllGettersAndSetters() {
    	String cpf = "98765432100";
        String name = "Jane Doe";
        String email = "janedoe@example.com";
        
        CreateCustomerDTO customerDTO = new CreateCustomerDTO(cpf, name, email);

        customerDTO.setCpf(cpf);
        customerDTO.setName(name);
        customerDTO.setEmail(email);

        assertEquals(cpf, customerDTO.getCpf());
        assertEquals(name, customerDTO.getName());
        assertEquals(email, customerDTO.getEmail());
    }

    @Test
    public void testNullOrEmptyValues() {
        CreateCustomerDTO customerDTO = new CreateCustomerDTO(null, "", " ");

        assertNull(customerDTO.getCpf());
        assertEquals("", customerDTO.getName());
        assertEquals(" ", customerDTO.getEmail());
    }
}