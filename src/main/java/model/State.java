package model;

public interface State {
    void clickOperation(String message);
    void clickDigit(String message);
    void clickMemoryRecall();
    void clickMonoOperation(String message);
    void clickEqual();
    void clickDot();
    void clickUndo();
}
