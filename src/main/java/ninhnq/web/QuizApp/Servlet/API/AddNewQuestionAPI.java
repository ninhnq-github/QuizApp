package ninhnq.web.QuizApp.Servlet.API;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ninhnq.web.QuizApp.Entity.AnswerEntity;
import ninhnq.web.QuizApp.Entity.QuestionEntity;
import ninhnq.web.QuizApp.helper.HibernateUtility;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.SessionFactory;

@WebServlet(name = "NewQuestionServlet", value = "/AddQuestionAPI")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddNewQuestionAPI extends HttpServlet {

    int fileSizeThreshold = 1024 * 1024;
    int maxFileSize = 1024 * 1024 * 5;
    int maxRequestSize = 1024 * 1024 * 5 * 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(this.getServletName() + "was called...........................................................");
        String UPLOAD_DIRECTORY = "question_bank";
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
        String filename = request.getParameter("questionsFile");
        String bankID_ = request.getParameter("questionsBank");
        int bankID = -1;
        String addnewbank = request.getParameter("addNewBank");
        if (request.getParameter("addNewBank").equals("true")) {

        } else
            bankID = Integer.valueOf(bankID_);

        //System.out.println(uploadPath + filename + " // " + bankID_ + addnewbank);

        List<String> textFile;
        textFile = Files.readAllLines(Path.of(uploadPath + File.separator + filename));
        int q_startID = QuestionEntity.getCurrentID();
        int a_startID = AnswerEntity.getCurrentID();
        for (int line = 0; line <textFile.size(); line++){
            String content = textFile.get(line);
            if (content.trim().equals("")) continue;
            //System.out.println(content);
            String question = content;
            List<String> answers = new ArrayList<>();
            String correct_ans = "", correct="";
            line++;
            while (true){
                String answer = textFile.get(line);
                if (answer.startsWith("ANSWER:")){
                    correct = answer.substring(8).trim();
                    break;
                }
                answers.add(answer);
                line++;
            }
            for (String ans: answers)
                if (ans.startsWith(correct))
                    correct_ans = ans.substring(2).trim();

            QuestionEntity questionEntity = new QuestionEntity(++q_startID,bankID,0,question,correct_ans);
            QuestionEntity.insert(questionEntity);
            System.out.println(questionEntity.toString());
            for (String ans: answers){
                AnswerEntity answerEntity = new AnswerEntity(++a_startID, q_startID, ans.substring(2).trim());
                AnswerEntity.insert(answerEntity);
                System.out.println(answerEntity.toString());
            }
        }

        return;
    }
}
