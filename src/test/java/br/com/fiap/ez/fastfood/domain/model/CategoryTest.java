package br.com.fiap.ez.fastfood.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testCategoryCreation() {
        Category category = new Category(1L, "LANCHE");

        assertEquals(1L, category.getId());
        assertEquals("LANCHE", category.getName());
    }

    @Test
    void testSettersAndGetters() {
        Category category = new Category();

        category.setId(2L);
        category.setName("BEBIDA");

        assertEquals(2L, category.getId());
        assertEquals("BEBIDA", category.getName());
    }

    @Test
    void testCategoryEqualsAndHashCode() {
        Category category1 = new Category(1L, "LANCHE");
        Category category2 = new Category(1L, "LANCHE");
        Category category3 = new Category(2L, "BEBIDA");

        assertEquals(category1, category2);
        assertNotEquals(category1, category3);

        assertEquals(category1.hashCode(), category2.hashCode());
        assertNotEquals(category1.hashCode(), category3.hashCode());
    }
    
    @Test
    void testEqualsWithNull() {
        Category category = new Category(1L, "LANCHE");

        assertFalse(category.equals(null));
    }
}
