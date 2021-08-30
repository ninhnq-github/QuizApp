package ninhnq.web.QuizApp.helper;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.Entity.QuiztestEntity;
import ninhnq.web.QuizApp.Entity.TestQuestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LocalQuizTestLoader {
    public static QuiztestEntity load(String baseDir, String testID){
        QuiztestEntity quizTest = new QuiztestEntity();

        String TEST_DIRECTORY = "question_bank";
        String testPath = baseDir + TEST_DIRECTORY;
        String testFileName = testPath + File.separator + testID + ".txt";

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
                    //System.out.println("name-------------------------------------------------");
                } else if (line.startsWith("<TIME>")){
                    quizTest.setTime(Integer.valueOf(line.substring(6).trim()));
                    //System.out.println("time-------------------------------------------------");
                } else if (line.startsWith("<QUES>")){
                    quizTest.setPoint(Integer.valueOf(line.substring(6).trim()));
                    //System.out.println("Ques-------------------------------------------------");
                } else if (line.startsWith("<OPEN>")){
                    String open_s = line.substring(6).trim();
                    quizTest.setStart(AppUtils.getTimeMilis(open_s,"dd-MM-yyy HH:mm:ss"));
                    //System.out.println("open-------------------------------------------------");
                } else if (line.startsWith("<CLOSE>")){
                    String open_s = line.substring(7).trim();
                    quizTest.setStart(AppUtils.getTimeMilis(open_s,"dd-MM-yyy HH:mm:ss"));
                    //System.out.println("close-------------------------------------------------");
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
                    //mlistQuestion.add(q);
                    //System.out.println("title-------------------------------------------------");
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
                    //System.out.println("text-------------------------------------------------");
                    TestQuestion tq = new TestQuestion(q,new ArrayList<>());
                    testQuestion.add(tq);
                } else {
                    //System.out.println("Question - in-------------------------------------------------");
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
                    //System.out.println(questionEntity.toString());
                    int Aid = 0;
                    List<AnswerEntity> mlistAnswer = new ArrayList<>();
                    for (String ans : answers) {
                        //System.out.println("Answer scaned: " + ans);
                        AnswerEntity answerEntity = new AnswerEntity(Aid++, Qid-1, ans.substring(2).trim());
                        //AnswerEntity.insert(answerEntity);
                        mlistAnswer.add(answerEntity);
                        //System.out.println(answerEntity.toString());
                    }
                    TestQuestion tq = new TestQuestion(questionEntity,mlistAnswer);
                    testQuestion.add(tq);
                    //System.out.println("Question - out-------------------------------------------------");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        quizTest.setQuestions(testQuestion);
        return quizTest;
    }
}
