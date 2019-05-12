package org.bzyw.rpn;

import java.math.BigInteger;
import java.util.*;

public class MathUtil {
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';
    private static final char SQUARE_BRACKET_LEFT = '[';
    private static final char SQUARE_BRACKET_RIGHT = ']';
    private static final char BRACE_LEFT = '{';
    private static final char BRACE_RIGHT = '}';
    private static final char ADD = '+';
    private static final char SUBSTRACT = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private boolean add;

    private MathUtil() {
    }

    public static void main(String[] args) {
        String expression = "4+(1+3*2)/2";
        validate("{1+[2*(1+9)]}+9");
        System.out.println(eval(expression));
        //eval("(3+4)*5-6");
        //eval("((3+4)*5-6)/4-7");
        //eval("a+b*c+(d*e+f)*g");
        //eval("3+4*5-6");
    }

    public static void validate(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char temp = expression.charAt(i);
            if (isOpenBracket(temp)) {
                stack.push(temp);
            } else if (isCloseBracket(temp)) {
                char ch = 0;
                try {
                    ch = stack.pop();
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("expression is illegal.");
                }

                if (!isPairs(temp, ch))
                    throw new IllegalArgumentException("expression is illegal.");
            }
        }
        if (!stack.isEmpty())
            throw new IllegalArgumentException("expression is illegal.");
    }

    private static boolean isPairs(char ch1, char ch2) {
        boolean flag1 = (ch1 == LEFT_BRACKET && ch2 == RIGHT_BRACKET) || (ch2 == LEFT_BRACKET && ch1 == RIGHT_BRACKET);
        boolean flag2 = (ch1 == SQUARE_BRACKET_LEFT && ch2 == SQUARE_BRACKET_RIGHT) || (ch2 == SQUARE_BRACKET_LEFT && ch1 == SQUARE_BRACKET_RIGHT);
        boolean flag3 = (ch1 == BRACE_LEFT && ch2 == BRACE_RIGHT) || (ch2 == BRACE_LEFT && ch1 == BRACE_RIGHT);
        if (flag1 || flag2 || flag3)
            return true;
        else
            return false;
    }

    private static boolean isCloseBracket(char ch) {
        if (ch == RIGHT_BRACKET || ch == SQUARE_BRACKET_RIGHT || ch == BRACE_RIGHT) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOpenBracket(char ch) {
        if (ch == LEFT_BRACKET || ch == SQUARE_BRACKET_LEFT || ch == BRACE_LEFT) {
            return true;
        } else {
            return false;
        }
    }

    public static String eval(String expression) {
        Character[] postfix = infixToPostfix(expression);
        System.out.println(Arrays.toString(postfix));
        Stack<Character> stack = new Stack();
        for (char ch : postfix) {
            if (isOperand(ch)) {
                stack.push(ch);
            } else {
                char second = stack.pop();
                char first = stack.pop();
                stack.push(calculate(String.valueOf(first), String.valueOf(second), ch));
            }
        }
        return String.valueOf(stack.pop());
    }

    private static char calculate(String operand1, String operand2, char oper) {
        BigInteger op1 = new BigInteger(operand1);
        BigInteger op2 = new BigInteger(operand2);
        BigInteger result = null;
        switch (oper) {
            case ADD:
                result = op1.add(op2);
                break;
            case SUBSTRACT:
                result = op1.subtract(op2);
                break;
            case MULTIPLY:
                result = op1.multiply(op2);
                break;
            case DIVIDE:
                result = op1.divide(op2);
                break;
            default:
                //...
        }
        return result.toString().toCharArray()[0];
    }

    private static Character[] infixToPostfix(String infix) {
        char[] charArr = infix.toCharArray();
        List<Character> output = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        for (char ch : charArr) {
            if (isOperand(ch))
                output.add(ch);
            else if (ch == LEFT_BRACKET) {
                stack.push(ch);
            } else if (ch == RIGHT_BRACKET) {
                while (!stack.isEmpty()) {
                    Character oper = stack.pop();
                    if (oper != LEFT_BRACKET)
                        output.add(oper);
                    else
                        break;
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(ch);
                    continue;
                }
                boolean flag = false;
                while (!stack.isEmpty()) {
                    Character oper = stack.peek();
                    if (isLowerPriority(oper, ch)) {
                        stack.push(ch);
                        break;
                    } else {
                        if (oper != LEFT_BRACKET) {
                            stack.pop();
                            output.add(oper);
                            flag = true;
                        } else {
                            stack.push(ch);
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag)
                    stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output.toArray(new Character[output.size()]);
    }

    private static boolean isOperand(char symbol) {
        if ((symbol >= '0' && symbol <= '9') || (symbol >= 'a' && symbol <= 'z') || (symbol >= 'A' && symbol <= 'Z'))
            return true;
        else
            return false;
    }

    /**
     * 当操作符oper1优先级比oper2低时返回true，否则返回false
     *
     * @param oper1
     * @param oper2
     * @return
     */
    private static boolean isLowerPriority(char oper1, char oper2) {
        validate(oper1);
        validate(oper2);
        boolean result = false;
        switch (oper1) {
            case ADD:
            case SUBSTRACT:
                if (oper2 == MULTIPLY || oper2 == DIVIDE)
                    result = true;
                break;
            case MULTIPLY:
            case DIVIDE:
                if (oper1 == LEFT_BRACKET)
                    result = true;
                break;
            case LEFT_BRACKET://'('优先级最高
                break;
            default:
                //do nothing
        }
        return result;
    }

    /**
     * 校验操作符
     *
     * @param oper
     */
    private static void validate(char oper) {
        if (oper != ADD && oper != SUBSTRACT && oper != MULTIPLY && oper != DIVIDE && oper != LEFT_BRACKET)
            throw new IllegalArgumentException("unsupported  instruction character:" + oper + ",only +-*/ is supported.");
    }

}
