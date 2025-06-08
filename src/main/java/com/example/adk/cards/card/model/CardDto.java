package com.example.adk.cards.card.model;

/**
 * Representation of a credit card returned from the API.
 */
public record CardDto(
        String uuid,
        String cardAccountUuid,
        Long identifierConductor,
        Long linkedCardIdentifierConductor,
        Long statusId,
        Long stageId,
        String number) {
}
