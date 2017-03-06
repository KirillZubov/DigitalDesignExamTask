package main.java;

import java.io.*;
import java.util.*;

/**
 * Created by KirillZubov on 3/6/2017.
 */
public class SortFile {

    private List<String> str = new ArrayList<>();

    private static boolean process(String source, String target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException();
        }
        try {
            SortFile sf = new SortFile();
            sf.read(source);
            sf.sort();
            sf.write(target);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void read(String source) throws IOException {
        try {
            String path = "src\\main\\resources\\";
            String fullPath = path+source;
            File file = new File(fullPath);
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

    private void sort() {
        Collections.sort(str);
    }

    private void write(String target) {
        try {
            String path = "src\\main\\resources\\";
            String fullPath = path+target;
            File fout = new File(fullPath);
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
        SortFile.process("a.txt", "b.txt");

    }
}
