package peaksoft;

import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.model.User;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.saveUser("Aibek", "Erlan uulu", (byte) 18);
//        userDaoHibernate.saveUser("Asan", "Tairov", (byte) 23);
//        userDaoHibernate.saveUser("Uson", "Kamylov", (byte) 23);
//        userDaoHibernate.cleanUsersTable();
//        userDaoHibernate.dropUsersTable();
        userDaoHibernate.createUsersTable();

    }
}
