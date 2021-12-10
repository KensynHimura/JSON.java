package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> allUsers(); // выводит всех юзеров

    void addUser(User user); // добавление юзера

    void deleteUser(User user); // удаление

    void editUser(User user); // изменять

    User getUserById(Long id); // получение юзера по id

    User findByUserName(String name); // получение юзера по имени

}
