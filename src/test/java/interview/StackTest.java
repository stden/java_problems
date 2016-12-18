package interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class StackTest extends Assert {
    @Test
    public void testStack() {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}
