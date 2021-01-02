import models.Range;
import models.Seat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    private final static double ROWS = 127;
    private final static double COLUMNS = 7;
    private final static double EIGHT = 8;
    private final static String ROW_CALCULATE = "ROW_CALCULATE";
    private final static String COLUMN_CALCULATE = "COLUMN_CALCULATE";


    public static void main(String[] args) {
        List<Seat> seats = calculateHighestSeatId(getData());
        System.out.println(seats.get(seats.size()-1).getSeatId());
        getMySeat(seats);

    }

    private static void getMySeat(List<Seat> seats) {
        List<Double> ids = new ArrayList<>();
        for (Seat seat : seats) {
            ids.add(seat.getSeatId());
        }

        List<Double> candidates = new ArrayList<>();
        for (int i = 1; i < ids.size() - 2; i++) {
            Double curr = ids.get(i);
            Double next = ids.get(i+1);
            if (next - curr == 2) {
                candidates.add(curr+1);
            }
        }

        System.out.println("Missing seat is: " + candidates);
    }


    private static List<Seat> calculateHighestSeatId(List<String> data) {

        List<Seat> seats = data.stream().map(binary -> {
            int count = 0;
            double row = getRow(binary.substring(0, binary.length() - 3), count, new Range(0,ROWS), ROW_CALCULATE);
            double column = getColumn(binary.substring(binary.length() - 3), count, new Range(0,COLUMNS), COLUMN_CALCULATE);
            double seatID = (row * EIGHT) + column;

            return new Seat(row, column, seatID);

        }).collect(Collectors.toList());

        seats.sort(Comparator.comparing(Seat::getSeatId));

        return seats;


    }

    private static double getRow(String binary, int count, Range range, String calculation) {
        return getAlgorithm(binary,count,range, calculation);
    }


    private static double getColumn(String binary, int count, Range range, String calculation) {
        return getAlgorithm(binary,count,range, calculation);
    }

    private static double getAlgorithm(String binary, int count, Range range, String calculation) {
        char[] c = binary.toCharArray();

        if(calculation.equalsIgnoreCase(ROW_CALCULATE)){
            if (count == c.length - 1) {
                if (c[count] == 'F') {
                    return range.getLow();
                } else {
                    return range.getHigh();
                }
            } else if (c[count] == 'F') {
                count++;
                range.setLow(range.getLow());
                double diff = range.getHigh() - range.getLow();
                range.setHigh(Math.floor(range.getLow() + (diff / 2)));
                return getRow(binary, count, range, ROW_CALCULATE);
            } else if (c[count] == 'B') {
                count++;
                range.setHigh(range.getHigh());
                double diff = range.getHigh() - range.getLow();
                range.setLow(Math.ceil(range.getLow() + (diff / 2)));
                return getRow(binary, count, range, ROW_CALCULATE);
            } else {
                return getRow(binary, count, range, ROW_CALCULATE);
            }
        }else{
            if (count == c.length - 1) {
                if (c[count] == 'L') {
                    return range.getLow();
                } else {
                    return range.getHigh();
                }
            } else if (c[count] == 'L') {
                count++;
                range.setLow(range.getLow());
                double diff = range.getHigh() - range.getLow();
                range.setHigh(Math.floor(range.getLow() + (diff / 2)));
                return getRow(binary, count, range, COLUMN_CALCULATE);
            } else if (c[count] == 'R') {
                count++;
                range.setHigh(range.getHigh());
                double diff = range.getHigh() - range.getLow();
                range.setLow(Math.ceil(range.getLow() + (diff / 2)));
                return getRow(binary, count, range, COLUMN_CALCULATE);
            } else {
                return getRow(binary, count, range, COLUMN_CALCULATE);
            }
        }

    }

    private static List<String> getData() {
        List<String> list = new ArrayList<>();
        File file = new File("src/main/resource/input5.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            StringBuilder concatenatePassport = new StringBuilder();
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
