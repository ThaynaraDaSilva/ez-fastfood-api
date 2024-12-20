package br.com.fiap.ez.fastfood.application.usecases;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Order;
import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.PaymentMapper;

public class PaymentUseCase {

	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;

	public PaymentUseCase(PaymentRepository paymentRepository, OrderRepository orderRepository) {
		this.paymentRepository = paymentRepository;
		this.orderRepository = orderRepository;

	}

	public void registerPayment(Order order) {

		Payment payment = new Payment();

		payment.setOrder(order);

		payment.setCustomer(order.getCustomer());
		payment.setPaymentPrice(order.getTotalPrice());
		payment.setPaymentStatus(PaymentStatus.PENDING);
		paymentRepository.registerPayment(payment);

	}

	public PaymentDTO registerPaymentStatus(PaymentDTO paymentDto) {
		Payment payment = paymentRepository.findPaymentById(paymentDto.getPaymentId());
		if (payment == null) {
			throw new BusinessException("Não existe pagamento com este id");
		}
		Order order = orderRepository.findOrderById(payment.getOrder().getId());

		// payment
		if (payment.getPaymentStatus() == PaymentStatus.PENDING) {
			payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
			payment.setPaymentStatus(PaymentStatus.valueOf(paymentDto.getPaymentStatus().toUpperCase()));
			paymentRepository.registerPaymentStatus(payment);

			// order
			if (PaymentStatus.valueOf(paymentDto.getPaymentStatus().toUpperCase()) == PaymentStatus.OK) {
				order.setStatus(OrderStatus.RECEIVED);
			} else {
				order.setStatus(OrderStatus.CANCELLED);
			}
			order.setOrderTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
			orderRepository.save(order);

			return PaymentMapper.domainToResponseDto(payment);
		} else {
			throw new BusinessException("Este pagamento já foi confirmado ou recusado.");
		}

	}

	public PaymentDTO checkPaymentStatus(Long paymentId) {
		Payment payment = paymentRepository.findPaymentById(paymentId);
		if (payment != null) {
			return PaymentMapper.domainToResponseDto(payment);
		} else {
			throw new BusinessException("Não existe pagamento com este id");
		}

	}
}
