import models.Instruction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8 {

    public static void main(String[] args) {
        List<Instruction> data = getData();
        System.out.println(getAccumulatorEx1(data));
        System.out.println(getAccumulatorEx2(data));
    }

    private static int getAccumulatorEx2(List<Instruction> data) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getOperation().equals("nop") || (data.get(i).getOperation().equals("jmp") && data.get(i).getSignedNumber() != 0)) {
                ArrayList<Instruction> modified = new ArrayList(data);
                switch (data.get(i).getOperation()) {
                    case "nop":
                        modified.set(i, new Instruction("jmp", data.get(i).getSignedNumber()));
                    case "jmp":
                        modified.set(i, new Instruction("nop", data.get(i).getSignedNumber()));
                }
                Integer tryExecuteAnswer = tryExecute(modified);
                if (tryExecuteAnswer != null) {
                    return tryExecuteAnswer;
                }
            }
        }
        return -1;

    }


    public static Integer tryExecute(ArrayList<Instruction> data) {
        int[] counter = new int[data.size()];
        int accumulator = 0;
        for (int i = 0; i < data.size(); i++) {
            Instruction inc = data.get(i);
            if (inc.getOperation().equalsIgnoreCase("nop") && !checkIfInstructionRunSecondTime(counter, i)) {
                continue;
            } else if (inc.getOperation().equalsIgnoreCase("acc") && !checkIfInstructionRunSecondTime(counter, i)) {
                accumulator += inc.getSignedNumber();
            } else if (inc.getOperation().equalsIgnoreCase("jmp") && !checkIfInstructionRunSecondTime(counter, i)) {
                i += inc.getSignedNumber() - 1;
            } else {
                return null;
            }
        }
        return accumulator;
    }

    private static Integer getAccumulatorEx1(List<Instruction> data) {
        int[] counter = new int[data.size()];
        int accumulator = 0;
        for (int i = 0; i < data.size(); i++) {
            Instruction inc = data.get(i);
            if (inc.getOperation().equalsIgnoreCase("nop") && !checkIfInstructionRunSecondTime(counter, i)) {
                continue;
            } else if (inc.getOperation().equalsIgnoreCase("acc") && !checkIfInstructionRunSecondTime(counter, i)) {
                accumulator += inc.getSignedNumber();
            } else if (inc.getOperation().equalsIgnoreCase("jmp") && !checkIfInstructionRunSecondTime(counter, i)) {
                i += inc.getSignedNumber() - 1;
            } else {
                break;
            }
        }
        return accumulator;
    }

    private static boolean checkIfInstructionRunSecondTime(int[] counter, int i) {
        if (counter[i] == 0) {
            counter[i] = 1;
            return false;
        } else {
            return true;
        }
    }

    private static List<Instruction> getData() {
        List<Instruction> list = new ArrayList<>();
        File file = new File("src/main/resource/input8.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            StringBuilder concatenatePassport = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                String[] split = text.split(" ");
                list.add(new Instruction(split[0], Integer.parseInt(split[1])));
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
