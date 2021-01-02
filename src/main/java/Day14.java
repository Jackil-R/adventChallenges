import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day14 {

    public static void main(String[] args) {
        HashMap<Integer, String> data = getData();

        long sum = 0L;
        for (String val : data.values()) {
            sum += Long.parseLong(val, 2);
        }

        System.out.println("Sum: " + sum);


    }

    private static HashMap<Integer, String> getData() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        File file = new File("src/main/resource/input14.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            String mask = "";
            while ((text = reader.readLine()) != null) {
                if (text.startsWith("mask")) {
                    mask = text.substring(text.indexOf("=") + 2);
                } else {
                    int address = Integer.parseInt(text.substring(4, text.indexOf("]")));
                    int num = Integer.parseInt(text.substring(text.indexOf("=") + 2));
                    map.put(address, applyMask(num, mask));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ignored) {
            }
        }

        return map;
    }
    private static String applyMask(int n, String mask) {
        String bin = Integer.toBinaryString(n);
        while (bin.length() < 36) {
            bin = "0" + bin;
        }
        String ret = "";
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == 'X') {
                ret += bin.charAt(i);
            } else {
                ret += mask.charAt(i);
            }
        }
        return ret;
    }

}
