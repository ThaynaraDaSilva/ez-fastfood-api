package br.com.fiap.ez.fastfood.adapters.out.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import br.com.fiap.ez.fastfood.application.ports.out.CustomerRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.CustomerJpaRepository;
import br.com.fiap.ez.fastfood.domain.model.Customer;
import br.com.fiap.ez.fastfood.infrastructure.mapper.CustomerMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CustomerEntity;

public class CustomerRepositoryImpl implements CustomerRepository {

	private final CustomerJpaRepository customerJpaRepository;

	public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository) {
		this.customerJpaRepository = customerJpaRepository;
	}

	@Override
	public Customer save(Customer customer) {
		CustomerEntity entity = CustomerMapper.domainToEntity(customer);
		customerJpaRepository.save(entity);
		return customer;
	}

	@Override
	 public List<Customer> findAll() {
        return customerJpaRepository.findAll().stream()
            .map(CustomerMapper::entityToDomain)
            .collect(Collectors.toList());
    }
	@Override
	public Customer removeByCpf(String cpf) {
		customerJpaRepository.removeCustomerByCpf(cpf);
		return null;
	}

	@Override
	public Optional<Customer> findByCpf(String cpf) {
		 return customerJpaRepository.findCustomerByCpf(cpf).map(CustomerMapper::entityToDomain);
	}

	@Override
	public Customer updateCustomerByCpf(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}