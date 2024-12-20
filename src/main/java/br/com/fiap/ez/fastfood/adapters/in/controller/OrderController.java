package br.com.fiap.ez.fastfood.adapters.in.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ez.fastfood.application.dto.CreateOrderDTO;
import br.com.fiap.ez.fastfood.application.usecases.OrderUseCase;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Operations", description = "Operations related to order")
public class OrderController {

	private final OrderUseCase orderUseCase;

	public OrderController(OrderUseCase orderUseCase) {
		this.orderUseCase = orderUseCase;
	}

	@Operation(summary = "Register a new order (fake checkout)")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Order registered"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/checkout", produces = "application/json")
	public ResponseEntity<?> registerOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {

		return new ResponseEntity<>(orderUseCase.registerOrder(createOrderDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "List all orders")
	@GetMapping(path = "/list-all", produces = "application/json")
	public ResponseEntity<?> listOrders() {

		return new ResponseEntity<>(orderUseCase.listAllOrders(), HttpStatus.OK);

	}

	@Operation(summary = "List orders with status 'READY', 'IN_PREPARATION', 'RECEIVED' considering order time")

	@GetMapping(path = "/list-uncompleted-orders", produces = "application/json")
	public ResponseEntity<?> listUnCompletedOrders() {

		return new ResponseEntity<>(orderUseCase.listUncompletedOrders(), HttpStatus.OK);
	}

	@Hidden
	@Operation(summary = "List unfinished orders")
	@GetMapping(path = "/list-orders-in-queue", produces = "application/json")
	public ResponseEntity<?> listUnfinishedOrders() {

		return new ResponseEntity<>(orderUseCase.listUnfinishedOrders(), HttpStatus.OK);

	}

	@PostMapping(path = "/update-order-status")
	public ResponseEntity<?> updateOrderStatus(@Parameter Long orderId) {

		return new ResponseEntity<>(orderUseCase.updateOrderStatus(orderId), HttpStatus.OK);
	}

}
