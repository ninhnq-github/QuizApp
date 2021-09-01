package ninhnq.web.QuizApp.helper;

import java.io.*;

public class LocalObjectWriter {
    String file_path;
    ObjectOutputStream writer;

    public void write(Object object, boolean append){
        File file = new File(file_path);
        if (file.exists() && append)
        {
            try{
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file_path,append)){
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
                writer.writeObject(object);
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                file.createNewFile();
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file_path,append));
                writer.writeObject(object);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public LocalObjectWriter(String file_path){
        this.file_path = file_path;
    }

    public LocalObjectWriter() {
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public ObjectOutputStream getWriter() {
        return writer;
    }

    public void setWriter(ObjectOutputStream writer) {
        this.writer = writer;
    }
}
