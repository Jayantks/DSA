import java.util.ArrayDeque;
import java.util.Deque;
class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> st = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!st.isEmpty() && st.peek() == c) {
                st.pop();       // cancel the duplicate
            } else {
                st.push(c);     // keep the character
            }
        }

        StringBuilder res = new StringBuilder();
        while (!st.isEmpty()) {
            res.append(st.pop());
        }

        return res.reverse().toString();
    }
}