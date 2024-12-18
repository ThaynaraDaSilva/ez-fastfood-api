package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CategoryEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {

    @Test
    void testEntityToDomain() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("LANCHE");

        Category category = CategoryMapper.entityToDomain(categoryEntity);

        assertThat(category).isNotNull();
        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("LANCHE");
    }

    @Test
    void testDomainToEntity() {
        Category category = new Category(2L, "BEBIDA");

        CategoryEntity categoryEntity = CategoryMapper.domainToEntity(category);

        assertThat(categoryEntity).isNotNull();
        assertThat(categoryEntity.getId()).isEqualTo(2L);
        assertThat(categoryEntity.getName()).isEqualTo("BEBIDA");
    }
    
    @Test
    void testCategoryMapperConstructor() {
        CategoryMapper categoryMapper = new CategoryMapper();
        assertThat(categoryMapper).isNotNull();
    }
}
