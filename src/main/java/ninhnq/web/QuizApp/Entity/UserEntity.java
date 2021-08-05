package ninhnq.web.QuizApp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "quizappdb", catalog = "")
public class UserEntity {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Integer gen;
    private String birht;
    private Byte active;
    private Byte live;
    private String last;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 12)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "gen", nullable = true)
    public Integer getGen() {
        return gen;
    }

    public void setGen(Integer gen) {
        this.gen = gen;
    }

    @Basic
    @Column(name = "birht", nullable = true, length = -1)
    public String getBirht() {
        return birht;
    }

    public void setBirht(String birht) {
        this.birht = birht;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Basic
    @Column(name = "live", nullable = true)
    public Byte getLive() {
        return live;
    }

    public void setLive(Byte live) {
        this.live = live;
    }

    @Basic
    @Column(name = "last", nullable = true, length = -1)
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (gen != null ? !gen.equals(that.gen) : that.gen != null) return false;
        if (birht != null ? !birht.equals(that.birht) : that.birht != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (live != null ? !live.equals(that.live) : that.live != null) return false;
        if (last != null ? !last.equals(that.last) : that.last != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (gen != null ? gen.hashCode() : 0);
        result = 31 * result + (birht != null ? birht.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (live != null ? live.hashCode() : 0);
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }
}
