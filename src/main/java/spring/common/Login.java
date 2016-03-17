package main.java.spring.common;


public class Login {
    private String name;
    private String password;
    private Country country;
    private String lifeForm;
    private String birthDate;

    public Login(String birthDate, String lifeForm, Country country, String password, String name) {
        this.birthDate = birthDate;
        this.lifeForm = lifeForm;
        this.country = country;
        this.password = password;
        this.name = name;
    }

    public Login() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLifeForm() {
        return lifeForm;
    }

    public void setLifeForm(String lifeForm) {
        this.lifeForm = lifeForm;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
