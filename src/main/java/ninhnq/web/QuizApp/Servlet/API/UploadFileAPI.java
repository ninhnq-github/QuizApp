package ninhnq.web.QuizApp.Servlet.API;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UploadFileServlet", value = "/UploadAPI")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFileAPI extends HttpServlet {

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
        if (ServletFileUpload.isMultipartContent(request)||true) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(fileSizeThreshold);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(maxFileSize);
            upload.setSizeMax(maxRequestSize);
            String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            System.out.println(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            List<FileItem> formItems = null;
            try {
                formItems = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        System.out.println(filePath);
                        File storeFile = new File(filePath);
                        try {
                            item.write(storeFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        request.setAttribute("message", "File "
                                + fileName + " has uploaded successfully!");
                        PrintWriter out = response.getWriter();
                        out.print("{\"status\":1, \"filename\":\""+ fileName +"\"}");
                    }
                }
            }
            return;
        }
        PrintWriter out = response.getWriter();
        out.print("{\"status\":0}");
        return;
    }
}
