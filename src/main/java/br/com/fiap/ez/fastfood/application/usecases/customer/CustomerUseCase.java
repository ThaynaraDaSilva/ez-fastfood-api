package br.com.fiap.ez.fastfood.application.usecases.customer;

import java.util.Optional;

import br.com.fiap.ez.fastfood.application.dto.CustomerDTO;
import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;

public class CustomerUseCase {

	private final CustomerRepository customerRepository;

	public CustomerUseCase(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerDTO create(CustomerDTO customerDTO) {

		// Convert DTO to Domain Model using CustomerMapper
		Customer customer = CustomerMapper.dtoToDomain(customerDTO);

		if (customer != null && customer.isValid()) {
			Optional<Customer> existingCustomer = customerRepository.findByCpf(customer.getCpf());
			if (!existingCustomer.isPresent()) {
				// customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				customerRepository.save(customer);
				return customerDTO;
			} else {
				throw new BusinessException("Cliente já cadastrado");
			}
		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}

	}

	public CustomerDTO deleteCustomerByCpf(String cpf) {
		Optional<Customer> customer = customerRepository.findByCpf(cpf);

		if (customer.isPresent()) {
			customerRepository.removeByCpf(cpf);
			return CustomerMapper.domainToDto(customer.get());
		} else {
			throw new BusinessException("Cliente não encontrado");
		}
	}

	public CustomerDTO updateCustomer(String cpf, CustomerDTO updateCustomerDTO) {
		Customer updatedCustomer = CustomerMapper.dtoToDomain(updateCustomerDTO);

		Optional<Customer> existingCustomer = customerRepository.findByCpf(cpf);
		if (existingCustomer.isPresent() && updatedCustomer.isValid()) {
			Customer customer = existingCustomer.get();
			customer.setName(updatedCustomer.getName());
			customer.setEmail(updatedCustomer.getEmail());
			customer.setCpf(updatedCustomer.getCpf());

			// Update the customer in the repository
			customerRepository.save(customer);
			return CustomerMapper.domainToDto(customer); // Return updated customer as DTO
		} else {
			throw new IllegalArgumentException("Dados inválidos");
		}
	}

	public CustomerDTO findCustomerByCpf(String cpf) {
		return customerRepository.findByCpf(cpf).map(CustomerMapper::domainToDto)
				.orElseThrow(() -> new BusinessException("Cliente não encontrado"));
	}

}
