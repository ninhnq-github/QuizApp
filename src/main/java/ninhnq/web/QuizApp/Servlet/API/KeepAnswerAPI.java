package ninhnq.web.QuizApp.Servlet.API;

import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.helper.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "KeepAnswerServlet", value = "/AnswerAPI")
public class KeepAnswerAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (request.getSession()==null || request.getSession().getAttribute("authentication") == null) {
            out.print("You are Not Authenticated, please login!");
            response.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
            //System.out.println("request has been rejected!");
            return;
        }
        //System.out.println("request has been accepted!");
        int qid = Integer.parseInt(request.getParameter("question"));
        int aid = Integer.parseInt(request.getParameter("answer"));
        //System.out.println("Answer: " + aid + " of Question:" + qid + " is recorded!");
        Quiztest quiz = (Quiztest) request.getSession().getAttribute("quizTest");
        if (aid==-1)
            quiz.getQuestions().get(qid).setChoiced("null");
        else
        {
            String answer = quiz.getQuestions().get(qid).getAnswer().get(aid).getContent();
            //System.out.println(answer);
            quiz.getQuestions().get(qid).setChoiced(answer);
        }
        out.println("OK");
        response.setStatus(200);
        AppUtils.storeSession(request.getSession(),getServletContext().getRealPath("/"));
        return;
    }
}
