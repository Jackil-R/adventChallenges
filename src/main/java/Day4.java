import models.Passport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    public static void main(String[] args) {
        List<Passport> passports = getData();

        List<Passport> validPassports = passports.stream().filter(p -> (
                p.getBirthYear() != 0 &&
                        p.getIssueYear() != 0 &&
                        p.getExpirationYear() != 0 &&
                        p.getHeight() != null &&
                        p.getHairColor() != null &&
                        p.getEyeColor() != null &&
                        p.getPassportId() != null)).collect(Collectors.toList());

        System.out.println(passports.size());
        System.out.println(validPassports.size());
        System.out.println(passports.size()-validPassports.size());

        Set<String> colors = Set.of("amb","blu","brn","gry","grn","hzl","oth");

        List<Passport> collect = validPassports.stream()
                .filter(passport -> passport.getBirthYear() >= 1920 && passport.getBirthYear() <= 2002 && String.valueOf(passport.getBirthYear()).length() == 4)
                .filter(passport -> passport.getIssueYear() >= 2010 && passport.getIssueYear() <= 2020 && String.valueOf(passport.getIssueYear()).length() == 4)
                .filter(passport -> passport.getExpirationYear() >= 2020 && passport.getExpirationYear() <= 2030 && String.valueOf(passport.getExpirationYear()).length() == 4)
                .filter(passport -> {
                    String height = passport.getHeight();
                    if(height.matches("([0-9]{3})([cm]{2})")){
                        int cm = Integer.parseInt(height.substring(0, height.indexOf('c')));
                        return cm >= 150 && cm <= 193;
                    }else if(height.matches("([0-9]{2})([in]{2})")){
                        int in = Integer.parseInt(height.substring(0, height.indexOf('i')));
                        return in >= 59 && in <= 76;
                    }else{
                        return false;
                    }
                })
                .filter(passport -> passport.getHairColor().matches("(#[a-f0-9]{6})"))
                .filter(passport -> colors.contains(passport.getEyeColor().toLowerCase()))
                .filter(passport -> passport.getPassportId().matches("([0-9]{9})")).collect(Collectors.toList());

        System.out.println(collect.size());


    }

    private static List<Passport> getData(){
        List<Passport> list = new ArrayList<>();
        File file = new File("src/main/resource/input4.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            StringBuilder concatenatePassport = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                if(!text.equalsIgnoreCase("")){
                    concatenatePassport.append(" ").append(text);
                }else{
                    list.add(parseFunction(concatenatePassport.toString()));
                    concatenatePassport = new StringBuilder();
                }
            }
            list.add(parseFunction(concatenatePassport.toString()));
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

    private static Passport parseFunction(String concatenatePassport) {
        List<String> passportField =Arrays.asList(concatenatePassport.split(" "));
        passportField.stream().sorted().forEach(String::trim);
        Passport passport = new Passport();

        Map<String, String> map=
                passportField.stream()
                        .map(s -> s.split(":"))
                        .collect(Collectors.toMap(a -> a[0], a -> a.length > 1 ? a[1] : ""));

        map.remove("");
        map.forEach( (k,v) -> {
            switch (k) {
                case "byr":
                    passport.setBirthYear(Integer.parseInt(v));
                    break;
                case "iyr":
                    passport.setIssueYear(Integer.parseInt(v));
                    break;
                case "eyr":
                    passport.setExpirationYear(Integer.parseInt(v));
                    break;
                case "hgt":
                    passport.setHeight(v);
                    break;
                case "hcl":
                    passport.setHairColor(v);
                    break;
                case "ecl":
                    passport.setEyeColor(v);
                    break;
                case "pid":
                    passport.setPassportId(v);
                    break;
                case "cid":
                    passport.setCountryId(v);
                    break;
            }
        });
        return passport;
    }
}
