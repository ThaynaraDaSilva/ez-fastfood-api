package br.com.fiap.ez.fastfood.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("Fernando Silva", "fernando.silva@test.com", "000.000.000-01");
    }
    
    @Test
    void testConstructorEmpty() {
        Customer emptyCustomer = new Customer();
        assertThat(emptyCustomer).isNotNull();
        assertThat(emptyCustomer.getName()).isNull();
        assertThat(emptyCustomer.getEmail()).isNull();
        assertThat(emptyCustomer.getCpf()).isNull();
    }

    @Test
    public void testConstructorAndGetters() {
        assertThat(customer.getName()).isEqualTo("Fernando Silva");
        assertThat(customer.getEmail()).isEqualTo("fernando.silva@test.com");
        assertThat(customer.getCpf()).isEqualTo("000.000.000-01");
    }

    @Test
    public void testSetters() {
    	customer.setId(1L);
        customer.setName("Fernando Silva");
        customer.setEmail("fernando.silva@test.com");
        customer.setCpf("000.000.000-01");

        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getName()).isEqualTo("Fernando Silva");
        assertThat(customer.getEmail()).isEqualTo("fernando.silva@test.com");
        assertThat(customer.getCpf()).isEqualTo("000.000.000-01");
    }

    @Test
    public void testIsValid() {
        assertThat(customer.isValid()).isTrue();

        customer.setEmail("invalidEmail");
        assertThat(customer.isValid()).isFalse();

        customer.setEmail("fernando.silva@test.com");
        customer.setCpf(null);
        assertThat(customer.isValid()).isFalse();
    }

    @Test
    public void testHasValidCpfFormat() {
        assertThat(customer.hasValidCpfFormat()).isTrue();

        customer.setCpf("00000000001");
        assertThat(customer.hasValidCpfFormat()).isFalse();

        customer.setCpf("000.000.000-1");
        assertThat(customer.hasValidCpfFormat()).isFalse();

        customer.setCpf("000.000.000-01");
        assertThat(customer.hasValidCpfFormat()).isTrue();
    }
}
