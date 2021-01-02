package models;

public class Passport {

    private int birthYear;
    private int issueYear;
    private int expirationYear;
    private String height;
    private String hairColor;
    private String eyeColor;
    private String passportId;
    private String countryId;

    public Passport(){

    }

    public Passport(int birthYear, int issueYear, int expirationYear, String height, String hairColor, String eyeColor, String passportId, String countryId) {
        this.birthYear = birthYear;
        this.issueYear = issueYear;
        this.expirationYear = expirationYear;
        this.height = height;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.passportId = passportId;
        this.countryId = countryId;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public String getHeight() {
        return height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }
}
