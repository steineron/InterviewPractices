import java.util.LinkedList;
import java.util.Scanner;

class Stack {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int i = findParenthesesStructureViolation(input.next());
            if (i >= 0) {
                System.out.println("violation at: " + i);
            }
        }
        input.close();
    }

    /* process a non-null string for valid parentheses structure.
    Return the index of the parentheses structure violation. Or -1 if not found
    */
    private static int findParenthesesStructureViolation(final String s) {
        char[] chars = s.toCharArray();
        // use a linked list as a stack - every ‘(‘ pushes something to the list
        // every ‘)’ pops something
        // a correct structure would yield a 0-sized, empty list at the end
        // of examination
        LinkedList<Character> parenthesesStack = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                parenthesesStack.addFirst(c);
            } else if (c == ')') {
                if (parenthesesStack.peekFirst() != null) {
                    parenthesesStack.removeFirst();
                } else {
                    return i; // the index in which an excessive  ‘)` was found
                }
            }
        }
        // if the stack is empty - return -1 otherwise return the string length
        // i.e. at least one  ‘(‘ wasn’t closed
        return parenthesesStack.isEmpty() ?
                -1 :
                chars.length;

    }
}
