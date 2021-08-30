package ninhnq.web.QuizApp.Entity;

import java.util.List;

public class TestQuestion {
    public QuestionEntity getQuestion() {
        return question;
    }

    public List<AnswerEntity> getAnswer() {
        return answer;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public void setAnswer(List<AnswerEntity> answer) {
        this.answer = answer;
    }

    public TestQuestion(QuestionEntity question, List<AnswerEntity> answer, String choiced) {
        this.question = question;
        this.answer = answer;
        this.choiced = choiced;
    }

    QuestionEntity question;
    List<AnswerEntity> answer;

    public String getChoiced() {
        return choiced;
    }

    public void setChoiced(String choiced) {
        this.choiced = choiced;
    }

    String choiced;

    public TestQuestion(QuestionEntity question, List<AnswerEntity> answer) {
        this.question = question;
        this.answer = answer;
    }
}
