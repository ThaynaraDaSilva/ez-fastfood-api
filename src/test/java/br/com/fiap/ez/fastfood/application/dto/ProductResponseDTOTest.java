package br.com.fiap.ez.fastfood.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductResponseDTOTest {

    @Test
    public void testEmptyConstructor() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        assertNull(productResponseDTO.getId());
        assertNull(productResponseDTO.getName());
        assertNull(productResponseDTO.getDescription());
        assertNull(productResponseDTO.getCategoryName());
        assertNull(productResponseDTO.getPrice());
    }

    @Test
    public void testAllFieldsConstructor() {
        Long id = 1L;
        String name = "X-bacon";
        String description = "O melhor da casa";
        String categoryName = "LANCHE";
        Double price = 20.50;

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(id, name, description, categoryName, price);

        assertEquals(id, productResponseDTO.getId());
        assertEquals(name, productResponseDTO.getName());
        assertEquals(description, productResponseDTO.getDescription());
        assertEquals(categoryName, productResponseDTO.getCategoryName());
        assertEquals(price, productResponseDTO.getPrice());
    }

    @Test
    public void testConstructorWithoutCategoryName() {
        Long id = 2L;
        String name = "X-bacon";
        String description = "O melhor da casa";
        Double price = 20.50;

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(id, name, description, price);

        assertEquals(id, productResponseDTO.getId());
        assertEquals(name, productResponseDTO.getName());
        assertEquals(description, productResponseDTO.getDescription());
        assertNull(productResponseDTO.getCategoryName());
        assertEquals(price, productResponseDTO.getPrice());
    }

    @Test
    public void testAllGettersAndSetters() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        Long id = 3L;
        String name = "Fritas";
        String description = "Crocante!";
        String categoryName = "ACOMPANHAMENTO";
        Double price = 5.00;

        productResponseDTO.setId(id);
        productResponseDTO.setName(name);
        productResponseDTO.setDescription(description);
        productResponseDTO.setCategoryName(categoryName);
        productResponseDTO.setPrice(price);

        assertEquals(id, productResponseDTO.getId());
        assertEquals(name, productResponseDTO.getName());
        assertEquals(description, productResponseDTO.getDescription());
        assertEquals(categoryName, productResponseDTO.getCategoryName());
        assertEquals(price, productResponseDTO.getPrice());
    }

}