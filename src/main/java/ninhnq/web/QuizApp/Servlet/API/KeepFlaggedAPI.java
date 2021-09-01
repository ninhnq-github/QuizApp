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

@WebServlet(name = "KeepFlaggedServlet", value = "/FlagAPI")
public class KeepFlaggedAPI extends HttpServlet {
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
        boolean flag = Boolean.parseBoolean(request.getParameter("flag"));
        //System.out.println(flag);
        //System.out.println(request.getParameter("flag"));
        //System.out.println("Answer: " + aid + " of Question:" + qid + " is recorded!");
        Quiztest quiz = (Quiztest) request.getSession().getAttribute("quizTest");
        quiz.getQuestions().get(qid).setFlagged(flag);
        out.println("OK");
        response.setStatus(200);
        AppUtils.storeSession(request.getSession(),getServletContext().getRealPath("/"));
        return;
    }
}
