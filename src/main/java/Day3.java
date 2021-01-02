import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) {
       part1(getData(),1,3);
       part1(getData(),1,1);
       part1(getData(),1,5);
       part1(getData(),1,7);
       part1(getData(),2,1);

        int i = 33;
        int j = 31;
        int k;

        System.out.println("i is " + i);
        System.out.println("j is " + j);

        k = i%j;
        System.out.println("i%j is " + k);
    }

    private static void part1(List<String> data, int down, int right) {
//        int count = 0;
//        int rightCount = 0;
//        System.out.println(data.get(0));
//        for(int i =0; i<data.size()-1; i++){
//            int preCount = rightCount;
//            rightCount+=3;
//            StringBuilder currentString = new StringBuilder(data.get(i));
//            StringBuilder nextString = new StringBuilder(data.get(i+1));
//
//            //.....#.........#...#..##....#..
//            //.#........#...#........#.......
//
//            if(!(rightCount >= currentString.length())){
//                if( nextString.charAt(rightCount) == '#'){
//                    nextString.setCharAt(rightCount,'X');
//                    count++;
//                }else{
//                    nextString.setCharAt(rightCount,'0');
//                }
//            }
//            System.out.println(nextString + " " +preCount +"+3= " + rightCount);
//            index = (index + right) % currentString.length();
//        }
//
//        System.out.println(count);

        int trees = 0;
        int index = 0;
        int columns = data.get(0).length();

        for(int i = 0; i < data.size(); i += down){
            StringBuilder currentString = new StringBuilder(data.get(i));
            if(data.get(i).charAt(index) == '#'){
                trees ++;
                currentString.setCharAt(index,'X');
            }else{
                currentString.setCharAt(index,'0');
            }
            int i1 = index + right;
//            System.out.print(currentString + " " + i1 + " % " + columns + " = " );
            index = (index + right) % columns;
//            System.out.print(index);
//            System.out.println();
        }
        System.out.println(trees);
    }


    private static List<String> getData(){
        List<String> list = new ArrayList<>();
        File file = new File("src/main/resource/input3.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(text);
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
