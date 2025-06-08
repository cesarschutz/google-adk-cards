package com.example.adk.cards.card.model;

/**
 * Payload used to cancel a credit card.
 */
public record CancelCardDto(
        String uuid,
        boolean gerarNovaVia) {
}
