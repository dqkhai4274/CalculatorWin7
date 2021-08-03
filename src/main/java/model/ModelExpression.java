package model;

import MyException.NotANumberException;
import view.KeyBoardObserver;

public abstract class ModelExpression implements KeyBoardObserver, ModelPublisher {
    public static final String operations = "+-*/";
    public static final String[] clear = {"CE", "C"};
    public static final String digits = "0123456789";
    public static final String[] selfOperations = {"NE", "sqrt", "%", "1/x"};

    public abstract void clickDigit(String message);
    public abstract void clickBasicOperation(String message);
    public abstract void clickExtendedOperation(String message);
    public abstract void clickMemory(String message);
    public abstract void clickClear(String message);
    public abstract void clickUndo();
    public abstract void clickEqual();
    public abstract void clickDot();
    public void clickParenthese(String message){
        //default mode is not support
        throw new UnsupportedOperationException();
    }
    public void clickUnit(String message){
        throw new UnsupportedOperationException();
    }



    public static boolean isDigit(String message){
        return digits.contains(message);
    }

    public static boolean isOperation(String message){
        return operations.contains(message);
    }

    public static boolean isExtendedOperation(String message){
        for(String selfOp : selfOperations){
            if(selfOp.equals(message)){
                return true;
            }
        }
        return false;
    }

    public static boolean isClear(String message){
        for(String c : clear){
            if(c.equals(message))
                return true;
        }
        return false;
    }

    public static double calculate(double o1, double o2, String oper) throws ArithmeticException{
        if(oper.equals("*")){
            return o1*o2;
        }else if(oper.equals("-")){
            return o1-o2;
        }else if(oper.equals("/")){
            if(o2 == 0){
                throw new ArithmeticException();
            }
            return o1/o2;
        }else if(oper.equals("+")){
            return o2+o1;
        }
        return o2;
    }

    public static double extendedCalculate(double o1, String oper) throws ArithmeticException, NotANumberException {
        if(oper == "NE"){
            return 0-o1;
        }else if(oper == "1/x"){
            if(o1 == 0){
                throw new ArithmeticException();
            }
            return 1/o1;
        }else{
            if(o1 < 0){
                throw new NotANumberException();
            }
            return Math.sqrt(o1);
        }
    }
}

