/** We will use the shunting yard algorithm to parse an infix expression into a postfix expression, 
  * then evaluate the expression. A good explanation of the shunting yard algorithm can be find on
  * the wikipedia [Shunting Yard Algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm).
  *
  */

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Calculator {
    private final boolean LEFT = true;
    private final boolean RIGHT = false;
    
    public List<String> infix2postfix(String infix) {
        List<String> postfix = new ArrayList<>();
        if (infix == null || infix.isEmpty())  return postfix;
        
        Stack<String> ops = new Stack<>();
        int j = 0;
        while (j < infix.length()) {
            char token = infix.charAt(j);
            if (isOperator(token)) {
                while (!ops.isEmpty() && ops.peek() != '(' && (precedence(token) < precedence(ops.peek()) || 
                            precedence(token) == precedence(ops.peek()) && associativity(token) == LEFT))
                     postfix.add(String.valueOf(ops.pop()));
                ops.push(token);
            }
            else if (token == '(')  ops.push(token);
            else if (token == ')') {
                while (!ops.isEmpty() && ops.peek() != '(')  postfix.add(String.valueOf(ops.pop()));
                if (ops.isEmpty()) throw new IllegalArgumentException("Invalid infix expression!");
                ops.pop();
            }
            else if (Character.isDigit(token)) {
                StringBuilder operand = new StringBuilder();
                while (j < infix.length() && !isOperator(infix.charAt(j)) && infix.charAt(j) != '(' 
                            && infix.charAt(j) != ')') {
                    if (Character.isDigit(infix.charAt(j)))  operand.append(infix.charAt(j));
                    j++;
                }
                postfix.add(operand.toString());
                j--;
            }
            j++;
        }
        return postfix;
    }
    
    private boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/' || token == '^';
    }
    
    private int precedence(char token) {
        if (token == '+' || token == '-')  return 1;
        else if (token == '*' || token == '/')  return 2;
        else if (token == '^')  return 3;
        else throw new IllegalArgumentException("Illegal operator!");
    }
    
    private boolean associativity(char token) {
        if (token == '+' || token == '-' || token == '*' || token == '/')  return LEFT;
        else if (token == '^')  return RIGHT;
        else throw new IllegalArgumentException("Invalid operator!");
    }
    
    public int evaluate(List<String> postfix) {
        if (postfix == null || postfix.isEmpty())  return 0;
     
        Stack<Integer> operand = new Stack<>();
        while (!postfix.isEmpty()) {
            String token = postfix.remove(0);
            if (Character.isDigit(token.charAt(0)))  operand.push(Integer.parseInt(token));
            else operand.push(calculate(operand.pop(), operand.pop(), s.charAt(0)));
        }
        return operand.pop();
    }
    
    private int calculate(int y, int x, char op) {
        switch (op) {
            case '+':  return x+y;
            case '-':  return x-y;
            case '*':  return x*y;
            case '/':  return x/y;
            case '^':  return (int)Math.pow(x, y);
            default: throw new IllegalArgumentException("Illegal operator!");
        }
    }
 
    public static void main(String[] args) {
        String infix = "2+1 0/&(2*$3-1)+,,(3..-2)*2@@^2^2";
        Calculator cal = new Calculator();
        List<String> postfix = cal.infix2postfix(infix);
        for (String token : postfix)  System.out.print(token + " ");
        System.out.println();
        int result = cal.evaluate(postfix);
        System.out.println("result = " + result);
    }
}
