package ninhnq.web.QuizApp.Servlet.API;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.Entity.TestQuestion;
import ninhnq.web.QuizApp.helper.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TimeServlet", value = "/TimeAPI")
public class ServerTimeAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (request.getSession()==null || request.getSession().getAttribute("authentication") == null) {
            out.print("TIMEOUT");
            return;
        }
        if (request.getSession()==null){
            String time_start = request.getParameter("TimeStart");
            String time_limit = request.getParameter("TimeLimit");
            if (time_start == null || time_limit == null)
                out.print("TIMEOUT");
            long start = Long.parseLong(time_start), limit = Long.parseLong(time_limit);
            String timeleft = AppUtils.getTimeLeft(start,limit);
            out.print(timeleft);
        }
        Quiztest quiz = (Quiztest) request.getSession().getAttribute("quizTest");
        String timeleft = AppUtils.getTimeLeft(quiz.getStart(),Long.valueOf(quiz.getTime()));
        out.print(timeleft);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
