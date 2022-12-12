package dismefront.web3.data;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class DataBaseManager implements Serializable {

    private SessionFactory factory;
    private Session session;

    private LinkedList<Attempt> attempts = new LinkedList<>();

    public DataBaseManager() {
        try {
            this.factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Attempt.class)
                    .buildSessionFactory();

            this.createSession();
        } catch (Exception e) {
            System.out.println("Exception during session factory init: " + e.getMessage());
        }
    }

    private void createSession() {
        this.session = factory.getCurrentSession();
    }

    public void addAttempt(Attempt attempt) {
        createSession();

        System.out.println("Start saving hit");

        this.session.beginTransaction();
        this.session.save(attempt);
        this.session.getTransaction().commit();

        attempts.addFirst(attempt);
    }

    public LinkedList<Attempt> getAttempts() {
        createSession();
        session.beginTransaction();
        attempts.clear();
        for (Attempt x : session
                .createQuery("select a from Attempt a", Attempt.class)
                .getResultList())
            attempts.addFirst(x);
        session.getTransaction().commit();
        return attempts;
    }

    public void setAttempts(LinkedList<Attempt> attempts) {
        this.attempts = attempts;
    }

    public void clearAttempts() {
        createSession();
        session.beginTransaction();
        session.createQuery("delete from Attempt").executeUpdate();
        session.getTransaction().commit();
        this.attempts.clear();
    }

    public String getJsonAttempts() {
        Gson parser = new Gson();
        return parser.toJson(attempts);
    }

}
