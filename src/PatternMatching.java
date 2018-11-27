import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jian1.w on 11/27/18.
 */
public class PatternMatching {
    Map<String, Set<String>> cache = new HashMap<>();
    public boolean isMatch(String text, String pattern) {
        if (!cache.containsKey(text)) {
            cache.put(text, new HashSet<String>());
        }
        if (cache.get(text).contains(pattern)) {
            System.out.println("cache hit " + text + " " + pattern);
            return false;
        }
        cache.get(text).add(pattern);
        for (int i=0, j=0; i<text.length() || j<pattern.length(); i++, j++) {
            if (cache.get(text).contains(pattern)) {
                System.out.println("cache hit 2" + text + " " + pattern);
            }
            if (i<text.length() && j<pattern.length()) {
                if (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?') continue;
                else if (pattern.charAt(j) == '*') {
                    int k = 0;
                    while ((i+k) <= text.length() && isMatch(text.substring(i+k), pattern.substring(j+1)) == false) {
                        k++;
                    }
                    return i+k <= text.length();
                } else {
                    return false;
                }
            } else if (j<pattern.length()) {
                if (pattern.charAt(j) == '*') return isMatch("", pattern.substring(j+1));
                else return false;
            } else {
                //last char is '*', the pattern could be empty
                if (pattern.length() > 0 && pattern.charAt(pattern.length()-1) == '*') return true;
                else return false;
            }
        }
        return true;
    }
}
