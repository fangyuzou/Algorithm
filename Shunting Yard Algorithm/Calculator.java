/** We will use the shunting yard algorithm to parse an infix expression into a postfix expression, 
  * then evaluate the expression. A good explanation of the shunting yard algorithm can be find on
  * the wikipedia [Shunting Yard Algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm).
  *
  */
  
public class Calculator {
    private final boolean LEFT = true;
    private final boolean RIGHT = false;
    
    public List<String> infix2postfix(String infix) {
        List<String> postfix = new ArrayList<>();
        if (infix == null || infix.isEmpty())  return postfix;
        
        Stack<String> ops = new Stack<>();
        int i = 0, j = 0;
        while (j < infix.length()) {
            char token = infix.charAt(j);
            if (isOperator(token)) {
                while (!ops.isEmpty() && (precedence(token) < precedence(ops.peek() || 
                        precedence(token) == precedence(ops.peek() && associtivity(token) == LEFT))
                     postfix.add(String.valueOf(ops.pop()));
                ops.push(token);
            }
            else if (token == '(')  ops.push(token);
            else if (token == ')') {
                while (!ops.isEmpty() && ops.peek() != '(')  postfix.add(String.valueOf(ops.pop()));
                if (ops.isEmpty()) throw new IllegalArgumentException("The infix expression is not valid!");
                ops.pop();
            }
            else if (Character.isDigit(token)) {
                StringBuilder operand = new StringBuilder();
                int k = j+1;
                char nextToken = infix.charAt(k);
                while (k < infix.length() && !isOperator(infix.charAt(k)) && infix.charAt(k) != '(' 
                            && infix.charAt(k) != ')') {
                    if (Character.isDigit(infix.charAt(k)))  operand.append(nextToken);
                    k++;
                }
                postfix.add(operand.toString());
                j = k-1;
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
            String s = postfix.pop();
            if (Character.isDigit(s.charAt(0)))  operand.push(Integer.parseInt(s));
            else {
                operand.push(calulate(operand.pop(), operand.pop(), s.charAt(0)));
            }
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
