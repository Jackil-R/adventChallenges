import models.Action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day12 {

    private static String FACE = "EAST";
    private static int EAST_COUNTER = 0;
    private static int NORTH_COUNTER = 0;
    private static int WEST_COUNTER = 0;
    private static int SOUTH_COUNTER = 0;

    public static void main(String[] args) {
        List<Action> data = getData();

        System.out.println(part1(data));
    }

    private static int part1(List<Action> data) {

        data.forEach(action -> {
            switch (action.getMove()) {
                case 'N':
                    NORTH_COUNTER += action.getValue();
                    break;
                case 'S':
                    SOUTH_COUNTER += action.getValue();
                    break;
                case 'E':
                    EAST_COUNTER += action.getValue();
                    break;
                case 'W':
                    WEST_COUNTER += action.getValue();
                    break;
                case 'R':
                case 'L':
                    FACE = getShipFace(action.getMove(),action.getValue());
                    break;
                case 'F':
                    if (FACE.equals("EAST")) {
                        if (WEST_COUNTER - action.getValue() > 0) {
                            WEST_COUNTER = WEST_COUNTER - action.getValue();
                        } else if (WEST_COUNTER - action.getValue() < 0) {
                            EAST_COUNTER = EAST_COUNTER + Math.abs(action.getValue() - WEST_COUNTER);
                            WEST_COUNTER = 0;
                        }
                    } else if (FACE.equals("SOUTH")) {
                        if (NORTH_COUNTER - action.getValue() > 0) {
                            NORTH_COUNTER = NORTH_COUNTER - action.getValue();
                        } else if (NORTH_COUNTER - action.getValue() < 0) {
                            SOUTH_COUNTER = SOUTH_COUNTER + Math.abs(action.getValue() - NORTH_COUNTER);
                            NORTH_COUNTER = 0;
                        }
                    } else if (FACE.equals("WEST")) {
                        if (EAST_COUNTER - action.getValue() > 0) {
                            EAST_COUNTER = EAST_COUNTER - action.getValue();
                        } else if (EAST_COUNTER - action.getValue() < 0) {
                            WEST_COUNTER = WEST_COUNTER + Math.abs(action.getValue() - EAST_COUNTER);
                            EAST_COUNTER = 0;
                        }
                    } else if (FACE.equals("NORTH")) {
                        if (SOUTH_COUNTER - action.getValue() > 0) {
                            SOUTH_COUNTER = SOUTH_COUNTER - action.getValue();
                        } else if (SOUTH_COUNTER - action.getValue() < 0) {
                            NORTH_COUNTER = NORTH_COUNTER + Math.abs(action.getValue() - SOUTH_COUNTER);
                            SOUTH_COUNTER = 0;
                        }
                    }
                    break;
            }
        });

        return (Math.abs(EAST_COUNTER) - Math.abs(WEST_COUNTER)) + (Math.abs(NORTH_COUNTER) - Math.abs(SOUTH_COUNTER));

    }

    private static String getShipFace(char move, int value) {

        switch (move) {
            case 'L':
                switch (value) {
                    case 90:
                        switch (FACE) {
                            case "NORTH":
                                return "WEST";
                            case "EAST":
                                return "NORTH";
                            case "SOUTH":
                                return "EAST";
                            case "WEST":
                                return "SOUTH";
                        }
                    case 270:
                        switch (FACE) {
                            case "NORTH":
                                return "EAST";
                            case "EAST":
                                return "SOUTH";
                            case "SOUTH":
                                return "WEST";
                            case "WEST":
                                return "NORTH";
                        }
                    case 180:
                        switch (FACE) {
                            case "NORTH":
                                return "SOUTH";
                            case "EAST":
                                return "WEST";
                            case "SOUTH":
                                return "NORTH";
                            case "WEST":
                                return "EAST";
                        }
                    default:
                        return "No Found";

                }
            case 'R':
                switch (value) {
                    case 90:
                        switch (FACE) {
                            case "NORTH":
                                return "EAST";
                            case "EAST":
                                return "SOUTH";
                            case "SOUTH":
                                return "WEST";
                            case "WEST":
                                return "NORTH";
                        }
                    case 270:
                        switch (FACE) {
                            case "NORTH":
                                return "WEST";
                            case "EAST":
                                return "NORTH";
                            case "SOUTH":
                                return "EAST";
                            case "WEST":
                                return "SOUTH";
                        }
                    case 180:
                        switch (FACE) {
                            case "NORTH":
                                return "SOUTH";
                            case "EAST":
                                return "WEST";
                            case "SOUTH":
                                return "NORTH";
                            case "WEST":
                                return "EAST";
                        }
                    default:
                        return "No Found";
                }
            default:
                return "No Found";
        }
    }

    private static List<Action> getData() {
        List<Action> list = new ArrayList<>();
        File file = new File("src/main/resource/input12.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            StringBuilder concatenatePassport = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                list.add(new Action(text.charAt(0), Integer.parseInt(text.substring(1))));
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


