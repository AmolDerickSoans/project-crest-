import java.util.Calendar;
import java.util.Date;

public class User {
    private long id;
    private String email;
    private String password;
    private Date createDate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User(long id, String email, String password, Date createDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createDate = Calendar.getInstance().getTime();
    }
}
