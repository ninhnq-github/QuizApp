package ninhnq.web.QuizApp.Servlet.Controller;

import com.mysql.cj.Session;
import ninhnq.web.QuizApp.Entity.*;
import ninhnq.web.QuizApp.helper.AppUtils;
import ninhnq.web.QuizApp.helper.LocalObjectWriter;
import ninhnq.web.QuizApp.helper.LocalQuizTestLoader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuizTestServlet", value = "/Quiz")
public class QuizTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String testID = request.getParameter("QuizID");

        if (LocalQuizTestLoader.check(getServletContext().getRealPath("/"),testID)==false) {
            response.getWriter().println("Bài kiểm tra không tồn tại!, " +
                    "<a href='"+getServletContext().getContextPath()+"/login.jsp'>quay về</a>");
            return;
        }

        QuizHeader header = LocalQuizTestLoader.load_header(getServletContext().getRealPath("/"),testID);
        if (System.currentTimeMillis() < header.getOpenl() || System.currentTimeMillis() > header.getClosel())
        {
            response.getWriter().println("Bài quiz đã đóng hoặc chưa mở, " +
                    "<a href='"+getServletContext().getContextPath()+"/login.jsp'>quay về</a>");
            return;
        }

        Quiztest quizTest = null;
        if (request.getSession().getAttribute("quizTest")==null)
        {

            quizTest = LocalQuizTestLoader.load(getServletContext().getRealPath("/"),testID);
            quizTest.setStart(System.currentTimeMillis());
            request.getSession().setAttribute("quizTest",quizTest);
            AppUtils.storeSession(request.getSession(),getServletContext().getRealPath("/"));
        }
        else
        {
            quizTest = (Quiztest) request.getSession().getAttribute("quizTest");
        }

        header = LocalQuizTestLoader.load_header(getServletContext().getRealPath("/"),quizTest.getId());
        if (System.currentTimeMillis() < header.getOpenl() || System.currentTimeMillis() > header.getClosel())
        {
            request.getSession().setAttribute("quizTest",null);
            AppUtils.storeSession(request.getSession(),getServletContext().getRealPath("/"));
            response.sendRedirect(getServletContext().getContextPath()+"/Home");
            return;
        }

        Account user = (Account) request.getSession().getAttribute("user");
        request.setAttribute("quiz_title","TRẮC NGHIỆM CÔNG CHỨC");
        request.setAttribute("user_name",user.getName());
        request.setAttribute("mQuizTest",quizTest);
        request.setAttribute("mlistQuestion",quizTest.getQuestions());
        request.setAttribute("no_ques",quizTest.getPoint());
        request.setAttribute("quiz_name",quizTest.getName());
        request.setAttribute("time",quizTest.getTime());
        request.setAttribute("QuizID",quizTest.getId());
        request.setAttribute("StartTime",quizTest.getStart());
        request.setAttribute("LimitTime",quizTest.getTime());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/quiztest.jsp");
        dispatcher.forward(request,response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    void dotest(HttpServletRequest request)
    {
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
    }
}
