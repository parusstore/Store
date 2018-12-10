package com.padma.parus.store.payload;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Order request payload", description = "Place an order payload. This is triggered when user adds an item to the cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

	@NotBlank(message = "addressId cannot be blank")
	@ApiModelProperty(value = "One of the users addressId", required = true, allowableValues = "NonEmpty String")
	private String addressId;
	
	@NotBlank(message = "productId cannot be blank")
	@ApiModelProperty(value = "productId to be bought", required = true, allowableValues = "NonEmpty String")
	private String productId;
	
	@NotBlank(message = "quantity cannot be blank")
	@ApiModelProperty(value = "quantity of the product to be brought", required = true, allowableValues = "NonEmpty String")
	private String quantity;
	
}
