package com.univale.techshop.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
  @NotNull(message = "O produto é obrigatório")
  Long productId,

  @Positive( message = "A quantidade deve ser maior que zero.")
  Integer quantity
) {}
