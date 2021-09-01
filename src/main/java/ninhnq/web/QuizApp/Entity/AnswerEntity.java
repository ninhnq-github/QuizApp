package ninhnq.web.QuizApp.Entity;

import ninhnq.web.QuizApp.helper.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "answer", schema = "quizappdb", catalog = "")
public class AnswerEntity  implements Serializable {
    private int id;

    public AnswerEntity(){}

    public AnswerEntity(int id, Integer qid, String content) {
        this.id = id;
        this.qid = qid;
        this.content = content;
    }

    private Integer qid;
    private String content;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "qid", nullable = true)
    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (id != that.id) return false;
        if (qid != null ? !qid.equals(that.qid) : that.qid != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (qid != null ? qid.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
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
            org.hibernate.query.Query<Integer> query = session.createQuery("SELECT MAX(id) FROM AnswerEntity ");
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

    public static List<AnswerEntity> getAll()
    {
        Transaction transaction = null;
        List<AnswerEntity> mlist = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            org.hibernate.query.Query<AnswerEntity> query = session.createQuery("FROM AnswerEntity");
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

    public static List<AnswerEntity> get_by_question(int id)
    {
        Transaction transaction = null;
        List<AnswerEntity> mlist = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            org.hibernate.query.Query<AnswerEntity> query = session.createQuery("FROM AnswerEntity WHERE qid=:ques");
            query.setParameter("ques",id);
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

    public static  AnswerEntity getById(int id)
    {
        Transaction transaction = null;
        AnswerEntity ques = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<AnswerEntity> query = session.createQuery("FROM AnswerEntity WHERE AnswerEntity.id=:rowId");
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

    public static boolean insert( AnswerEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.save(bankEntity);
            transaction.commit();
            System.out.println("Insert < AnswerEntity> complete");
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

    public static boolean delete( AnswerEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.delete(bankEntity);
            transaction.commit();
            System.out.println("Delete < AnswerEntity> complete");
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

    public static boolean update( AnswerEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.update(bankEntity);
            transaction.commit();
            System.out.println("Update < AnswerEntity> complete");
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
}
