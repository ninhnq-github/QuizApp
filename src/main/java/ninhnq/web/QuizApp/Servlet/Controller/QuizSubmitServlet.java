package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.Account;
import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.Entity.TestQuestion;
import ninhnq.web.QuizApp.helper.LocalQuizTestLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "QuizSubmitServlet", value = "/Submit")
public class QuizSubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //System.out.println("This servlet was called: " + getServletName());
        //System.out.println(getServletInfo());
        String test_id = request.getParameter("QuizID");
        Quiztest quiztest = LocalQuizTestLoader.load(getServletContext().getRealPath("/"),test_id);
        Account user = (Account) request.getSession().getAttribute("user");
        String uid = user.getAccount();
        Quiztest quiz = (Quiztest) request.getSession().getAttribute("quizTest");
        long start = quiz.getStart();
        long end = System.currentTimeMillis();
        quiz.setEnd(end);
        int attempt = 1;

        String SUBMITION_DIRECTORY = "submission";
        String dirPath = getServletContext().getRealPath("/") + SUBMITION_DIRECTORY;
        String filename = test_id + "$" + uid + "$" + attempt + ".txt";
        String filePath = dirPath + File.separator + filename;
        //PrintWriter out = new PrintWriter(filePath);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
        //System.out.println("Start to write to: " + filePath);

        out.write("START: " + start + "\n");
        out.write("END: " + end + "\n");
        for (TestQuestion ques: quiztest.getQuestions()){
            if (ques.getQuestion().getType()==-1) continue;
            String answer = request.getParameter("" + ques.getQuestion().getId());
            out.write(ques.getQuestion().getId() + ":" + answer + "\n");
        }
        out.close();
        //System.out.println("File writing completed");

        String url = getServletContext().getContextPath() + "/Result?QuizID=" + test_id + "&Attempt=" + attempt;
        //System.out.println("Sending you to: " + url);
        response.sendRedirect(url);
    }
}