package model.handler;

public interface Handler {
    void setNext(Handler h);
    void handle(String message);
}
