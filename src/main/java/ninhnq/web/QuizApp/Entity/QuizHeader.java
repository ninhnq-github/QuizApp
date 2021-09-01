package ninhnq.web.QuizApp.Entity;

import ninhnq.web.QuizApp.helper.AppUtils;

public class QuizHeader {
    String id;
    String name;
    String ques;
    String time;
    String open;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getId() {
        return id;
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

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    String close;

    public QuizHeader(Quiztest q){
        this.id = q.getId();
        this.name = q.getName();

        if (q.getNoques()==0){
            this.ques = "//";
        } else
            this.ques = String.valueOf(q.getNoques());

        if (q.getTime()==0){
            this.time = "//";
        } else
            this.time = String.valueOf(q.getTime());

        if (q.getStart()==0){
            this.open = "//";
        } else
            this.open = AppUtils.getDateTimeFormat(q.getStart(),"HH:mm dd/MM/yyyy");

        if (q.getEnd()==0){
            this.close = "//";
        } else
            this.close = AppUtils.getDateTimeFormat(q.getEnd(),"HH:mm dd/MM/yyyy");

        if (System.currentTimeMillis()>q.getStart() && System.currentTimeMillis()<q.getEnd())
        {
            this.status = "OPEN";
        }
        else if (System.currentTimeMillis()<q.getStart() || q.getStart()==0 ||
                q.getEnd()==0 || q.getTime()==0 || q.getNoques()==0 || q.getPoint()==0){
            this.status = "WAIT";
        } else
            this.status = "CLOSED";
    }
}
