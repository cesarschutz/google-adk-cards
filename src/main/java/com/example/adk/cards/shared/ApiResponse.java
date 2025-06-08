package com.example.adk.cards.shared;
/**
 * Simple wrapper for HTTP-like responses returned by the agents.
 */
public class ApiResponse<T> {

    private final int status;
    private final T body;
    private final String message;

    public ApiResponse(int status, T body, String message) {
        this.status = status;
        this.body = body;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }
}
