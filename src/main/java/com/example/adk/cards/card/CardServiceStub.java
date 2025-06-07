package com.example.adk.cards.card;

import com.example.adk.cards.card.model.CancelCardDto;
import com.example.adk.cards.card.model.CardDto;
import com.example.adk.cards.shared.ApiResponse;

/**
 * Simple in-memory implementation used for development/testing.
 */
public class CardServiceStub implements CardService {

    @Override
    public ApiResponse<String> block(String uuid) {
        String body = "cartão bloqueado";
        return new ApiResponse<>(200, body, "Cartão " + uuid + " bloqueado com sucesso.");
    }

    @Override
    public ApiResponse<String> unblock(String uuid) {
        String body = "cartão desbloqueado";
        return new ApiResponse<>(200, body, "Cartão " + uuid + " desbloqueado com sucesso.");
    }

    @Override
    public ApiResponse<CardDto> get(String uuid) {
        CardDto dto = new CardDto(uuid, "4321", 10000L, 2000L, 8000L, 0L, "7345 7485 8347 2486");
        return new ApiResponse<>(200, dto, "Consulta do cartão " + uuid + " realizada.");
    }

    @Override
    public ApiResponse<String> cancel(CancelCardDto dto) {
        String body = "cartão cancelado";
        return new ApiResponse<>(200, body, "Cartão " + dto.uuid() + " cancelado.");
    }
}
