package agents.cards.visa.agents.card.model;

public class CardDto {
    private final String uuid;
    private final String cardAccountUuid;
    private final Long identifierConductor;
    private final Long linkedCardIdentifierConductor;
    private final Long statusId;
    private final Long stageId;
    private final String number;

    public CardDto(String uuid, String cardAccountUuid, Long identifierConductor, Long linkedCardIdentifierConductor, Long statusId, Long stageId, String number) {
        this.uuid = uuid;
        this.cardAccountUuid = cardAccountUuid;
        this.identifierConductor = identifierConductor;
        this.linkedCardIdentifierConductor = linkedCardIdentifierConductor;
        this.statusId = statusId;
        this.stageId = stageId;
        this.number = number;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCardAccountUuid() {
        return cardAccountUuid;
    }

    public Long getIdentifierConductor() {
        return identifierConductor;
    }

    public Long getLinkedCardIdentifierConductor() {
        return linkedCardIdentifierConductor;
    }

    public Long getStatusId() {
        return statusId;
    }

    public Long getStageId() {
        return stageId;
    }

    public String getNumber() {
        return number;
    }
}
