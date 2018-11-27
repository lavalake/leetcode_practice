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
        boolean ret = pm.isMatch("ababcdssss", "?**a?*a");
        System.out.println("isMatching " + ret);
    }
}
