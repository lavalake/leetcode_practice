/**
 * Created by jian1.w on 11/27/18.
 */
public class Runner {
    public static void main(String[] args) {
        PatternMatching pm = new PatternMatching();
        //aba a*a
        //aba a**a
        //aba a*a?
        //abaa ?*a?a?
        String text = "";
        String pattern = "**";
        boolean ret = pm.isMatchBrutalForce(text, pattern);
        System.out.println("isMatching " + ret);
        ret = pm.isMatchingDp(text, pattern);
        System.out.println(ret);
    }
}
