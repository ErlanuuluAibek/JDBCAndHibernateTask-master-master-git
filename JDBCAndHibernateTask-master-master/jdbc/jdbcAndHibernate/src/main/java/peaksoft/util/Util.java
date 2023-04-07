package peaksoft.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import peaksoft.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String USER_NAME = "postgres";

    private static final String PASSWORD = "2518";


    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connected to the postgresSQL successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    private static SessionFactory sess() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory =  new Configuration().configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
    public static SessionFactory getSessionFactory(){
        return sess();
    }
}
