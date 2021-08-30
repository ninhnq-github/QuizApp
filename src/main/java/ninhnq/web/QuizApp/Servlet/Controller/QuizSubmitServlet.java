package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.Entity.QuiztestEntity;
import ninhnq.web.QuizApp.Entity.TestQuestion;
import ninhnq.web.QuizApp.helper.LocalQuizTestLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@WebServlet(name = "QuizSubmitServlet", value = "/Submit")
public class QuizSubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("This servlet was called: " + getServletName());
        System.out.println(getServletInfo());
        String test_id = request.getParameter("QuizID");
        QuiztestEntity quiztest = LocalQuizTestLoader.load(getServletContext().getRealPath("/"),test_id);
        int userid = 11229111;
        long start = 118198198198L;
        long end = 118218011801L;
        int attempt = 1;

        String SUBMITION_DIRECTORY = "submission";
        String dirPath = getServletContext().getRealPath("/") + SUBMITION_DIRECTORY;
        String filename = test_id + "$" + userid + "$" + attempt + ".txt";
        String filePath = dirPath + File.separator + filename;
        PrintWriter out = new PrintWriter(filePath);
        System.out.println("Start to write to: " + filePath);

        out.println("START: " + start);
        out.println("END: " + end);
        for (TestQuestion ques: quiztest.getQuestions()){
            if (ques.getQuestion().getType()==-1) continue;
            String answer = request.getParameter("" + ques.getQuestion().getId());
            out.println(ques.getQuestion().getId() + ":" + answer);
        }
        out.close();
        System.out.println("File writing completed");

        String url = getServletContext().getContextPath() + "/Result?QuizID=" + test_id + "&Attempt=" + attempt;
        System.out.println("Sending you to: " + url);
        response.sendRedirect(url);
    }
}