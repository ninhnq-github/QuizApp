package ninhnq.web.QuizApp.helper;

import ninhnq.web.QuizApp.Entity.QuizHeader;
import ninhnq.web.QuizApp.Entity.Quiztest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocalAssignLoader {
    public static List<QuizHeader> load(String baseDir, String uid){
        String DATA_DIR = "data";
        String dirPath = baseDir + DATA_DIR;
        String filename = "ASSIGN.txt";
        String filePath = dirPath + File.separator + filename;
        List<QuizHeader> mlist = new ArrayList<>();
        try {
            FileInputStream filestream = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(filestream,"UTF8");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()){
                String line = in.nextLine();
                String[] splited = line.split(",");
                if (splited[0].equals(uid))
                    for (int i=1; i<splited.length; i++) {
                        if (splited[i].trim().equals("")) continue;
                        QuizHeader quiz = LocalQuizTestLoader.load_header(baseDir,splited[i]);
                        mlist.add(quiz);
                    }
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mlist;
    }
}
