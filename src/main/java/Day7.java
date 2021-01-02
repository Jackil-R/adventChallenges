import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7 {

    public static void main(String[] args) {
        String bag = "shiny gold bag";
        Map<String, Map<String, Integer>> data = getData();
        int sum = getNumberOfBags(data,bag);
        System.out.println(sum);

    }

    private static int getNumberOfBags(Map<String, Map<String, Integer>> data, String bag) {
        int numberOfbags=0;
        List<String> containingBags = new ArrayList<>();

        data.forEach((k,v) -> {
            if(v.keySet().stream().filter(str -> str.equalsIgnoreCase(bag)).count() >= 1)
                containingBags.add(k);
        });

        data.keySet().removeAll(containingBags);
        numberOfbags+=containingBags.size();
        Set<String> bagContainingBags = new HashSet<>(containingBags);
        while(bagContainingBags.size()!=0){
            bagContainingBags = getBagContainingBags(data,bagContainingBags);
            data.keySet().removeAll(bagContainingBags);
            numberOfbags+=bagContainingBags.size();
        }

        return numberOfbags;
    }

    private static Set<String> getBagContainingBags(Map<String, Map<String, Integer>> data, Set<String>  bagContainingBags) {
        Set<String> temp = new HashSet<>();
        bagContainingBags.forEach(bag -> {
            data.forEach((k,v) -> {
                if(v.keySet().stream().filter(str -> str.equalsIgnoreCase(bag)).count() >= 1)
                    temp.add(k);
            });
        });
        return temp;
    }

    private static Map<String, Map<String, Integer>> getData() {
        Map<String,Map<String, Integer>> list = new HashMap<>();
        File file = new File("src/main/resource/input7.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null) {
                String[] split = text.split(" contain ");
                String[] elements = split[1].substring(0,split[1].length()-1).split(", ");
                Map<String, Integer> bagToNumber = new HashMap<>();
                for(String e : elements){
                    if(!e.equalsIgnoreCase("no other bags")){
                        String bagName = e.substring(2).replace("bags","bag");
                        int number = Integer.parseInt(e.substring(0,1));
                        bagToNumber.put(bagName, number);
                    }
                }
                list.put(split[0].replace("bags","bag"),bagToNumber);

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

//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map.Entry;
//
//public class Day7 {
//
//    public static void main(String[] args) throws Exception {
//        final List<String> input = Files.readAllLines(Path.of("src/main/resource/input7.txt"));
//        final HashMap<String, Bag> map = new HashMap<>();
//        for (String line : input) {
//            final String[] kv = line.split(" bags contain ");
//            final String[] values = kv[1].substring(0, kv[1].length() - 1).split(", ");
//            if (!map.containsKey(kv[0])) {
//                map.put(kv[0], new Bag(kv[0]));
//            }
//
//            for (String v : values) {
//                if (v.equals("no other bags")) {
//                    continue;
//                }
//
//                final int count = Integer.parseInt(v.substring(0, v.indexOf(" ")));
//                final String name = v.substring(v.indexOf(" ") + 1, v.lastIndexOf(" "));
//                if (!map.containsKey(name)) {
//                    map.put(name, new Bag(name));
//                }
//
//                map.get(kv[0]).addBags(map.get(name), count);
//            }
//        }
//
//        part1(map);
//        part2(map);
//    }
//
//    private static void part1(HashMap<String, Bag> bagMap) {
//        int total = 0;
//        for (Bag b : bagMap.values()) {
//            total += b.containsBag("shiny gold") ? 1 : 0;
//        }
//
//        System.out.println(total);
//    }
//
//    private static void part2(HashMap<String, Bag> bagMap) {
//        System.out.println(bagMap.get("shiny gold").countBags());
//    }
//
//    private static class Bag {
//        private final String name;
//
//        private final HashMap<Bag, Integer> bags = new HashMap<>();
//
//        public Bag(String name) {
//            this.name = name;
//        }
//
//        public void addBags(Bag bag, int count) {
//            this.bags.put(bag, count);
//        }
//
//        public boolean containsBag(String name) {
//            if (this.name.equals(name)) {
//                return false;
//            }
//
//            for (Bag b : bags.keySet()) {
//                if (b.name.equals(name) || b.containsBag(name)) {
//                    return true;
//                }
//            }
//
//            return false;
//        }
//
//        public int countBags() {
//            int total = 0;
//            for (Entry<Bag, Integer> e : this.bags.entrySet()) {
//                total += e.getValue() + e.getValue() * e.getKey().countBags();
//            }
//
//            return total;
//        }
//    }
//}
