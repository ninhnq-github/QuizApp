package ninhnq.web.QuizApp.Servlet.Controller;

import com.google.protobuf.ByteString;
import ninhnq.web.QuizApp.Entity.Account;
import ninhnq.web.QuizApp.Entity.QuizResult;
import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.Entity.TestQuestion;
import ninhnq.web.QuizApp.helper.AppConstant;
import ninhnq.web.QuizApp.helper.AppUtils;
import ninhnq.web.QuizApp.helper.LocalQuizTestLoader;
import ninhnq.web.QuizApp.helper.LocalSubmissionLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "QuizResultServlet", value = "/Result")
public class QuizResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //String TEST_DIRECTORY = "question_bank";
        //String testPath = getServletContext().getRealPath("/") + TEST_DIRECTORY;
        AppUtils.storeSession(request.getSession(),getServletContext().getRealPath("/"));
        Account user = (Account) request.getSession().getAttribute("user");
        String uid = user.getAccount();
        String testID = request.getParameter("QuizID");
        long tid = Long.parseLong(request.getParameter("tid"));
        //String testFileName = testPath + File.separator + testID + ".txt";
        Quiztest quizTest = LocalQuizTestLoader.load(getServletContext().getRealPath("/"),testID);
        //System.out.println("[TRACE] Start to read submission");
        LocalSubmissionLoader.load(getServletContext().getRealPath("/"),uid,testID,tid,quizTest);
        //System.out.println("[TRACE] Read submission finished");
        //System.out.println("[TRACE] Grading");
        int correct = 0;
        for (TestQuestion ques: quizTest.getQuestions()){
            if (ques.getQuestion().getType()==-1) continue;
            if (ques.getQuestion().getAnswer().equals(ques.getChoiced()))
                correct++;
        }
        //System.out.println("[TRACE] Setting attributes");
        String result_sum = String.format("Đúng %2.2f trên tổng số %2.2f câu hỏi (%.2f%%)",
                                                                (float)correct,(float)quizTest.getPoint(),
                                                                ((float)correct/quizTest.getPoint())*100);

        long during = quizTest.getEnd() - quizTest.getStart();
        String dtime = during / (60*60*1000) + " giờ " + (during % (60*60*1000))/(60*1000)+ " phút " + (during % (60*1000))/(1000) + " giây ";
        request.setAttribute("mQuizTest",quizTest);
        request.setAttribute("startTime", AppUtils.getDateTimeFormat(quizTest.getStart(),"EEEE, dd MMMM yyyy, HH:mm:ss a"));
        request.setAttribute("endTime", AppUtils.getDateTimeFormat(quizTest.getEnd(),"EEEE, dd MMMM yyyy, HH:mm:ss a"));
        request.setAttribute("duringTime", dtime);
        request.setAttribute("quỉzResult",result_sum);
        request.setAttribute("mlistQuestion",quizTest.getQuestions());
        request.setAttribute("quiz_title","TRẮC NGHIỆM CÔNG CHỨC");
        request.setAttribute("no_ques",quizTest.getPoint());
        request.setAttribute("user_name",user.getName());
        request.setAttribute("quiz_name",quizTest.getName());
        request.setAttribute("time",quizTest.getTime());

        //System.out.println("[TRACE] You are going to be send to .quizresult.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/quizresult.jsp");
        dispatcher.forward(request,response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
