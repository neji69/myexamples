package example.restassured;

public class User {
    private String email;
    private String name;
    private int[] tasks;
    private int[] companies;

    private String hobby;
    private String addres;
    private String name1;
    private String surname1;
    private String fathername1;
    private String cat;
    private String dog;
    private String parrot;
    private String cavy;
    private String hamster;
    private String squirrel;
    private String phone;
    private String inn;
    private String gender;
    private String birthday;
    private String date_start;

    public User(String email, String name, int[] tasks, int[] companies) {
        this.email = email;
        this.name = name;
        this.tasks = tasks;
        this.companies = companies;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getHobby() {
        return hobby;
    }

    public String getPhone() {
        return phone;
    }

    public int[] getCompanies() {
        return companies;
    }

    public int[] getTasks() {
        return tasks;
    }
}
