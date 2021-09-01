package ninhnq.web.QuizApp.Servlet.Controller;

import ninhnq.web.QuizApp.Entity.QuestionEntity;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@WebServlet(name = "QuizServlet", value = "/QuizServlet")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        //System.out.println(this.getServletName() + "was called...........................................................");
        List<QuestionEntity> part1_q, part2_q;
        part1_q = QuestionEntity.get_by_bank(1);
        //System.out.println(this.getServletName() + ": PART1 ------" + part1_q.size());
        part2_q = QuestionEntity.get_by_bank(0);
        //System.out.println(this.getServletName() + ": PART2 ------" + part2_q.size());

        QuestionEntity[] part1 = new QuestionEntity[part1_q.size()];
        for (int i=0; i<part1_q.size(); i++) part1[i] = part1_q.get(i);

        QuestionEntity[] part2 = new QuestionEntity[part2_q.size()];
        for (int i=0; i<part2_q.size(); i++) part2[i] = part2_q.get(i);

        //System.out.println("This is the size of part1: " + part1_q.size());
        //System.out.println("This is the size of part2: " + part2_q.size());

        Random rand = new Random();
        for (int i=0; i<100; i++){
            int a = rand.nextInt(part1_q.size());
            int b = rand.nextInt(part1_q.size());
            QuestionEntity q = part1[a];
            part1[a] = part1[b];
            part1[b] = q;
        }

        for (int i=0; i<100; i++){
            int a = rand.nextInt(part2_q.size());
            int b = rand.nextInt(part2_q.size());
            QuestionEntity q = part2[a];
            part2[a] = part2[b];
            part2[b] = q;
        }

        String part1_title = "PART 1: ERROR RECOGNITION / DIRECTIONS: Identify the one underlined word or phrase A, B, C, or D that should be corrected or rewritten.";
        String part2_title = "PART 2: GRAMMAR AND VOCABULARY / DIRECTIONS: Choose the best option to complete each following sentence.";
        String part3_title = "PART 3: GAP FILING / DIRECTIONS: Read the following passage and choose the best option for each blank to complete the passage.";
        String part4_title = "PART 4: READING COMPREHENSION / DIRECTIONS: Read the passage carefully and choose the correct answer.";

        String[] Test = new String[5];

        PrintWriter out = response.getWriter();

        for (int i=0; i<5; i++)
        {
            Test[i] = "TEST 0" + i + "\n" + "\n";
            Test[i] += part1_title + "\n" + "\n";
            for (int j=0; j<5; j++)
                Test[i] += part1[i*5+j].getQuestionText(j+1) + "\n";
            Test[i] += part2_title + "\n" + "\n";
            for (int j=0; j<10; j++)
                Test[i] += part2[i*10+j].getQuestionText(j+6) + "\n";
            Test[i] += "\n";
            Test[i] += part3_title + "\n" + "\n";
            Test[i] += "\n";
            Test[i] += part4_title + "\n" + "\n";

            out.println(Test[i].replace("\n","<br>"));
            //System.out.println(Test[i]);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
