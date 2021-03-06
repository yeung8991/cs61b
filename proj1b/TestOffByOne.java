import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char a = 'a';
        char b = 'b';
        assertTrue(offByOne.equalChars(a, b) && offByOne.equalChars(b, a));

        char c = 'c';
        char e = 'e';
        assertFalse(offByOne.equalChars(c, e));
    }
}
