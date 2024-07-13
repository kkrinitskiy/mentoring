package multithreading.task_05;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Expression extends Thread{
    private BigDecimal a;
    private BigDecimal b;
    private ExpressionType type;
    private BigDecimal result;
    private String op;

    public Expression(BigDecimal a, BigDecimal b, ExpressionType type) {
        if(b.equals(new BigDecimal(0)) && type.equals(ExpressionType.DIV)){
            throw new RuntimeException("Делить на ноль нельзя!");
        }
        this.a = a;
        this.b = b;
        this.type = type;

    }

    @Override
    public void run() {
        switch (type){
            case ADD -> {
                result = a.add(b);
                op = "+";
            }
            case SUB -> {
                result = a.subtract(b);
                op = "-";
            }
            case MUL -> {
                result = a.multiply(b);
                op = "*";
            }
            case DIV -> {
                result = a.divide(b, 20, RoundingMode.HALF_UP);
                op = "/";
            }
        }

        printResult();
    }

    public void printResult(){
        System.out.printf("%s %s %s = %s\n", a, op, b, result);
    }


}
