package site.nomoreparties.stellarburgers;

import org.apache.commons.lang3.RandomStringUtils;

public class User {

    private String name;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getRandomUser() {
        User user = new User();

        user.setName(RandomStringUtils.randomAlphabetic(5));
        user.setEmail(getRandomEmail());
        user.setPassword(RandomStringUtils.randomAlphabetic(6));
        return user;
    }

    public static User getRandomUserWithPasswordLessSixSymbols() {
        User user = new User();

        user.setName(RandomStringUtils.randomAlphabetic(5));
        user.setEmail(getRandomEmail());
        user.setPassword(RandomStringUtils.randomAlphabetic(5));
        return user;
    }

    public static String getRandomEmail(){
        return RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@"
                + RandomStringUtils.randomAlphabetic(5).toLowerCase() + ".com";
    }
}
