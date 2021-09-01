package ninhnq.web.QuizApp.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalObjectReader {
    String file_path;
    ObjectInputStream reader;

    public List<Object> readList() throws IOException {
        List<Object> mlist = new ArrayList<>();
        try {
            this.reader = new ObjectInputStream(new FileInputStream(file_path));
            while (true)
            {
                Object obj = reader.readObject();
                mlist.add(obj);
            }
        }
        catch (ClassNotFoundException | EOFException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        if (reader!=null) reader.close();
        return mlist;
    }

    public Object read(){
        try
        {
            reader = new ObjectInputStream(new FileInputStream(file_path));
            Object obj = reader.readObject();
            return obj;
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public LocalObjectReader(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public ObjectInputStream getReader() {
        return reader;
    }

    public void setReader(ObjectInputStream reader) {
        this.reader = reader;
    }
}
