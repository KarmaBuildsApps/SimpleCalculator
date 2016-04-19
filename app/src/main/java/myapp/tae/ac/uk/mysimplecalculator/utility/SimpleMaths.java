package myapp.tae.ac.uk.mysimplecalculator.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karma on 18/04/16.
 */
public class SimpleMaths {

    public static ArrayList<String> getReversePolishNotationFormat(String problem) {
        ArrayList<String> reversePolishNotationFormat = new ArrayList<>();
        String number = "";
        String fourOp = "/*+-";
        String unSortedOperator = "";
        char c;
        for (int i = 0; i < problem.length(); i++) {
            c = problem.charAt(i);
            if (fourOp.contains(String.valueOf(c))) {
                reversePolishNotationFormat.add(number);
                number = "";
                if (!unSortedOperator.isEmpty()) {
                    char prevOp = unSortedOperator.charAt(unSortedOperator.length() - 1);
                    if (prevOp == '*' || prevOp == '/' || c == '+' || c == '-') {
                        reversePolishNotationFormat.add(String.valueOf(prevOp));
                        unSortedOperator = unSortedOperator.substring(0, unSortedOperator.length() - 1);
                        if (!unSortedOperator.isEmpty() && !(c == '/' || c == '*')) {
                            reversePolishNotationFormat.add(unSortedOperator);
                            unSortedOperator = "";
                        }
                    }
                }
                unSortedOperator += c;
                continue;
            }
            number += c;
            if (i == problem.length() - 1) {
                reversePolishNotationFormat.add(number);
                for (int j = unSortedOperator.length() - 1; j >= 0; j--) {
                    reversePolishNotationFormat.add(String.valueOf(unSortedOperator.charAt(j)));
                }
            }
        }

        return reversePolishNotationFormat;
    }

    public static String calculateSimpleMaths(String problem) {
        List<String> reversePolishNotation = getReversePolishNotationFormat(problem);
        List<String> unCalculatedNumber = new ArrayList<>();
        String fourOp = "/*+-";
        String operationResult = "";
        String num1 = "", num2 = "";
        String nextItem;
        for (int i = 0; i < reversePolishNotation.size(); i++) {
            if (i == 0) {
                num1 = reversePolishNotation.get(i);
                num2 = reversePolishNotation.get(i + 1);
                i++;
                continue;
            }
            nextItem = reversePolishNotation.get(i);
            if (fourOp.contains(nextItem)) {
                operationResult = calculate(num1, num2, nextItem.charAt(0));
                if (unCalculatedNumber.size() > 0) {
                    num1 = unCalculatedNumber.get(unCalculatedNumber.size() - 1);
                    num2 = operationResult;
                } else if (i != reversePolishNotation.size() - 1) {
                    num1 = operationResult;
                    num2 = reversePolishNotation.get(i + 1);
                    i++;
                }
                continue;
            }
            unCalculatedNumber.add(num1);
            num1 = num2;
            num2 = nextItem;
        }

        return operationResult;
    }

    public static String calculate(String num1, String num2, char operator) {
        double result = 0;
        switch (operator) {
            case '/':
                result = Double.parseDouble(num1) / Double.parseDouble(num2);
                break;
            case '*':
                result = Double.parseDouble(num1) * Double.parseDouble(num2);
                break;
            case '+':
                result = Double.parseDouble(num1) + Double.parseDouble(num2);
                break;
            case '-':
                result = Double.parseDouble(num1) - Double.parseDouble(num2);
                break;
        }
        return String.valueOf(result);
    }
}
