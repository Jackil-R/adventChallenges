import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) {

        List<Integer> numbers1 = getNumber().stream().filter(x -> x < 2020).collect(Collectors.toList());
        List<Integer> numbers2 = getNumber().stream().filter(x -> x < 2020).collect(Collectors.toList());
        List<Integer> numbers3 = getNumber().stream().filter(x -> x < 2020).collect(Collectors.toList());

        numbers1.stream().distinct().sorted().forEach(i -> {
            numbers2.stream().distinct().sorted().forEach(j -> {
                numbers3.stream().distinct().sorted().forEach(k -> {
                    if((i+j+k)==2020){
                        System.out.println("2020");
                        System.out.println(i + " " + j + " " + k);
                    }
                });
            });
        });

    }

    private static List<Integer> getNumber(){
        List<Integer> list = new ArrayList<Integer>();
        File file = new File("src/main/resource/input1.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(Integer.parseInt(text));
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

        return list;
    }

}
