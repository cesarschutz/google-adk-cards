package agents.cards.visa.agents.shared;

public class ApiResponse<T> {

    public int status;
    public T body;
    public String message;

    public ApiResponse(int status, T body, String message) {
        this.status = status;
        this.body = body;
        this.message = message;
    }
}
