package br.com.fiap.ez.fastfood.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderItemDTO {

	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("quantity")
	private Integer quantity;


	public OrderItemDTO() {
		super();
	}

	public OrderItemDTO(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
