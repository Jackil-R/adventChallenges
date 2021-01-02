import models.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day9 {

    private final static int preamble = 25;

    public static void main(String[] args) {
        final List<Long> input = getData();
        long invalid = part1(input);
        part2(input, invalid);

    }

    private static void part2(List<Long> input, long invalid) {
        System.out.println(getWeakness(input,invalid));
    }

    private static long getWeakness(List<Long> list, long invalid) {
        Long key = 0L;
        for(int i = 0; i < list.size()-preamble; i++){
            List<Long> sublist = list.subList(i, i + preamble);
            Long[] integers = sublist.toArray(Long[]::new);
            Pair contiguousSet = findContiguousSet(integers, invalid);
            long sum = Arrays.asList(integers).subList(contiguousSet.getLeft(), contiguousSet.getRight()+1).stream().mapToLong(Long::longValue).sum();
            OptionalLong max = Arrays.asList(integers).subList(contiguousSet.getLeft(), contiguousSet.getRight() + 1).stream().mapToLong(Long::longValue).max();
            OptionalLong min = Arrays.asList(integers).subList(contiguousSet.getLeft(), contiguousSet.getRight() + 1).stream().mapToLong(Long::longValue).min();
            if(sum == invalid){
               return min.getAsLong() + max.getAsLong();
            }
        }
        return key;
    }

    private static Pair findContiguousSet(Long[] integers, Long sum) {
        long curr_sum;
        int i, j;
        int n = integers.length;
        Pair pair = new Pair();
        for (i = 0; i < integers.length; i++) {
            curr_sum = integers[i];
            for (j = i + 1; j <= n; j++) {
                if (curr_sum == sum) {
                    int p = j - 1;
                    pair.setLeft(i);
                    pair.setRight(p);
                    return pair;
                }
                if (curr_sum > sum || j == n)
                    break;
                curr_sum = curr_sum + integers[j];
            }
        }
        return pair;
    }

    private static long part1(List<Long> list) {
        System.out.println(getKey(list));
        return getKey(list);
    }

    private static long getKey(List<Long> list) {
        Long key = 0L;
        for(int i = 0; i < list.size()-preamble; i++){
            List<Long> sublist = list.subList(i, i + preamble);
            Long[] integers = sublist.toArray(Long[]::new);
            Long sum = list.get(i+preamble);
            if(findPair(integers,sum)){
                continue;
            }else{
                key=sum;
                break;
            }
        }
        return key;
    }

    private static boolean findPair(Long[] arr, Long n) {
        int l, r;
        Arrays.sort(arr);
        l = 0;
        r = arr.length - 1;
        while (l < r) {
            if (arr[l] + arr[r] == n)
                return true;
            else if (arr[l] + arr[r] < n)
                l++;
            else
                r--;
        }
        return false;
    }

    private static List<Long> getData() {
        List<Long> list = new ArrayList<>();
        File file = new File("src/main/resource/input9.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            StringBuilder concatenatePassport = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                list.add(Long.parseLong(text));
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
