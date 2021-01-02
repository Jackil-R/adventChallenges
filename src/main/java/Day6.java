import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    public static void main(String[] args) {

        List<List<Set<Character>>> data = getData();
        List<Integer> numberOfPeopleInEachGroup = data.stream().map(list -> {
            Set<Character> set = new HashSet<>();
//            list.forEach(s -> {
//                set.addAll(s);
//
//            });

            final boolean[] flag = {true};
            list.forEach(s -> {
                if(flag[0]){
                    set.addAll(s);
                    flag[0] = false;
                }else{
                    set.retainAll(s);
                }

            });
            return set.size();

        }).collect(Collectors.toList());
        int totalSum = numberOfPeopleInEachGroup.stream().mapToInt(Integer::intValue).sum();
        System.out.println(totalSum);

    }


    private static List<List<Set<Character>>> getData() {
        List<List<Set<Character>>> list = new ArrayList<>();
        File file = new File("src/main/resource/input6.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            List<Set<Character>> people = new ArrayList<>();
            while ((text = reader.readLine()) != null) {
                if(!text.equalsIgnoreCase("")){
                    Set<Character> charsSet = text.chars().mapToObj(i -> (char) i).collect(Collectors.toSet());
                    people.add(charsSet);
                }else{
                    list.add(people);
                    people = new ArrayList<>();
                }
            }
            list.add(people);

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
