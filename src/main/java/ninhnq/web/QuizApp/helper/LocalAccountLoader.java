package ninhnq.web.QuizApp.helper;

import ninhnq.web.QuizApp.Entity.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocalAccountLoader {
    public static List<Account> load_A(String baseDir){
        String DATA_DIR = "data";
        String dirPath = baseDir + DATA_DIR;
        String filename = "Account.txt";
        String filePath = dirPath + File.separator + filename;
        List<Account> mlist = new ArrayList<>();
        try {
            FileInputStream filestream = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(filestream,"UTF8");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()){
                String line = in.nextLine();
                String[] splited = line.split(";");
                Account account = new Account(splited[0],splited[1],splited[2]);
                mlist.add(account);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mlist;
    }
    public static List<Account> load(String baseDir){
        String DATA_DIR = "data";
        String dirPath = baseDir + DATA_DIR;
        String filename = "LOGIN.txt";
        String filePath = dirPath + File.separator + filename;
        List<Account> mlist = new ArrayList<>();
        try {
            FileInputStream filestream = new FileInputStream(filePath);
            InputStreamReader reader = new InputStreamReader(filestream,"UTF8");
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()){
                String line = in.nextLine();
                String[] splited = line.split(",");
                Account account = new Account(splited[0],splited[1],splited[2], splited[3]);
                mlist.add(account);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mlist;
    }
}
