import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {

    public static void main(String[] args) {
        final List<List<String>> data = getData();
        System.out.println(part1(data));
    }

    private static int part1(List<List<String>> data) {

        List<List<String>> lists = calculateSeat(data);
        List<List<String>> previousList = new ArrayList<>();
        boolean equalLists = lists.size() == 0 && lists.containsAll(previousList);
        while (!equalLists) {
            previousList = lists;
            lists = calculateSeat(lists);
            equalLists = lists.size() == previousList.size() && lists.containsAll(previousList);
        }
        return lists.stream().map(l -> Collections.frequency(l, "#")).collect(Collectors.toList()).stream().mapToInt(Integer::intValue).sum();
    }

    private static List<List<String>> calculateSeat(final List<List<String>> data) {
        // # - occupied seat
        // L - empty seat

        List<List<String>> copy = cloneList(data);
        for (int i = 0; i < data.size(); i++) {
            List<String> currentRow = data.get(i);
            for (int j = 0; j < currentRow.size(); j++) {
                List<String> topRow;
                List<String> bottomRow;
                String currentPosition = currentRow.get(j);
                String topLeft = "";
                String topRight = "";
                String left = "";
                String right = "";
                String down = "";
                String up = "";
                String bottomLeft = "";
                String bottomRight = "";

                if (i == 0) {
                    bottomRow = data.get(i + 1);
                    if (j != 0) {
                        left = currentRow.get(j - 1);
                        bottomLeft = bottomRow.get(j - 1);
                    }
                    down = bottomRow.get(j);
                    if (j != currentRow.size() - 1) {
                        right = currentRow.get(j + 1); ;
                        bottomRight = bottomRow.get(j + 1);
                    }
                } else if (i == data.size() - 1) {
                    topRow = data.get(i - 1);
                    if (j != 0) {
                        left = currentRow.get(j - 1);
                        topLeft = topRow.get(j - 1);
                    }
                    up = topRow.get(j);
                    if (j != currentRow.size() - 1) {
                        right = currentRow.get(j + 1);
                        topRight = topRow.get(j + 1);
                    }
                } else {
                    bottomRow = data.get(i + 1);
                    topRow = data.get(i - 1);
                    if (j != 0) {
                        topLeft = topRow.get(j - 1);
                        left = currentRow.get(j - 1);
                        bottomLeft = bottomRow.get(j - 1);
                    }

                    if (j != currentRow.size() - 1) {
                        topRight = topRow.get(j + 1);
                        right = currentRow.get(j + 1);
                        bottomRight = bottomRow.get(j + 1);
                    }
                    up = topRow.get(j);
                    down = bottomRow.get(j);
                }

                if (currentPosition.equalsIgnoreCase("L") && checkNoOccupiedSeatsAdjacent(topLeft, topRight, left, right, down, up, bottomLeft, bottomRight)) {
                    copy.get(i).set(j, "#");
                } else if (currentPosition.equalsIgnoreCase("#")) {
                    List<String> seats = new ArrayList<>();
                    seats = Arrays.asList(left, topLeft, up, topRight, right, bottomRight, down, bottomLeft);
                    if (checkOccupiedSeats(seats)) {
                        copy.get(i).set(j, "L");
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        print(copy);
        return copy;
    }

    private static boolean checkNoOccupiedSeatsAdjacent(String topLeft, String topRight, String left, String right, String down, String up, String bottomLeft, String bottomRight) {
        return (topLeft.equals("L") || topLeft.equals(".") || topLeft.equals("")) &&
                (topRight.equals("L") || topRight.equals(".") || topRight.equals("")) &&
                (left.equals("L") || left.equals(".") || left.equals("")) &&
                (right.equals("L") || right.equals(".") || right.equals("")) &&
                (down.equals("L") || down.equals(".") || down.equals("")) &&
                (up.equals("L") || up.equals(".") || up.equals("")) &&
                (bottomLeft.equals("L") || bottomLeft.equals(".") || bottomLeft.equals("")) &&
                (bottomRight.equals("L") || bottomRight.equals(".") || bottomRight.equals(""));
    }

    private static List<List<String>> cloneList(List<List<String>> data) {
        List<List<String>> a = new ArrayList<>();
        for (List<String> list : data) {
            a.add(new ArrayList<>(list));
        }
        return a;
    }

    private static void print(List<List<String>> copy1) {
        for (List<String> a : copy1) {
            System.out.println(a);
        }
        System.out.println();

    }

    private static boolean checkOccupiedSeats(List<String> seats) {
        long count = seats.stream().filter(str -> str.equals("#")).count();
        return count >= 4;
    }

    private static List<List<String>> getData() {
        List<List<String>> list = new ArrayList<>();
        File file = new File("src/main/resource/input11.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null) {
                String[] arr = text.split("");
                List<String> strings = Arrays.asList(arr);
                list.add(strings);
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

//                if(i==0){
//                    if(currentPosition.equalsIgnoreCase("L") && (
//                            (left.equals("L") || left.equals(".") || left.equals("")) &&
//                            (bottomLeft.equals("L") || bottomLeft.equals(".") || bottomLeft.equals("")) &&
//                            (down.equals("L") || down.equals(".")) &&
//                            (bottomRight.equals("L") || bottomRight.equals(".") || bottomRight.equals("")) &&
//                            (right.equals("L") || right.equals(".")||  right.equals("")) ))
//                    {
//                        copy.get(i).set(j,"#");
//                    }else if(currentPosition.equalsIgnoreCase("#")){
//
//                        List<String> seats =  Arrays.asList(left,bottomLeft,down,bottomRight,right);
//                        if(checkOccupiedSeats(seats)){
//                            copy.get(i).set(j,"L");
//                        }else{
//                            continue;
//                        }
//
//                    }else{
//                        continue;
//                    }
//
//
//                }else if(i == data.size()-1){
//                    if(currentPosition.equalsIgnoreCase("L") && (
//                            (topLeft.equals("L") || topLeft.equals(".") || topLeft.equals("")) &&
//                            (topRight.equals("L")|| topRight.equals(".") || topRight.equals("") ) &&
//                            (left.equals("L")|| left.equals(".") ||left.equals("")  ) &&
//                            (right.equals("L") || right.equals(".") || right.equals("")) &&
//                            (up.equals("L") || up.equals(".")) ))
//                    {
//                        copy.get(i).set(j,"#");
//                    }else if(currentPosition.equalsIgnoreCase("#")){
//
//                        List<String> seats =  Arrays.asList(left,topLeft,up,topRight,right);
//                        if(checkOccupiedSeats(seats)){
//                            copy.get(i).set(j,"L");
//                        }else{
//                            continue;
//                        }
//
//                    }else{
//                        continue;
//                    }
//
//                }else{
