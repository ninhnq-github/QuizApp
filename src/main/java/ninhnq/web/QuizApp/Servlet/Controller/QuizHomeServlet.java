package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.Account;
import ninhnq.web.QuizApp.Entity.QuizHeader;
import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.helper.LocalAccountLoader;
import ninhnq.web.QuizApp.helper.LocalAssignLoader;
import ninhnq.web.QuizApp.helper.LocalQuizTestLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuizHomeServlet", value = "/Home")
public class QuizHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("authentication") ==null || session.getAttribute("authentication").equals("invalid")) {
            response.sendRedirect(getServletContext().getContextPath()+"/login.jsp");
            return;
        }
        Account user = (Account) session.getAttribute("user");
        String uid = user.getAccount();
        List<QuizHeader> mlist = LocalAssignLoader.load(getServletContext().getRealPath("/"),uid);
        request.setAttribute("quizAssigned", mlist);
        request.setAttribute("user_name",((Account)session.getAttribute("user")).getName());
        request.getRequestDispatcher("/quizhome.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}