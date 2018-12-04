import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(num, result, 0, target, 0, 0, new StringBuilder());
        return result;
    }
    private void dfs(String num, List<String> result, int index, int target, long sum, long pre, StringBuilder exp) {
        if (num.length() == index && sum == target) {
            result.add(exp.toString());
            return;
        }
        if (num.length() <= index) return;
        for (int i=index; i<num.length(); i++) {

            String operand = num.substring(index, i+1);
            long cur = Long.valueOf(operand);
            if (index == 0){
                dfs(num, result, i+1, target, cur, cur, exp.append(operand));
                exp.delete(exp.length()-operand.length(), exp.length());
            }
            else {
                dfs(num, result, i+1, target, sum+cur, cur, exp.append("+"+operand));
                exp.delete(exp.length()-operand.length()-1, exp.length());
                dfs(num, result, i+1, target, sum-cur, -cur, exp.append("-"+operand));
                exp.delete(exp.length()-operand.length()-1, exp.length());
                dfs(num, result, i+1, target, sum-pre+(pre*cur), pre*cur, exp.append("*"+operand));
                exp.delete(exp.length()-operand.length()-1, exp.length());
            }
            if (num.charAt(index) == '0') break;
        }
    }
}
