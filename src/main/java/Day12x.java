import models.Action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day12x {

    private static String FACE = "EAST";
    private static int X = 0;
    private static int Y = 0;

    public static void main(String[] args) {
        List<Action> data = getData();

        System.out.println(part1(data));
    }

    private static int part1(List<Action> data) {

        data.forEach(action -> {
            switch (action.getMove()) {
                case 'N':
                    Y += action.getValue();
                    break;
                case 'S':
                    Y -= action.getValue();
                    break;
                case 'E':
                    X += action.getValue();
                    break;
                case 'W':
                    X -= action.getValue();
                    break;
                case 'R':
                case 'L':
                    FACE = getShipFace(action.getMove(),action.getValue());
                    break;
                case 'F':
                    if (FACE.equals("EAST")) {
                        X +=action.getValue();
                    } else if (FACE.equals("SOUTH")) {
                        Y -=action.getValue();
                    } else if (FACE.equals("WEST")) {
                        X -=action.getValue();
                    } else if (FACE.equals("NORTH")) {
                        Y +=action.getValue();
                    }
                    break;
            }
        });

        return Math.abs(X) + Math.abs(Y);

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


