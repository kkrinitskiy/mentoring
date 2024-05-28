package stuff.lambda;

public class MyStringOps {
    public String reverse(String str){
        if(str.isBlank() || str == null){
            return "";
        }

        String result = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}
