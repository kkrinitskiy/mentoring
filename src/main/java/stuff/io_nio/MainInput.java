package stuff.io_nio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainInput {
    public static void main(String[] args) {
        String src = "src/main/java/stuff/io_nio/text.txt";
        String dest = "src/main/java/stuff/io_nio/text1.txt";

        try(FileReader reader = new FileReader(src);
            FileWriter writer = new FileWriter(dest)){


//            поток который читает из начследников класса Reader, буфферизует данные и выдает по необходимости
            while (reader.ready()) {
                BufferedReader bufferedReader = new BufferedReader(reader);
                bufferedReader.lines().forEach(System.out::println);

                String line = bufferedReader.readLine();
                System.out.println(line);
            }

//              чтение потоком из файла
            FileInputStream inputStream = new FileInputStream(src);
//              чтение потоком из потока
            InputStreamReader reader1 = new InputStreamReader(inputStream);




//              чтение всего файла построково
            List<String> strings = Files.readAllLines(Paths.get(src));
            for (String string : strings) {
                System.out.println(string);
            }

//              чтение и запись побайтово
            char[] buffer = new char[1024];
            while (reader.ready()){
                int real = reader.read(buffer);
                writer.write(buffer, 0, real);
            }

        }catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }catch (IOException e){
            System.out.println("IO exception");
        }
    }
}
