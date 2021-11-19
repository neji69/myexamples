package example.restassured;

import java.util.List;

public class Party {

    public class DateStart {
        private int sec;
        private int usec;
    }

    public class DateUpdated {
        private int sec;
        private int usec;
    }

    public class Birthday {
        private int sec;
        private int usec;
    }

    public class DateRegister {
        private int sec;
        private int usec;
    }

    public class Company {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

    public class Task {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

    private String email;
    private String name;
    private String name1;
    private String hobby;
    private String surname1;
    private String fathername1;
    private String cat;
    private String dog;
    private String parrot;
    private String cavy;
    private String hamster;
    private String squirrel;
    private String phone;
    private String adres;
    private String gender;
    private DateStart date_start;
    private DateUpdated date_updated;
    private Birthday birthday;
    private List<String> role;
    private DateRegister date_register;
    private String date;
    private String by_user;
    private List<Company> companies;
    private List<Task> tasks;

    public String getEmail() {
        return email;
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

    public List<Company> getCompanies() {
        return companies;
    }
}
