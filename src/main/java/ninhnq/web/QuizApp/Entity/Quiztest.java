package ninhnq.web.QuizApp.Entity;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class Quiztest {
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quiztest)) return false;
        Quiztest quiztest = (Quiztest) o;
        return getPoint() == quiztest.getPoint() && getNoques() == quiztest.getNoques() && getTime() == quiztest.getTime() && getStart() == quiztest.getStart() && getEnd() == quiztest.getEnd() && Objects.equals(getId(), quiztest.getId()) && Objects.equals(getName(), quiztest.getName()) && Objects.equals(getQuestions(), quiztest.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPoint(), getNoques(), getTime(), getStart(), getEnd(), getQuestions());
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getNoques() {
        return noques;
    }

    public void setNoques(int noques) {
        this.noques = noques;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public List<TestQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestQuestion> questions) {
        this.questions = questions;
    }

    private String id;
    private String name;
    private int point;
    private int noques;
    private int time;
    private long start;
    private long end;

    public Quiztest(){}

    public Quiztest(String id) {
        this.id = id;
        this.name = "Chưa cập nhật";
        this.start = 0L;
        this.end = 0L;
        this.noques = 0;
        this.time = 0;
        this.point = 0;
    }

    public Quiztest(String id, String name, Integer point, Integer noques, Integer time, Long start, Long end, List<TestQuestion> questions) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.noques = noques;
        this.time = time;
        this.start = start;
        this.end = end;
        this.questions = questions;
    }

    List<TestQuestion> questions;
}
