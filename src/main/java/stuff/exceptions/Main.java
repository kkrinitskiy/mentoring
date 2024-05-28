package stuff.exceptions;

import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {

        doWork();
    }

    public static void doWork(){
        System.out.println("do some work");
        doInnerWork();
        printST(Thread.currentThread().getStackTrace());
    }

    public static void doInnerWork(){
        System.out.println("Some inner work");
        printST(Thread.currentThread().getStackTrace());
    }

    public static void printST(StackTraceElement[] e){
        System.out.println("#####################################");
        for (StackTraceElement el : e) {
            System.out.println(MessageFormat.format("Метод {0}, был вызван в строке №{1}, в классе {2}, в файле {3}", el.getMethodName(), el.getLineNumber(),
                    el.getClassName(), el.getFileName()));
        }

    };
}

