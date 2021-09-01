package ninhnq.web.QuizApp.helper;

import ninhnq.web.QuizApp.Entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtility {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/quizappdb");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "nmastera2000");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(AccountEntity.class);
                configuration.addAnnotatedClass(AnswerEntity.class);
                configuration.addAnnotatedClass(AssignEntity.class);
                configuration.addAnnotatedClass(BankEntity.class);
                configuration.addAnnotatedClass(QuestionEntity.class);
                configuration.addAnnotatedClass(Quiztest.class);
                configuration.addAnnotatedClass(ResultEntity.class);
                configuration.addAnnotatedClass(TestQuestion.class);
                configuration.addAnnotatedClass(UserEntity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
