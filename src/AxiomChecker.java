import parser.Expression;
import parser.ExpressionParser;
import parser.ExpressionType;

import java.util.HashMap;

class AxiomChecker {
    private static final String ax1 = "A -> B -> A";
    private static final String ax2 = "(A -> B) -> (A -> B -> C) -> (A -> C)";
    private static final String ax3 = "A -> B -> A & B";
    private static final String ax4 = "A & B -> A";
    private static final String ax5 = "A & B -> B";
    private static final String ax6 = "A -> A | B";
    private static final String ax7 = "B -> A | B";
    private static final String ax8 = "(A -> C) -> (B -> C) -> (A | B -> C)";
    private static final String ax9 = "(A -> B) -> (A -> !B) -> !A";
    private static final String ax10 = "!!A -> A";

    static int check(Expression expression) {
        ExpressionParser expressionParser = new ExpressionParser();
        if (checkMatching(expression, expressionParser.parse(ax1), new HashMap<>())) return 1;
        if (checkMatching(expression, expressionParser.parse(ax2), new HashMap<>())) return 2;
        if (checkMatching(expression, expressionParser.parse(ax3), new HashMap<>())) return 3;
        if (checkMatching(expression, expressionParser.parse(ax4), new HashMap<>())) return 4;
        if (checkMatching(expression, expressionParser.parse(ax5), new HashMap<>())) return 5;
        if (checkMatching(expression, expressionParser.parse(ax6), new HashMap<>())) return 6;
        if (checkMatching(expression, expressionParser.parse(ax7), new HashMap<>())) return 7;
        if (checkMatching(expression, expressionParser.parse(ax8), new HashMap<>())) return 8;
        if (checkMatching(expression, expressionParser.parse(ax9), new HashMap<>())) return 9;
        if (checkMatching(expression, expressionParser.parse(ax10), new HashMap<>())) return 10;
        return -1;
    }

    private static boolean checkMatching(Expression expression, Expression pattern, HashMap<String, Expression> values) {
        if (expression == null) return false;
        if (pattern.getType() == ExpressionType.VARIABLE) {
            if (values.containsKey(pattern.toString())) {
                return values.get(pattern.toString()).equals(expression);
            } else {
                values.put(pattern.toString(), expression);
                return true;
            }
        } else {
            if (expression.getType() == ExpressionType.VARIABLE) return false;
            if (expression.getType()!=pattern.getType()) return false;
            return checkMatching(expression.getLeftOperand(), pattern.getLeftOperand(), values) && checkMatching(expression.getRightOperand(), pattern.getRightOperand(), values);
        }
    }
}
