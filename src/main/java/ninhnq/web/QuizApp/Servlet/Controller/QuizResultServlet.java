package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.Entity.QuiztestEntity;
import ninhnq.web.QuizApp.Entity.TestQuestion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@WebServlet(name = "QuizResultServlet", value = "/Result")
public class QuizResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        List<TestQuestion> testQuestion = new ArrayList<>();
        quizTest.setId(198171);
        try {
            FileInputStream testFile = new FileInputStream(testFileName);
            InputStreamReader reader = new InputStreamReader(testFile, "UTF8");
            Scanner in = new Scanner(reader);
            int Qid = 113265;
            System.out.println("Start read file test-------------------------------------------------");
            System.out.println("File name: " + testFileName);
            System.out.println("File : " + in.hasNextLine());
            System.out.println("File : " + testFile.toString());
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.trim().equals("")) continue;
                // config............................................................
                if (line.startsWith("<NAME>")){
                    quizTest.setName(line.substring(6).trim());
                    System.out.println("name-------------------------------------------------");
                } else if (line.startsWith("<TIME>")){
                    quizTest.setTime(Integer.valueOf(line.substring(6).trim()));
                    System.out.println("time-------------------------------------------------");
                } else if (line.startsWith("<QUES>")){
                    quizTest.setPoint(Integer.valueOf(line.substring(6).trim()));
                    System.out.println("Ques-------------------------------------------------");
                } else if (line.startsWith("<OPEN>")){

                    System.out.println("open-------------------------------------------------");
                } else if (line.startsWith("<CLOSE>")){

                    System.out.println("close-------------------------------------------------");
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
                    QuestionEntity q = new QuestionEntity(Qid++,0,-1,line.substring(7).trim(),"");
                    mlistQuestion.add(q);
                    System.out.println("title-------------------------------------------------");
                    TestQuestion tq = new TestQuestion(q,new ArrayList<>());
                    testQuestion.add(tq);
                } else if (line.startsWith("<TEXT>")){
                    String phara = "";
                    String li = in.nextLine();
                    while (!li.equals("</TEXT>"))
                    {
                        phara += li + " <br> ";
                        li = in.nextLine();
                    }
                    QuestionEntity q = new QuestionEntity();
                    q.setId(Qid++);
                    q.setBid(0);
                    q.setType(-1);
                    q.setContent(phara);
                    System.out.println("text-------------------------------------------------");
                    TestQuestion tq = new TestQuestion(q,new ArrayList<>());
                    testQuestion.add(tq);
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

                    QuestionEntity questionEntity = new QuestionEntity(Qid++, 0, 0, question, correct_ans);
                    //QuestionEntity.insert(questionEntity);
                    System.out.println(questionEntity.toString());
                    int Aid = 0;
                    List<AnswerEntity> mlistAnswer = new ArrayList<>();
                    for (String ans : answers) {
                        System.out.println("Answer scaned: " + ans);
                        AnswerEntity answerEntity = new AnswerEntity(Aid++, Qid-1, ans.substring(2).trim());
                        //AnswerEntity.insert(answerEntity);
                        mlistAnswer.add(answerEntity);
                        System.out.println(answerEntity.toString());
                    }
                    TestQuestion tq = new TestQuestion(questionEntity,mlistAnswer);
                    testQuestion.add(tq);
                    System.out.println("Question - out-------------------------------------------------");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("mQuizTest",quizTest);
        request.setAttribute("mlistQuestion",testQuestion);
        request.setAttribute("quiz_title","TRẮC NGHIỆM CÔNG CHỨC");
        request.setAttribute("no_ques",quizTest.getPoint());
        request.setAttribute("user_name","Nguyen Quoc Ninh");
        request.setAttribute("quiz_name",quizTest.getName());
        request.setAttribute("time",quizTest.getTime());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/quizresult.jsp");
        dispatcher.forward(request,response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
