package LLD.Patterns.Structural.Composite;

// Solve a expression

interface ArithmeticExpression {
    int evaluate();
}

class Number implements ArithmeticExpression {
    int value;

    public Number(int value) { this.value = value; }

    @Override
    public int evaluate() {
        System.out.println("Number value is : " + value);
        return value;
    }
}

enum Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}
class Expression implements ArithmeticExpression {
    ArithmeticExpression left;
    ArithmeticExpression right;
    Operation operation;

    public Expression(ArithmeticExpression left, ArithmeticExpression right, Operation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    public int evaluate() {
        int value = 0;
        switch (operation) {
            case ADD:
                value = left.evaluate() + right.evaluate();
                break;
            case SUBTRACT:
                value = left.evaluate() - right.evaluate();
                break;
            case MULTIPLY:
                value = left.evaluate() * right.evaluate();
                break;
            case DIVIDE:
                value = left.evaluate() / right.evaluate();
                break;
        }
        System.out.println("Expression value is : " + value);
        return value;
    }
}

class Main {
    public static void main(String[] args) {
        ArithmeticExpression two = new Number(2);
        ArithmeticExpression one = new Number(1);
        ArithmeticExpression seven = new Number(7);

        ArithmeticExpression addExpression = new Expression(one, seven, Operation.ADD);
        ArithmeticExpression mulExpression = new Expression(two, addExpression, Operation.MULTIPLY);

        System.out.println("Result: " + mulExpression.evaluate());
    }
}