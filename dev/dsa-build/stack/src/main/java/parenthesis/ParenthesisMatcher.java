package parenthesis;

import java.util.HashMap;
import stack.Stack;

public class ParenthesisMatcher {
    private static final HashMap<String, String> opening;
    private static final HashMap<String, String> closing;

    static {
        opening = new HashMap<>();
        closing = new HashMap<>();

        opening.put("(", ")");
        opening.put("[", "]");
        opening.put("{", "}");

        closing.put(")", "(");
        closing.put("]", "[");
        closing.put("}", "{");
    }

    public static boolean isMatched(String string) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            String currentChar = Character.toString(string.charAt(i));

            if (opening.containsKey(currentChar)) {
                stack.push(currentChar);
            }

            else if (closing.containsKey(currentChar)) {
                if (stack.isEmpty() || !(stack.pop().equals(closing.get(currentChar)))) {
                    return false;
                }
            }
        }

        if (stack.size() > 0) {
            return false;
        }

        return true;
    }
}