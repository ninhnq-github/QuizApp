package ninhnq.web.QuizApp.Entity;

import ninhnq.web.QuizApp.helper.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "question", schema = "quizappdb", catalog = "")
public class QuestionEntity  implements Serializable {
    private int id;
    private Integer bid;

    public QuestionEntity() { }

    public QuestionEntity(int id, Integer bid, Integer type, String content, String answer) {
        this.id = id;
        this.bid = bid;
        this.type = type;
        this.content = content;
        this.answer = answer;
    }

    private Integer type;
    private String content;
    private String answer;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bid", nullable = true)
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "answer", nullable = true, length = 1000)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (id != that.id) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    public static int getCurrentID()
    {
        Transaction transaction = null;
        int id = 0;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            org.hibernate.query.Query<Integer> query = session.createQuery("SELECT MAX(id) FROM QuestionEntity");
            id = (int) query.uniqueResult();
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        }
        finally
        {
            session.close();
        }
        return id;
    }

    public static List<QuestionEntity> getAll()
    {
        Transaction transaction = null;
        List<QuestionEntity> mlist = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            org.hibernate.query.Query<QuestionEntity> query = session.createQuery("FROM QuestionEntity");
            mlist = query.getResultList();
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return mlist;
    }

    public static List<QuestionEntity> get_by_bank(int id)
    {
        Transaction transaction = null;
        List<QuestionEntity> mlist = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM QuestionEntity WHERE bid=:bank");
            query.setParameter("bank",id);
            mlist = query.getResultList();
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return mlist;
    }

    public static  QuestionEntity getById(int id)
    {
        Transaction transaction = null;
        QuestionEntity ques = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query< QuestionEntity> query = session.createQuery("FROM QuestionEntity WHERE QuestionEntity.id=:rowId");
            query.setParameter("rowId",id);
            ques = query.uniqueResult();
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return ques;
    }

    public static boolean insert( QuestionEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.save(bankEntity);
            transaction.commit();
            System.out.println("Insert < QuestionEntity> complete");
            return true;
        }
        catch (Exception e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
    }

    public static boolean delete( QuestionEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.delete(bankEntity);
            transaction.commit();
            System.out.println("Delete < QuestionEntity> complete");
            return true;
        }
        catch (Exception e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
    }

    public static boolean update( QuestionEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.update(bankEntity);
            transaction.commit();
            System.out.println("Update < QuestionEntity> complete");
            return true;
        }
        catch (Exception e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
    }

    public String getQuestionText(int id){
        String question = "Question " + id + ". " + this.content + "\n";
        List<AnswerEntity> answers = AnswerEntity.get_by_question(this.id);
        char c = 'A', correct='0';
        for (AnswerEntity ans:answers){
            question += c + ". " + ans.getContent() + "\n";
            if (ans.getContent().equals(this.answer))
                correct = c;
            c++;
        }
        question += "ANSWER: " + correct + "\n";
        return question;
    }

}
