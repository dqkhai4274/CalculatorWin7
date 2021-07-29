package model;

import MyException.NotANumberException;

public interface State {
    void clickOperation(String message);
    void clickDigit(String message);
    void clickMemoryRecall();
    void clickExtendedOperation(String message) throws NotANumberException;
    void clickEqual();
    void clickDot();
    void clickUndo();
}
