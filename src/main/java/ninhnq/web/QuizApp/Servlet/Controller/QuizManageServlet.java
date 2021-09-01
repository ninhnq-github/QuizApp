package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.BankEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.Entity.TestQuestion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuizManageServlet", value = "/Manage")
public class QuizManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String user_name = "Ninh Nguyễn";
        String quiz_name = "THÊM NGÂN HÀNG CÂU HỎI";

        request.setAttribute("quiz_title","QUESTION BANK");
        request.setAttribute("user_name",user_name);
        request.setAttribute("quiz_name",quiz_name);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/quizmanage.jsp");
        dispatcher.forward(request,response);
        return;
    }
}
