package com.univale.techshop.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateRequest(
        @NotNull( message = "Deve informar o id do usuário")
        Long categoryId,

        @NotNull( message = "O nome é obrigatorio")
        @Size( max = 100, message = "O nome não deve ser maior que 100 caracteres")
        String nome,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que um centavo")
        BigDecimal preco,

        @NotNull( message = "A Image url é obrigatória")
        String imageUrl
) {}
