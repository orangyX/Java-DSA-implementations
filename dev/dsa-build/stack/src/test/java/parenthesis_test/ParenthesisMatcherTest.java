package parenthesis_test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import parenthesis.ParenthesisMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

/*
    Compilation instructions:
        javac -cp "lib/*" parenthesis/*.java parenthesis_test/*.java stack/*.java
        java -cp "class;lib/*" org.junit.platform.console.ConsoleLauncher --select-package parenthesis_test
*/

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
