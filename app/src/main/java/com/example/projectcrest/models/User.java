import java.util.Calendar;
import java.util.Date;

public class User {
    private long id;
    private String email;
    private String name;
    private int age;
    private int profession;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getProfession() {
        return profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public User(long id, String email, String name, int age, int profession) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
        this.profession = profession;
    }
}
