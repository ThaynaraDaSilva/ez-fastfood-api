package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.application.usecases.CustomerUseCase;
import br.com.fiap.ez.fastfood.application.usecases.OrderUseCase;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.application.usecases.ProductUseCase;
import br.com.fiap.ez.fastfood.domain.repository.CategoryRepository;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public CustomerUseCase customerUseCase(CustomerRepository customerRepository) {
		return new CustomerUseCase(customerRepository);
	}

	@Bean
	public ProductUseCase productUseCase(ProductRepository productRepository,CategoryRepository categoryRepository) {
		return new ProductUseCase(productRepository,categoryRepository);
	}

	@Bean
	public OrderUseCase orderUseCase(OrderRepository orderRepository, ProductRepository productRepository,
			CustomerRepository customerRepository, PaymentUseCase paymentUseCase) {
		return new OrderUseCase(orderRepository, productRepository, customerRepository, paymentUseCase);
	}

	@Bean
	public PaymentUseCase paymentUseCase(PaymentRepository paymentRepository, OrderRepository orderRepository) {
		return new PaymentUseCase(paymentRepository,orderRepository); 
	}

}
