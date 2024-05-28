package stuff.io_nio;

import java.io.*;

public class MainOutput {
    public static void main(String[] args) {
        String src = "src/main/java/stuff/io_nio/text.txt";
        String dest = "src/main/java/stuff/io_nio/text2.txt";

//        InputStream - bytes
//        Writer - chars


//        try(FileInputStream inputStream = new FileInputStream(src);
//            FileOutputStream outputStream = new FileOutputStream(dest)){
//
//            byte[] buffer = new byte[1024];
//
//            while (inputStream.available() > 0){
//                int real = inputStream.read(buffer);
//                outputStream.write(buffer, 0, real);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        try(Reader reader = new FileReader(src);
                Writer writer = new FileWriter(dest)){

            char[] buffer = new char[1024];

            while (reader.ready()){
                int read = reader.read(buffer);
                StringBuilder res = new StringBuilder();
                for (int i = 0; i < read; i++) {
                    res.append(buffer[i]);
                }
                writer.append(res);

            }

        }catch (Exception e){

        }




    }
}
