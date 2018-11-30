public class RegularExpressionMatching {
    public int[][] cache;
    public boolean isMatch(String text, String pattern) {
        boolean firstMatch = false;
        if (pattern.length() == 0) return text.length() == 0;
        //current char match,
        if (text.length() != 0 && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.')) {
            firstMatch = true;
        }
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') { //next is *
            return ((firstMatch && isMatch(text.substring(1), pattern)) || isMatch(text, pattern.substring(2)));
        } else { // next is not *
            return (firstMatch && isMatch(text.substring(1), pattern.substring(1)));
        }

    }
    public boolean isMatchDp(String text, String pattern) {
        boolean[][] dp = new boolean[text.length()+1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i=text.length(); i>=0; i--) {
            for (int j=pattern.length()-1; j>=0; j--) {
                boolean firstMatch = (i<text.length()) && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');
                if (j < pattern.length()-1 && pattern.charAt(j+1) == '*') {
                    dp[i][j] = dp[i][j+2] || (firstMatch && dp[i+1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
