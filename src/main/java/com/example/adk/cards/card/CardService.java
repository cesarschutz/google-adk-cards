package com.example.adk.cards.card;

import com.example.adk.cards.card.model.CancelCardDto;
import com.example.adk.cards.card.model.CardDto;
import com.example.adk.cards.shared.ApiResponse;

/**
 * Contract for operations over credit cards. Implementations
 * should call the actual REST API.
 */
public interface CardService {
    ApiResponse<String> block(String uuid);

    ApiResponse<String> unblock(String uuid);

    ApiResponse<CardDto> get(String uuid);

    ApiResponse<String> cancel(CancelCardDto dto);
}
