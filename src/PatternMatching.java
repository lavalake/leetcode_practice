import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jian1.w on 11/27/18.
 */
public class PatternMatching {
    Map<String, Set<String>> cache = new HashMap<>();
    public boolean isMatchBrutalForce(String text, String pattern) {
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
                    while ((i+k) <= text.length() && isMatchBrutalForce(text.substring(i+k), pattern.substring(j+1)) == false) {
                        k++;
                    }
                    return i+k <= text.length();
                } else {
                    return false;
                }
            } else if (j<pattern.length()) {
                if (pattern.charAt(j) == '*') return isMatchBrutalForce("", pattern.substring(j+1));
                else return false;
            } else {
                //last char is '*', the pattern could be empty
                if (pattern.length() > 0 && pattern.charAt(pattern.length()-1) == '*') return true;
                else return false;
            }
        }
        return true;
    }
    public boolean isMatchingDp(String text, String pattern) {
        boolean[][] dp = new boolean[text.length()+1][pattern.length()+1];

        dp[0][0] = true;
        //this is for text=""
        for (int i=1; i<=pattern.length(); i++) {
            if (pattern.charAt(i-1) == '*') dp[0][i] = dp[0][i-1];
        }
        for (int i=0; i<text.length(); i++) {
            for (int j=0; j<pattern.length(); j++) {
                if (pattern.charAt(j) == '?' || text.charAt(i) == pattern.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j];
                } else if (pattern.charAt(j) == '*') {
                    dp[i+1][j+1] = dp[i+1][j] || dp[i][j];
                } else {
                    dp[i+1][j+1] = false;
                }
            }
        }
        return dp[text.length()][pattern.length()];
    }
}
