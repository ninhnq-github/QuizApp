package ninhnq.web.QuizApp.Entity;

import ninhnq.web.QuizApp.helper.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank", schema = "quizappdb", catalog = "")
public class BankEntity {
    private int id;
    private String name;
    private String author;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 50)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankEntity that = (BankEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    public static List<BankEntity> getAll()
    {
        Transaction transaction = null;
        List<BankEntity> mlist = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<BankEntity> query = session.createQuery("FROM BankEntity");
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

    public static BankEntity getById(int id)
    {
        Transaction transaction = null;
        BankEntity bank = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<BankEntity> query = session.createQuery("FROM BankEntity WHERE BankEntity.id=:rowId");
            query.setParameter("rowId",id);
            bank = query.uniqueResult();
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
        return bank;
    }

    public static boolean insert(BankEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.save(bankEntity);
            transaction.commit();
            System.out.println("Insert <BankEntity> complete");
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

    public static boolean delete(BankEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.delete(bankEntity);
            transaction.commit();
            System.out.println("Delete <BankEntity> complete");
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

    public static boolean update(BankEntity bankEntity)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.update(bankEntity);
            transaction.commit();
            System.out.println("Update <BankEntity> complete");
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
