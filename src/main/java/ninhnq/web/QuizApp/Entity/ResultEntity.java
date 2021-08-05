package ninhnq.web.QuizApp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "result", schema = "quizappdb", catalog = "")
public class ResultEntity {
    private int id;
    private Integer uid;
    private Integer tid;
    private Integer correct;
    private Integer wrong;
    private String time;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "tid", nullable = true)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "correct", nullable = true)
    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    @Basic
    @Column(name = "wrong", nullable = true)
    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    @Basic
    @Column(name = "time", nullable = true, length = -1)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultEntity that = (ResultEntity) o;

        if (id != that.id) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (tid != null ? !tid.equals(that.tid) : that.tid != null) return false;
        if (correct != null ? !correct.equals(that.correct) : that.correct != null) return false;
        if (wrong != null ? !wrong.equals(that.wrong) : that.wrong != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
        result = 31 * result + (wrong != null ? wrong.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
