package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.Entity.QuiztestEntity;
import ninhnq.web.QuizApp.Entity.TestQuestion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@WebServlet(name = "QuizTestServlet", value = "/Quiz")
public class QuizTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        //dotest(request);

        String TEST_DIRECTORY = "question_bank";
        String testPath = getServletContext().getRealPath("/") + TEST_DIRECTORY;
        String testID = request.getParameter("QuizID");
        String testFileName = testPath + File.separator + testID + ".txt";

        QuiztestEntity quizTest = new QuiztestEntity();
        List<QuestionEntity> mlistQuestion = new ArrayList<QuestionEntity>();
        quizTest.setId(198171);
        try {
            File testFile = new File(testFileName);
            Scanner in = new Scanner(testFile);
            int Qid = 0;
            System.out.println("Start read file test-------------------------------------------------");
            System.out.println("File name: " + testFileName);
            System.out.println("File : " + in.hasNextLine());
            System.out.println("File : " + testFile.toString());
            while (in.hasNextLine()) {
                String line = in.nextLine();
                // config............................................................
                if (line.startsWith("<NAME>")){
                    quizTest.setName(line.substring(6));
                    System.out.println("name-------------------------------------------------");
                } else if (line.startsWith("<TIME>")){
                    quizTest.setTime(Integer.valueOf(line.substring(6)));
                    System.out.println("time-------------------------------------------------");
                } else if (line.startsWith("<QUES>")){
                    quizTest.setPoint(Integer.valueOf(line.substring(6)));
                    System.out.println("ques-------------------------------------------------");
                } else if (line.startsWith("<TRY>")){
                    // set the properties
                } else if (line.startsWith("<REVIEW>")){
                    // set the properties
                } else if (line.startsWith("<SHOW>")){
                    // set the properties
                } else if (line.startsWith("<LOGIN>")){
                    // set the properties
                }
                // Import question............................................................
                else if (line.startsWith("<TITLE>")){
                    // set the properties
                    QuestionEntity q = new QuestionEntity();
                    q.setType(-1);
                    q.setContent(line.substring(7));
                    mlistQuestion.add(q);
                    System.out.println("title-------------------------------------------------");
                } else if (line.startsWith("<TEXT>")){
                    String phara = "";
                    String li = in.nextLine();
                    while (!li.equals("</TEXT>"))
                    {
                        phara += li + "\n";
                        li = in.nextLine();
                    }
                    QuestionEntity q = new QuestionEntity();
                    q.setType(-1);
                    q.setContent(phara);
                    System.out.println("text-------------------------------------------------");
                } else {
                    System.out.println("Question - in-------------------------------------------------");
                    String content = line;
                    if (content.trim().equals("")) continue;
                    //System.out.println(content);
                    String question = content;
                    List<String> answers = new ArrayList<>();
                    String correct_ans = "", correct = "";
                    while (true) {
                        String answer = in.nextLine();
                        if (answer.startsWith("ANSWER:")) {
                            correct = answer.substring(8).trim();
                            break;
                        }
                        answers.add(answer);
                    }

                    for (String ans : answers)
                        if (ans.toLowerCase().startsWith(correct.toLowerCase()))
                            correct_ans = ans.substring(2).trim();

                    QuestionEntity questionEntity = new QuestionEntity(0, 0, 0, question, correct_ans);
                    QuestionEntity.insert(questionEntity);
                    System.out.println(questionEntity.toString());
                    for (String ans : answers) {
                        AnswerEntity answerEntity = new AnswerEntity(0, 0, ans.substring(2).trim());
                        AnswerEntity.insert(answerEntity);
                        System.out.println(answerEntity.toString());
                    }
                    System.out.println("Question - out-------------------------------------------------");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("mQuizTest",quizTest);
        request.setAttribute("mlistQuestion",mlistQuestion);
        request.setAttribute("quiz_title","TRẮC NGHIỆM CÔNG CHỨC");
        request.setAttribute("no_ques",quizTest.getPoint());
        request.setAttribute("user_name","Nguyen Quoc Ninh");
        request.setAttribute("quiz_name",quizTest.getName());
        request.setAttribute("time",quizTest.getTime());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/quiztest.jsp");
        dispatcher.forward(request,response);
        return;
    }

    void dotest(HttpServletRequest request)
    {
        String user_name = "Ninh Nguyễn";
        String quiz_name = "ĐỀ 1: MÔN KIẾN THỨC CHUNG";
        Integer no_ques = 60;
        Integer time = 60;

        List<TestQuestion> mlistquestion = new ArrayList<TestQuestion>();

        int id = 1238721;
        for (int i=0; i<60; i++){
            QuestionEntity question = new QuestionEntity();
            question.setId(id+i);
            question.setAnswer("Correct _" + i);
            question.setBid(0);
            question.setContent("What is the correct answer?");
            question.setType(0);
            List<AnswerEntity> listanswer = new ArrayList<AnswerEntity>();
            for (int j=0; j<3; j++) {
                AnswerEntity answer = new AnswerEntity();
                answer.setContent("Wrong _ " + i);
                answer.setId(j);
                answer.setQid(id+i);
                listanswer.add(answer);
            }
            AnswerEntity answer = new AnswerEntity();
            answer.setContent("Correct _" + i);
            answer.setId(3);
            answer.setQid(id+i);
            listanswer.add(answer);
            TestQuestion testQuestion= new TestQuestion(question,listanswer);
            mlistquestion.add(testQuestion);
        }
        request.setAttribute("mlistQuestion",mlistquestion);
        request.setAttribute("quiz_title","TRẮC NGHIỆM CÔNG CHỨC");
        request.setAttribute("no_ques",no_ques);
        request.setAttribute("user_name",user_name);
        request.setAttribute("quiz_name",quiz_name);
        request.setAttribute("time",time);
    }
}
