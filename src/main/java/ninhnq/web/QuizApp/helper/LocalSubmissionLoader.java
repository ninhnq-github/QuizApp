package ninhnq.web.QuizApp.helper;

import ninhnq.web.QuizApp.Entity.Quiztest;
import ninhnq.web.QuizApp.Entity.TestQuestion;

import java.io.*;
import java.util.Scanner;

public class LocalSubmissionLoader {
    public static void load(String baseDir, String uid, String tid, long time, Quiztest quiz){
        String SUBMITION_DIRECTORY = "submission";
        String dirPath = baseDir + SUBMITION_DIRECTORY;
        String filename = tid + "$" + uid + "$" + time + ".txt";
        String filePath = dirPath + File.separator + filename;
        FileInputStream testFile = null;
        //System.out.println("Start to write to: " + filePath);
        try {
            testFile = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(testFile, "UTF8");
            Scanner in = new Scanner(reader);
            quiz.setStart(Long.parseLong(in.nextLine().substring(7).trim()));
            quiz.setEnd(Long.parseLong(in.nextLine().substring(5).trim()));
            for (TestQuestion ques: quiz.getQuestions()){
                if (ques.getQuestion().getType()==-1) continue;
                String choiced = in.nextLine().substring(7).trim();
                ques.setChoiced(choiced);
                //System.out.println(choiced);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
