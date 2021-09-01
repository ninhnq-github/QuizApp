package ninhnq.web.QuizApp.Entity;

import java.io.Serializable;
import java.util.Objects;

public class QuizResult implements Serializable {
    String quizID;
    String name;
    String noques;
    String total_point;
    String time;
    String start;
    String end;
    String correct;
    String grade_point;
    String lstart;

    public String getLstart() {
        return lstart;
    }

    public void setLstart(String lstart) {
        this.lstart = lstart;
    }

    public QuizResult(){}

    @Override
    public String toString() {
        return "QuizResult{" +
                "quizID='" + quizID + '\'' +
                ", name='" + name + '\'' +
                ", noques='" + noques + '\'' +
                ", total_point='" + total_point + '\'' +
                ", time='" + time + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", correct='" + correct + '\'' +
                ", grade_point='" + grade_point + '\'' +
                ", review='" + review + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizResult)) return false;
        QuizResult that = (QuizResult) o;
        return Objects.equals(getQuizID(), that.getQuizID()) && Objects.equals(getName(), that.getName()) && Objects.equals(getNoques(), that.getNoques()) && Objects.equals(getTotal_point(), that.getTotal_point()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getStart(), that.getStart()) && Objects.equals(getEnd(), that.getEnd()) && Objects.equals(getCorrect(), that.getCorrect()) && Objects.equals(getGrade_point(), that.getGrade_point()) && Objects.equals(getReview(), that.getReview());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuizID(), getName(), getNoques(), getTotal_point(), getTime(), getStart(), getEnd(), getCorrect(), getGrade_point(), getReview());
    }

    public QuizResult(String quizID, String name, String noques, String total_point, String time, String start, String end, String correct, String grade_point, String review) {
        this.quizID = quizID;
        this.name = name;
        this.noques = noques;
        this.total_point = total_point;
        this.time = time;
        this.start = start;
        this.end = end;
        this.correct = correct;
        this.grade_point = grade_point;
        this.review = review;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoques() {
        return noques;
    }

    public void setNoques(String noques) {
        this.noques = noques;
    }

    public String getTotal_point() {
        return total_point;
    }

    public void setTotal_point(String total_point) {
        this.total_point = total_point;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getGrade_point() {
        return grade_point;
    }

    public void setGrade_point(String grade_point) {
        this.grade_point = grade_point;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    String review;

}
