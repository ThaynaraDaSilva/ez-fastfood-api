package br.com.fiap.ez.fastfood.application.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class ProductDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    @Test
    public void testEmptyConstructor() {
        ProductDTO productDTO = new ProductDTO();

        assertNull(productDTO.getName());
        assertNull(productDTO.getDescription());
        assertNull(productDTO.getPrice());
        assertNull(productDTO.getCategoryId());
    }

    @Test
    public void testAllGettersAndSetters() {
        String name = "Pizza Pepperoni";
        String description = "Delicious pizza with pepperoni topping";
        Long categoryId = 1L;
        Double price = 20.50;

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setCategoryId(categoryId);

        assertEquals(name, productDTO.getName());
        assertEquals(description, productDTO.getDescription());
        assertEquals(price, productDTO.getPrice());
        assertEquals(categoryId, productDTO.getCategoryId());
    }

    @Test
    public void testValidProduct() {
        String name = "X-bacon";
        String description = "O melhor da casa";
        Long categoryId = 1L;
        Double price = 20.50;

        ProductDTO productDTO = new ProductDTO(name, description, categoryId, price);


        assertEquals(0, validator.validate(productDTO).size());
    }

    @Test
    public void testEmptyName() {
        String name = "";
        String description = "O melhor da casa";
        Long categoryId = 1L;
        Double price = 20.50;

        ProductDTO productDTO = new ProductDTO(name, description, categoryId, price);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertEquals(1, violations.size());

        ConstraintViolation<ProductDTO> violation = violations.iterator().next();
        assertEquals("Nome do produto é obrigatório", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    public void testNullName() {
        String name = null;
        String description = "O melhor da casa";
        Long categoryId = 1L;
        Double price = 20.50;

        ProductDTO productDTO = new ProductDTO(name, description, categoryId, price);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertEquals(1, violations.size());

        ConstraintViolation<ProductDTO> violation = violations.iterator().next();
        assertEquals("Nome do produto é obrigatório", violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
    }

    @Test
    public void testNegativePrice() {
        String name = "X-bacon";
        String description = "O melhor da casa";
        Long categoryId = 1L;
        Double price = -5.00;

        ProductDTO productDTO = new ProductDTO(name, description, categoryId, price);

        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertEquals(1, violations.size());

        ConstraintViolation<ProductDTO> violation = violations.iterator().next();
        assertEquals("Preço deve ser positivo", violation.getMessage());
        assertEquals("price", violation.getPropertyPath().toString());
    }

    @Test
    public void testNullDescription() {
        String name = "X-bacon";
        String description = null;
        Long categoryId = 1L;
        Double price = 20.50;

        ProductDTO productDTO = new ProductDTO(name, description, categoryId, price);

        assertEquals(0, validator.validate(productDTO).size());
    }
}