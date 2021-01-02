import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {

    public static void main(String[] args) {
        List<Integer> data = getData();
        data.add(0);
        data.add(3+Collections.max(data));
        int [] counter = {0,0,0};
        Collections.sort(data);
        int difference = getDifference(data, counter);
        System.out.println(difference);
        System.out.println(getOutlets(data,difference));
    }

    private static int getOutlets(List<Integer> data, int difference) {
        List<Integer> l = new ArrayList<>();
        int outletCounter = 0;

        for(int i = 0; i<data.size()-2; i++){
            int diff = Math.abs(data.get(i) - data.get(i+1))- 3;
            int diff2 = Math.abs(data.get(i+1) - data.get(i+2))-3;
            if(!(diff >= 0) && diff2!=0 ){
                l.add(data.get(i+1));
            }
        }
        List<Integer> collect = data.stream().filter(l::contains).collect(Collectors.toList());
        List<List<Integer>> allCombinations = getAllCombinations(collect);
        System.out.println(collect);

        for (List<Integer> a : allCombinations){
            List<Integer> collect1 = data.stream().filter(num -> !l.contains(num)).collect(Collectors.toList());
            collect1.addAll(a);
            Collections.sort(collect1);
            if(getDifference(collect1, new int[] {0,0,0})!=0){
                outletCounter++;

            }
        }
        List<Integer> collect1 = data.stream().filter(num -> !l.contains(num)).collect(Collectors.toList());
        System.out.println(collect1);
        if(getDifference(collect1, new int[] {0,0,0}) !=0){
            outletCounter++;
        }

        return outletCounter;
    }

    public static List<List<Integer>> getAllCombinations(List<Integer> elements) {
        List<List<Integer>> combinationList = new ArrayList<>();
        for ( long i = 1; i < Math.pow(2, elements.size()); i++ ) {
            List<Integer> list = new ArrayList<>();
            for ( int j = 0; j < elements.size(); j++ ) {
                if ( (i & (long) Math.pow(2, j)) > 0 ) {
                    list.add(elements.get(j));
                }
            }
            combinationList.add(list);
        }
        return combinationList;
    }

    private static int getDifference(List<Integer> data, int [] counter) {

        for(int i =0; i<data.size()-1; i++){

            int diff = Math.abs(data.get(i)-data.get(i+1));
            if(diff==1){
                counter[0]+=1;
            }else if(diff==2){
                counter[1]+=1;
            }else if(diff==3) {
                counter[2]+=1;
            }else{
                return 0;
            }

        }
        return counter[0]*counter[2];
    }



    private static List<Integer> getData() {
        List<Integer> list = new ArrayList<>();
        File file = new File("src/main/resource/input10.txt");
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
