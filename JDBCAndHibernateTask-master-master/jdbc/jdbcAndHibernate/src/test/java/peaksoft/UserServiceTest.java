package peaksoft;

import org.junit.Assert;
import org.junit.Test;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.List;

public class UserServiceTest {

    private final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    private final String testName = "Kanat";
    private final String testLastName = "Subanov";
    private final byte testAge = 23;

    @Test
    public void dropUsersTable() {
        try {
            userDaoHibernate.dropUsersTable();
            userDaoHibernate.dropUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userDaoHibernate.dropUsersTable();
            userDaoHibernate.createUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userDaoHibernate.dropUsersTable();
            userDaoHibernate.createUsersTable();
            userDaoHibernate.saveUser(testName, testLastName, testAge);

            User user = userDaoHibernate.getAllUsers().get(0);

            if (!testName.equals(user.getName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userDaoHibernate.dropUsersTable();
            userDaoHibernate.createUsersTable();
            userDaoHibernate.saveUser(testName, testLastName, testAge);
            userDaoHibernate.removeUserById(1L);
        } catch (Exception e) {
            Assert.fail("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userDaoHibernate.dropUsersTable();
            userDaoHibernate.createUsersTable();
            userDaoHibernate.saveUser(testName, testLastName, testAge);
            List<User> userList = userDaoHibernate.getAllUsers();

            if (userList.size() != 1) {
                Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            Assert.fail("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userDaoHibernate.dropUsersTable();
            userDaoHibernate.createUsersTable();
            userDaoHibernate.saveUser(testName, testLastName, testAge);
            userDaoHibernate.cleanUsersTable();

            if (userDaoHibernate.getAllUsers().size() != 0) {
                Assert.fail("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            Assert.fail("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }

    }
}
