package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.Account;
import ninhnq.web.QuizApp.Entity.QuizResult;
import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.Entity.TestQuestion;
import ninhnq.web.QuizApp.helper.AppConstant;
import ninhnq.web.QuizApp.helper.AppUtils;
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
        Account user = (Account) request.getSession().getAttribute("user");
        String uid = user.getAccount();
        Quiztest quiz = (Quiztest) request.getSession().getAttribute("quizTest");
        long start = quiz.getStart();
        long end = System.currentTimeMillis();
        quiz.setEnd(end);

        String SUBMITION_DIRECTORY = "submission";
        String dirPath = getServletContext().getRealPath("/") + SUBMITION_DIRECTORY;
        String filename = test_id + "$" + uid + "$" + quiz.getStart() + ".txt";
        String filePath = dirPath + File.separator + filename;
        //PrintWriter out = new PrintWriter(filePath);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
        //System.out.println("Start to write to: " + filePath);

        out.write("START: " + start + "\n");
        out.write("END: " + end + "\n");
        for (TestQuestion ques: quiz.getQuestions()){
            if (ques.getQuestion().getType()==-1) continue;
            String answer = request.getParameter("" + ques.getQuestion().getId());
            out.write(ques.getQuestion().getId() + ":" + answer + "\n");
        }
        out.close();
        //System.out.println("File writing completed");

        int correct = 0;
        for (TestQuestion ques: quiz.getQuestions()){
            if (ques.getQuestion().getType()==-1) continue;
            if (ques.getQuestion().getAnswer().equals(ques.getChoiced()))
                correct++;
        }

        //----------------------------------------TEST GRADING HISTORY HERE-------------------------------------------------------
        QuizResult result = new QuizResult();
        result.setName(quiz.getName());
        result.setQuizID(quiz.getId());
        result.setLstart(String.valueOf(quiz.getStart()));
        result.setStart(AppUtils.getDateTimeFormat(quiz.getStart(),"HH:mm:ss"));
        result.setEnd(AppUtils.getDateTimeFormat(quiz.getEnd(),"HH:mm:ss, dd/MM/yyyy"));
        result.setCorrect(String.valueOf(correct));
        result.setTime(String.valueOf(quiz.getTime()));
        result.setNoques(String.valueOf(quiz.getNoques()));
        result.setTotal_point(String.valueOf(quiz.getPoint()));
        result.setTotal_point(String.valueOf(quiz.getPoint()));
        result.setGrade_point(String.format("%.2f%%",(float)correct/quiz.getPoint()));
        result.setReview("allow");
        //System.out.println(result);

        String dir_path = getServletContext().getRealPath("/") + AppConstant.HISTORY_DIR;
        String file_name = user.getAccount() + ".txt";
        String file_path = dir_path + File.separator + file_name;
        File file = new File(file_path);
        if (file.exists()==false)
        {
            file.createNewFile();
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file_path,true));
            writer.writeObject(result);
            writer.close();
        }
        else
        {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file_path,true)){
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
            writer.writeObject(result);
            writer.close();
        }
        /*ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file_path));
        try {
            while (true)
            {
                QuizResult r2 = (QuizResult) inputStream.readObject();
                System.out.println(r2);
            }
        } catch (ClassNotFoundException | EOFException e) {
            e.printStackTrace();
        }
        inputStream.close();*/
        //-------------------------------------------------------------------------------------------------------------------------
        String url = getServletContext().getContextPath() + "/Result?QuizID=" + test_id + "&tid=" + quiz.getStart();
        //System.out.println("Sending you to: " + url);
        request.getSession().removeAttribute("quizTest");
        AppUtils.storeSession(request.getSession(),getServletContext().getRealPath("/"));
        response.sendRedirect(url);
    }
}