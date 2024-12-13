package br.com.fiap.ez.fastfood.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.dto.ProductResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;
import br.com.fiap.ez.fastfood.domain.repository.CategoryRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

class ProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductUseCase productUseCase;

    private ProductDTO productDTO;
    private Product product;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product Name");
        productDTO.setDescription("Product Description");
        productDTO.setPrice(100.0);
        productDTO.setCategoryId(1L);

        category = new Category(1L, "Category Name");
        product = new Product(1L, "Product Name", "Product Description", 10.0, category);
    }

    @Test
    void testSaveProductSuccess() {
    	ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Product Name");
        productDTO.setDescription("Product Description");
        productDTO.setPrice(100.0);
        productDTO.setCategoryId(1L);

        category = new Category(1L, "Category Name");
        product = new Product(1L, "Product Name", "Product Description", 10.0, category);
        // Mocking
        when(categoryRepository.findById(productDTO.getCategoryId())).thenReturn(category);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Method execution
        ProductResponseDTO result = productUseCase.save(productDTO);

        // Assertions
        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getDescription(), result.getDescription());
//        assertEquals(productDTO.getPrice(), result.getPrice());
//        assertEquals(category.getName(), result.getCategoryName());
    }

//    @Test
//    void testSaveProductCategoryNotFound() {
//        when(categoryRepository.findById(productDTO.getCategoryId())).thenReturn(null);
//
//        BusinessException exception = assertThrows(BusinessException.class, () -> productUseCase.save(productDTO));
//        assertEquals("Categoria escolhida não existe.", exception.getMessage());
//    }
//
//    @Test
//    void testUpdateProductSuccess() {
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//        when(categoryRepository.findById(productDTO.getCategoryId())).thenReturn(category);
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        ProductResponseDTO result = productUseCase.updateProduct(product.getId(), productDTO);
//
//        assertNotNull(result);
//        assertEquals(productDTO.getName(), result.getName());
//        assertEquals(productDTO.getDescription(), result.getDescription());
//        assertEquals(productDTO.getPrice(), result.getPrice());
//        assertEquals(category.getName(), result.getCategoryName());
//    }

    @Test
    void testUpdateProductNotFound() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> productUseCase.updateProduct(product.getId(), productDTO));
        assertEquals("Produto escolhido não foi encontrado.", exception.getMessage());
    }

//    @Test
//    void testUpdateProductCategoryNotFound() {
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//        when(categoryRepository.findById(productDTO.getCategoryId())).thenReturn(null);
//
//        // Method execution & Assertions
//        BusinessException exception = assertThrows(BusinessException.class, () -> productUseCase.updateProduct(product.getId(), productDTO));
//        assertEquals("Categoria não existe", exception.getMessage());
//    }

    @Test
    void testFindByIdSuccess() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ProductResponseDTO result = productUseCase.findById(product.getId());

        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> productUseCase.findById(product.getId()));
        assertEquals("Produto não foi encontrado.", exception.getMessage());
    }

    @Test
    void testFindAllSuccess() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductResponseDTO> result = productUseCase.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteProductSuccess() {
        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(productRepository.isProductAssociatedWithOrderItems(product.getId())).thenReturn(false);

        productUseCase.deleteById(product.getId());

        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test
    void testDeleteProductAssociatedWithOrderItems() {
        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(productRepository.isProductAssociatedWithOrderItems(product.getId())).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> productUseCase.deleteById(product.getId()));
        assertEquals("Esse produto não pode ser excluído,uma vez que já há pedido(s).", exception.getMessage());
    }

    @Test
    void testDeleteProductNotFound() {
        when(productRepository.existsById(product.getId())).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> productUseCase.deleteById(product.getId()));
        assertEquals("Produto escolhido não foi encontrado.", exception.getMessage());
    }

//    @Test
//    void testFindProductByCategoryIdSuccess() {
//        when(productRepository.findProductByCategoryId(productDTO.getCategoryId())).thenReturn(List.of(product));
//
//        List<ProductResponseDTO> result = productUseCase.findProductByCategoryId(productDTO.getCategoryId());
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void testFindProductByCategoryIdNotFound() {
//        when(productRepository.findProductByCategoryId(productDTO.getCategoryId())).thenReturn(List.of());
//
//        BusinessException exception = assertThrows(BusinessException.class, () -> productUseCase.findProductByCategoryId(productDTO.getCategoryId()));
//        assertEquals("Não há produtos cadastrados com esta categoria.", exception.getMessage());
//    }
}
