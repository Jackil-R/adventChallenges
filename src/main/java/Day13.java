import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

    private static int EARLIEST_TIME = 0;

    public static void main(String[] args) {
        List<Integer> data = getData();
        System.out.println(part1(data));
        System.out.println(part2(data));


    }

    private static int part2(List<Integer> data) {

        return 0;
    }

    private static int part1(List<Integer> data) {
        List<Integer> missedTMultipleTimes = data.stream().map(i -> {
            int earliestTime = EARLIEST_TIME%i;
            int a = EARLIEST_TIME-earliestTime;
            return a/i;
        }).collect(Collectors.toList());
        List<Integer> nextMultiplesTimes = new ArrayList<>();

        int pos = 0;
        int lowestDiff = Integer.MAX_VALUE;
        for(int i = 0; i < missedTMultipleTimes.size(); i++){
            int nextMultiple = data.get(i) * (missedTMultipleTimes.get(i) + 1);
            nextMultiplesTimes.add(nextMultiple);
            if(EARLIEST_TIME-nextMultiple<0){
                int diff = Math.abs(EARLIEST_TIME - nextMultiple);
                if(diff<lowestDiff){
                    lowestDiff=diff;
                    pos=i;
                }

            }
        }
        int i = nextMultiplesTimes.get(pos) - EARLIEST_TIME;
        System.out.println(nextMultiplesTimes.get(pos)-EARLIEST_TIME);
        return data.get(pos)*i;
    }

    private static List<Integer> getData() {
        List<Integer> list = new ArrayList<>();
        File file = new File("src/main/resource/input13.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            StringBuilder concatenatePassport = new StringBuilder();
            EARLIEST_TIME = Integer.parseInt(reader.readLine());
            text = reader.readLine();
            list = Arrays.stream(text.split(",")).filter(i -> !i.equals("x")).map(Integer::parseInt).collect(Collectors.toList());

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
