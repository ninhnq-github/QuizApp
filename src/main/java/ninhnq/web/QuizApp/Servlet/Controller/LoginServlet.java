package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.Account;
import ninhnq.web.QuizApp.helper.AppUtils;
import ninhnq.web.QuizApp.helper.LocalAccountLoader;
import ninhnq.web.QuizApp.helper.LocalObjectReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("authentication") !=null
                && session.getAttribute("authentication").equals("valid")){
            //request.setAttribute("user_name",((Account)session.getAttribute("user")).getName());
            response.sendRedirect(getServletContext().getContextPath() + "/Home");
            return;
        }
        String account_name = request.getParameter("account");
        String account_pass = request.getParameter("password");
        List<Account> mlist = LocalAccountLoader.load(getServletContext().getRealPath("/"));
        //System.out.println(account_name + "..................." + account_pass);
        for (Account acc: mlist){
            //System.out.println(acc.getAccount() + "-----" + acc.getPassword() + "-------" + acc.getName());
            if (acc.getAccount().equals(account_name) && acc.getPassword().equals(account_pass))
            {
                session.setAttribute("user",acc);
                session.setAttribute("authentication","valid");
                session.setAttribute("status","online");
                //------------------------------------------------------------
                AppUtils.RestoreSession(request.getSession(),getServletContext().getRealPath("/"));
                //------------------------------------------------------------
                request.setAttribute("user_name",acc.getName());
                response.sendRedirect(getServletContext().getContextPath() + "/Home");
                return;
            }
        }
        request.setAttribute("status","Đăng nhập thất bại, sai tên đăng nhập hoặc mật khẩu!");
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }
}