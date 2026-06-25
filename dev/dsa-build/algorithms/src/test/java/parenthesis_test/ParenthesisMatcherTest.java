package parenthesis_test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import parenthesis.ParenthesisMatcher;

public class ParenthesisMatcherTest {
    ParenthesisMatcher matcher;

    @Test
    @DisplayName("Accept on empty expression")
    void acceptOnEmptyExpression() {
        assertTrue(ParenthesisMatcher.isMatched(""));
    }

    @Test
    @DisplayName("Accept on balanced expression")
    void acceptOnValidExpression() {
        assertTrue(ParenthesisMatcher.isMatched("()(){}{[]}"));
    }

    @Test
    @DisplayName("Reject on unbalanced expression")
    void rejectOnInvalidExpression() {
        assertFalse(ParenthesisMatcher.isMatched("("), "Case 1 failed");
        assertFalse(ParenthesisMatcher.isMatched("(]"), "Case 2 failed");
        assertFalse(ParenthesisMatcher.isMatched("{}{}{}{"), "Case 3 failed");
        assertFalse(ParenthesisMatcher.isMatched("(((((((((({})))))))))]"), "Case 4 failed");
    }
}
