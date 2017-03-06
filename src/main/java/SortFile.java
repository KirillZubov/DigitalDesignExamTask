package main.java;

import java.io.*;
import java.util.*;

/**
 * Created by KirillZubov on 3/6/2017.
 */
public class SortFile {

    List<String> str = new ArrayList<>();


    private void read(String source) throws IOException {
        try {
            File file = new File(source);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                str.add(line);
            }
            fileReader.close();
        } catch (IOException e){
            e.fillInStackTrace();
        }

    }

    void dublicate() {
        Collections.sort(str);

    }

    private void write(String target) {
        try {
            File fout = new File(target);
            FileOutputStream fos = new FileOutputStream(fout);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            for (String s : str) {
                osw.write(s + '\n');
            }

            osw.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SortFile sortFile = new SortFile();
        sortFile.read("src\\main\\resource\\a.txt");
        System.out.println(sortFile.str);
    }
}
