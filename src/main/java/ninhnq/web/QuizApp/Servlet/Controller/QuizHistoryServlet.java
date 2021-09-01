package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.Account;
import ninhnq.web.QuizApp.Entity.QuizHeader;
import ninhnq.web.QuizApp.Entity.QuizResult;
import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.helper.AppUtils;
import ninhnq.web.QuizApp.helper.LocalAssignLoader;
import ninhnq.web.QuizApp.helper.LocalObjectReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuizHistoryServlet", value = "/History")
public class QuizHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("authentication") ==null || session.getAttribute("authentication").equals("invalid")) {
            response.sendRedirect(getServletContext().getContextPath()+"/login.jsp");
            return;
        }

        Quiztest test = (Quiztest) request.getSession().getAttribute("quizTest");
        if (test!=null){
            response.sendRedirect(getServletContext().getContextPath()+"/Quiz?QuizID=" + test.getId());
            return;
        }
        Account user = (Account) session.getAttribute("user");
        String uid = user.getAccount();
        List<QuizResult> mlist = AppUtils.loadResult(request.getSession(),getServletContext().getRealPath("/"));
        request.setAttribute("quizHistory", mlist);
        request.setAttribute("user_name",((Account)session.getAttribute("user")).getName());
        request.getRequestDispatcher("/quizhistory.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}