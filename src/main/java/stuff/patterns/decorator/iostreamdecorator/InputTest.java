package stuff.patterns.decorator.iostreamdecorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class InputTest {
    public static void main(String[] args){
        int c;
        try(InputStream in =
                    new LowerCaseInputStream(
                    new BufferedInputStream(
                    new FileInputStream(
                            "src/main/java/stuff/patterns/decorator/iostreamdecorator/test.txt")))){

            while ((c = in.read()) >= 0){
                System.out.print((char) c);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
