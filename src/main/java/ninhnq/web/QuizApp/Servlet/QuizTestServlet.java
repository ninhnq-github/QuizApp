package ninhnq.web.QuizApp.Servlet;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.Entity.TestQuestion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuizTestServlet", value = "/Quiz")
public class QuizTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String user_name = "Ninh Nguyễn";
        String quiz_name = "ĐỀ 1: MÔN KIẾN THỨC CHUNG";
        Integer no_ques = 60;
        Integer time = 60;

        List<TestQuestion> mlistquestion = new ArrayList<TestQuestion>();

        int id = 1238721;
        for (int i=0; i<60; i++){
            QuestionEntity question = new QuestionEntity();
            question.setId(id+i);
            question.setAnswer("Correct _" + i);
            question.setBid(0);
            question.setContent("What is the correct answer?");
            question.setType(0);
            List<AnswerEntity> listanswer = new ArrayList<AnswerEntity>();
            for (int j=0; j<3; j++) {
                AnswerEntity answer = new AnswerEntity();
                answer.setContent("Wrong _ " + i);
                answer.setId(j);
                answer.setQid(id+i);
                listanswer.add(answer);
            }
            AnswerEntity answer = new AnswerEntity();
            answer.setContent("Correct _" + i);
            answer.setId(3);
            answer.setQid(id+i);
            listanswer.add(answer);
            TestQuestion testQuestion= new TestQuestion(question,listanswer);
            mlistquestion.add(testQuestion);
        }
        request.setAttribute("mlistQuestion",mlistquestion);
        request.setAttribute("quiz_title","TRẮC NGHIỆM CÔNG CHỨC");
        request.setAttribute("no_ques",no_ques);
        request.setAttribute("user_name",user_name);
        request.setAttribute("quiz_name",quiz_name);
        request.setAttribute("time",time);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/quiztest.jsp");
        dispatcher.forward(request,response);
        return;
    }
}