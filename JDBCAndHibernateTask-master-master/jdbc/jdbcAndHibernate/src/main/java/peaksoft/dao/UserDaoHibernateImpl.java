package peaksoft.dao;

import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Util.getSessionFactory();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS users";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        System.out.println("Successfully added to database: " + user.getName());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.delete(user);
        System.out.println("Successfully deleted");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User").getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "TRUNCATE TABLE users";
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
