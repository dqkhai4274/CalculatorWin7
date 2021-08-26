package model.scienceMode;


import MyException.NotANumberException;
import org.apache.commons.numbers.gamma.Gamma;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class InfixExpression {
    public static final List<List<String>> OPERATORS = Arrays.asList(
            Arrays.asList("ln", "log", "sinh", "sin", "cos", "cosh", "tan", "tanh", "sqr",
                    "fact", "dms", "powten", "cuberoot", "sqrt", "ne", "reciproc", "int", "cube"),
            Arrays.asList("^", "yroot", "exp"),
            Arrays.asList("*", "/", "mod", "%"),
            Arrays.asList("+", "-"));

    private Stack<Double> operands = new Stack<>();
    private Stack<String> operators = new Stack<>();

    public double evaluate(Stack<String> expression) throws EmptyStackException, NotANumberException {
        String[] strings = new String[expression.size()];
        int i = 0;
        for(String s : expression){
            strings[i++] = s;
        }
        return evaluate(strings);
    }

    // expression in String[] array, vd: 4,+,cos,(,ln,7,)
    public double evaluate(String[] expression) throws EmptyStackException, NotANumberException {
        for(String token : expression) {
            if (isOperator(token)) {
                // case : precedence's current token greater than precedence's top stack then we must calculate the expression before this token
                // after that, we push result to operand stack, and continue
                while (!operators.isEmpty() && getPrecedence(operators.peek()) < getPrecedence(token)) {
                    helper();
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                // pop from operators stack until meet the corresponding parentheses
                while (!operators.peek().equals("(")) {
                    helper();
                }
                // then delete the old "(" parentheses
                operators.pop();
            } else {
                try {
                    // if token is a decimal, push to operands stack
                    operands.push(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    System.err.println(token + " is not a decimal number");
                } catch (NullPointerException ex) {
                    System.err.println("token is null");
                }
            }
        }
        while(!operators.isEmpty()){
            helper();
        }
        return operands.peek();
    }

    private void helper() throws EmptyStackException, NotANumberException {
        Stack<Double> reverseOperands = new Stack<>();
        Stack<String> reverseOperators = new Stack<>();
        Double tmp;
        String tmpOperator = operators.pop();
        // if it is monoOperator, calculate then put to stack
        if(isMonoOperator(tmpOperator)) {
            operands.push(calculate(operands.pop(), tmpOperator));
        }
        else{
            // in case operator is empty or operator is not empty but top's stack precedence is greater
            if(operators.isEmpty() || getPrecedence(operators.peek()) > getPrecedence(tmpOperator)){
                tmp = operands.pop();
                operands.push(calculate(operands.pop(), tmp, tmpOperator));
            }else{
                // in this case, precedence of top stack vs tmpOperator must be equal
                // because we only add to operator stack with smaller or equal precedence
                // because the right way to calculate is we must start from first operand, so we create new stack, then do it backward
                while(!operators.isEmpty() && getPrecedence(operators.peek()) == getPrecedence(tmpOperator)){
                    reverseOperators.push(tmpOperator);
                    tmpOperator = operators.pop();
                    reverseOperands.push(operands.pop());
                }
                reverseOperands.push(operands.pop());
                reverseOperands.push(operands.pop());
                reverseOperators.push(tmpOperator);
                // now just pop up from new stack and calculate
                while(!reverseOperators.isEmpty()){
                    Double tmpOperand = reverseOperands.pop();
                    reverseOperands.push(calculate(tmpOperand, reverseOperands.pop(), reverseOperators.pop()));
                }
                operands.push(reverseOperands.pop());
            }
        }
    }

    public static boolean isOperator(String operator){
        for(List<String> l : OPERATORS){
            for(String o : l){
                if(o.equalsIgnoreCase(operator)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMonoOperator(String operator){
        for(List<String> l : OPERATORS){
            for(String o : l){
                if(o.equalsIgnoreCase(operator)){
                    return true;
                }
            }
            break;
        }
        return false;
    }

    public static int getPrecedence(String operator){
        for(List<String> l : OPERATORS){
            for(String o : l){
                if(o.equalsIgnoreCase(operator)){
                    return OPERATORS.indexOf(l);
                }
            }
        }
        return 100;
    }

    public static double calculate(double a, String operator) throws NotANumberException {
        switch (operator){
            case "ln":
                if(a <= 0){
                    throw new NotANumberException();
                }
                return Math.log(a);
            case "log":
                if(a <= 0){
                    throw new NotANumberException();
                }
                return Math.log10(a);
            case "sinh":
                return Math.sinh(a);
            case "sin":
                return Math.sin(a);
            case "cos":
                return Math.cos(a);
            case "cosh":
                return Math.cosh(a);
            case "tan":
                return Math.tan(a);
            case "tanh":
                return Math.tanh(a);
            case "sqr":
                return Math.pow(a, 2);
            case "fact":
                if(a < 0){
                    throw new NotANumberException();
                }
                return Gamma.value(a+1);
            case "dms":
                //not implement, degree, minutes, second
                return 0;
            case "powten":
                return Math.pow(10, a);
            case "cuberoot":
                return Math.cbrt(a);
            case "sqrt":
                if(a < 0){
                    throw new NotANumberException();
                }
                return Math.sqrt(a);
            case "NE":
                return -a;
            case "recipoc":
                if(a == 0){
                    throw new NotANumberException();
                }
                return 1/a;
            case "Int":
                return (int) a;
            case "cube":
                return Math.pow(a, 3);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static double calculate(double a, double b, String operator) throws NotANumberException {
        switch (operator){
            case "^":
                return Math.pow(a, b);
            case "yroot":
                return Math.pow(a, 1.0/b);
            case "exp":
                return a * Math.pow(10, b);
            case "*":
                return a * b;
            case "/":
                if(b == 0){
                    throw new NotANumberException();
                }
                return a/b;
            case "mod":
                if(a != (int)a || b != (int)b || b == 0){
                    System.err.println("can't module a decimal number");
                    throw new NotANumberException();
                }else{
                    return a%b;
                }
            case "+":
                return a+b;
            case "-":
                return a-b;
        }
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args){
        InfixExpression a = new InfixExpression();
        try{
            System.out.println(args + "=" + a.evaluate(args));
        }catch(NotANumberException e){
            System.err.println("syntax error");
        }
    }
}
