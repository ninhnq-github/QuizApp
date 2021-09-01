package ninhnq.web.QuizApp.Entity;

public class Account {
    public Account(String name, String account, String password) {
        this.name = name;
        this.account = account;
        this.password = password;
    }

    String name;
    String account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String account, String password, String name, String phone) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.phone = phone;
    }

    String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;

}
