import models.Password;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {

    public static void main(String[] args) {
        List<Password> number = getPassword();
        AtomicInteger count = new AtomicInteger();

        number.forEach(policy -> {
//            String regex = "[^"+policy.getCharacter()+"]";
//            String newString = policy.getPassword().replaceAll(regex,"");
//            if(newString.length() >= policy.getStart() && newString.length() <= policy.getEnd()){
//                count.getAndIncrement();
//            }

            String password = policy.getPassword();
            int start = policy.getStart()-1;
            int end = policy.getEnd()-1;
            char c = policy.getCharacter();
            if( ((password.charAt(start) == c) && (password.charAt(end) != c)) || (((password.charAt(start) != c) && (password.charAt(end) == c))))
            {
                count.getAndIncrement();
            }
        });
        System.out.println(count.intValue());
    }

    private static List<Password> getPassword(){
        List<Password> list = new ArrayList<>();

        File file = new File("src/main/resource/input2.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(parseFunction(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        return list;
    }

    private static Password parseFunction(String text) {
        //9-11 p: pppppppppxblp
        String[] split = text.split(" ");
        return new Password(
                Integer.parseInt(split[0].split("-")[0]),
                Integer.parseInt(split[0].split("-")[1]),
                split[1].replace(':',' ').trim().charAt(0),
                split[2]);

    }
}
