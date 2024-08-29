package multithreading.task_05;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static multithreading.task_05.UtilTextMessagesStorage.*;

public class Main {
    private final static Scanner s = new Scanner(System.in);
    private static BigDecimal firstNumber;
    private static BigDecimal secondNumber;
    private static final ConcurrentLinkedDeque<Expression> expressions = new ConcurrentLinkedDeque<>();


    public static void main(String[] args) {

        System.out.println(INTRODUCE);

        while (true) {

            try {
                System.out.printf(EXPRESSIONS_IN_QUEUE, expressions.size());
                System.out.println(ADD_FIRST_NUMBER_MSG);

                firstNumber = new BigDecimal(checkInput(s.nextLine()));

                System.out.println(ADD_SECOND_NUMBER_MSG);

                secondNumber = new BigDecimal(checkInput(s.nextLine()));

                System.out.println(ADD_OPERATION_TYPE);

                ExpressionType type = checkType(s.nextLine());

                expressions.add(new Expression(firstNumber, secondNumber, type));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }



    }

    private static ExpressionType checkType(String s) {
        s = checkInput(s);

        switch (s){
            case "1" -> {
                return ExpressionType.ADD;
            }
            case "2" -> {
                return ExpressionType.SUB;
            }
            case "3" -> {
                return ExpressionType.MUL;
            }
            case "4" -> {
                return ExpressionType.DIV;
            }
            default -> throw new RuntimeException("Неверный формат ввода операции. Попробуйте снова");

        }
    }

    public static String checkInput(String input){
        if(input.equalsIgnoreCase("exit")){
            System.exit(0);
        }

        if(input.equalsIgnoreCase("execute")){
            if(expressions.isEmpty()){
                throw new RuntimeException(EMPTY_QUEUE);
            }
            executeQueue();
        }

        if(input.equalsIgnoreCase("clean")){
            expressions.clear();
            throw new RuntimeException(CLEAR_QUEUE);
        }

        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (!Character.isDigit(aChar)
                    && aChar != ' '
                    && aChar != ','
                    && aChar != '.') {
                throw new NumberFormatException(String.format(INCORRECT_SYMBOL_MSG, aChar));
            }
        }

        input = input.strip()
                .replaceAll(",",".")
                .replaceAll("\\s+","");

        return input;
    }

    public static void executeQueue(){
        expressions.forEach(Thread::start);

        for (Expression expression : expressions) {
            try {
                expression.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        expressions.clear();

        throw new RuntimeException(START);

    }

}
