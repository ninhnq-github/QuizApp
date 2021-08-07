package ninhnq.web.QuizApp.Servlet.API;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
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
        String timeleft = AppUtils.getTimeLeft(Long.valueOf("1628175896102"),Long.valueOf(60));
        out.print(timeleft);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
