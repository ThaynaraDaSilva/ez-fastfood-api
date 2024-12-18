package br.com.fiap.ez.fastfood.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

	@Test
	void testProductCreation() {
	    Category category = new Category(1L, "LANCHE");
	    Product product = new Product(1L, "Cheeseburger", "Burger delicioso", 10.99, category);

	    assertEquals(1L, product.getId());
	    assertEquals("Cheeseburger", product.getName());
	    assertEquals("Burger delicioso", product.getDescription());
	    assertEquals(10.99, product.getPrice());
	    assertEquals(category, product.getCategory());
	}

    @Test
    void testProductSetters() {
        Product product = new Product();
        Category category = new Category(2L, "BEBIDA");

        product.setId(2L);
        product.setName("Refri");
        product.setDescription("Refrescante");
        product.setPrice(2.99);
        product.setCategory(category);

        assertEquals(2L, product.getId());
        assertEquals("Refri", product.getName());
        assertEquals("Refrescante", product.getDescription());
        assertEquals(2.99, product.getPrice());
        assertEquals(category, product.getCategory());
    }

    @Test
    void testCategoryEqualsAndHashCode() {
        Category category1 = new Category(1L, "LANCHE");
        Category category2 = new Category(1L, "LANCHE");
        Category category3 = new Category(2L, "BEBIDA");

        assertEquals(category1, category2);
        assertNotEquals(category1, category3);
        assertEquals(category1.hashCode(), category2.hashCode());
    }
    
    @Test
    void testSetPrice() {
        Product product = new Product(1L, "Cheeseburger", "Burger delicioso", 10.99, new Category(1L, "LANCHE"));
        Double newPrice = 12.99;

        product.setPrice(newPrice);

        assertEquals(newPrice, product.getPrice(), "The price should be updated correctly.");
    }
}
