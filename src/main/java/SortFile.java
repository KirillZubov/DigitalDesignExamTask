package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by KirillZubov on 3/6/2017.
 */
public class SortFile {

    private static List<String> str = new ArrayList<>();

    public boolean process(String source, String target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException();
        }
        try {
            read(source);
            sort();
            write(target);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void read(String source) throws IOException {
        try {
            String path = "src\\main\\resources\\";
            String fullPath = path + source;
            File file = new File(fullPath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                str.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }

    }

    private static void sort() {
        Collections.sort(str);
    }

    private static void write(String target) {
        try {
            String path = "src\\main\\resources\\";
            String fullPath = path + target;
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

    public void find(String key) {
        System.out.println("Found line: ");
        for (String s : str) {
            if (s.contains(key)) {
                System.out.println(" " + s);
            }
        }
        System.out.println();
    }

    public void findRV(String key) {
        String pattern = ".*" + key + ".*";
        System.out.println("Found line: ");
        for (String s : str) {
            if (s.matches(pattern)) {
                System.out.println(" " + s);
            }
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        SortFile sf = new SortFile();
        sf.process("a.txt", "b.txt");
        sf.find("a");
        sf.findRV("a");

    }
}
