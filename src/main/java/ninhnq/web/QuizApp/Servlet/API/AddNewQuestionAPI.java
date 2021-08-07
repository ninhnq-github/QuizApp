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
import java.util.List;

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
        int bankID = Integer.valueOf(bankID_);

    }
}
