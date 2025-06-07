package agents.cards.visa.agents.card.model;

import com.google.adk.tools.Annotations;

public class CancelCardDto {

    private String uuid;
    private boolean gerarNovaVia;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isGerarNovaVia() {
        return gerarNovaVia;
    }

    public void setGerarNovaVia(boolean gerarNovaVia) {
        this.gerarNovaVia = gerarNovaVia;
    }
}
