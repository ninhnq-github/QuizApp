package ninhnq.web.QuizApp.helper;

import ninhnq.web.QuizApp.Entity.Account;
import ninhnq.web.QuizApp.Entity.QuizResult;
import ninhnq.web.QuizApp.Entity.Quiztest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AppUtils {

    public static String getNextDate(int days){
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(getCurrentDateTime("yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) return "1997-01-01";
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return format.format(calendar.getTime());
    }

    public static String getCurrentDateTime()
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static String getTimeLeft(Long timeStart, Long limit)
    {
        Long sub = limit*60*1000 - (System.currentTimeMillis() - timeStart);
        if (sub <0) return "TIMEOUT";
        int h = (int) (sub/(60*60*1000));
        sub = sub%(60*60*1000);
        int m = (int) (sub/(60*1000));
        sub = sub%(60*1000);
        int s = (int) (sub/(1000));
        String time = h+":"+m+":"+s;
        return time;
    }

    public static String getCurrentDateTime(String format)
    {
        SimpleDateFormat formatter= new SimpleDateFormat(format);
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static long getTimeMilis(String time, String pattern)  {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = (Date)formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mills = date.getTime();
        return mills;
    }

    public static String getDateTimeFormat(long time, String pattern){
        SimpleDateFormat formatter= new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return formatter.format(date);
    }

    public static void storeSession(HttpSession session, String baseDir){
        Account user = (Account) session.getAttribute("user");
        String dir_path = baseDir + "session_backup";
        String file_name = user.getAccount() + ".txt";
        LocalObjectWriter writer = new LocalObjectWriter(dir_path+ File.separator + file_name);
        writer.write(session.getAttribute("quizTest"),false);
    }

    public static void RestoreSession(HttpSession session, String baseDir){
        Account user = (Account) session.getAttribute("user");
        String dir_path = baseDir + "session_backup";
        String file_name = user.getAccount() + ".txt";
        LocalObjectReader reader = new LocalObjectReader(dir_path + File.separator +file_name);
        Quiztest quiz = (Quiztest) reader.read();
        if (quiz!=null) session.setAttribute("quizTest",quiz);
    }

    public static List<QuizResult> loadResult(HttpSession session, String baseDir){
        Account user = (Account) session.getAttribute("user");
        String dir_path = baseDir + "history";
        String file_name = user.getAccount() + ".txt";
        List<QuizResult> listReult = new ArrayList<>();
        LocalObjectReader reader = new LocalObjectReader(dir_path + File.separator +file_name);
        try {
            List<Object> mlis = reader.readList();
            for (Object obj: mlis)
                listReult.add((QuizResult) obj);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        System.out.println("Result has been loaded successfully: " + listReult.size());
        return listReult;
    }
}
