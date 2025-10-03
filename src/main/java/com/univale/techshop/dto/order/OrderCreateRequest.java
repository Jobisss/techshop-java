package com.univale.techshop.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record OrderCreateRequest (
    @NotNull( message = "O cliente é obrigatorio")
    Long clientId,

    @Size(max = 255, message = "Tamanho não pode ser maior que 255")
    String note,

    @NotEmpty(message = "O pedido deve ter pelo menos 1 item")
    List<@Valid OrderItemRequest> items
) {}
