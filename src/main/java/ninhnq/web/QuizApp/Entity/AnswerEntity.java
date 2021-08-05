package ninhnq.web.QuizApp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "quizappdb", catalog = "")
public class AnswerEntity {
    private int id;
    private Integer qid;
    private String content;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "qid", nullable = true)
    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (id != that.id) return false;
        if (qid != null ? !qid.equals(that.qid) : that.qid != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (qid != null ? qid.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
