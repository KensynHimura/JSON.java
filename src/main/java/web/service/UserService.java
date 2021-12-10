package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user); // добавление юзера

    void deleteUser(User user); // удаление

    void editUser(User user); // изменять

    User getUserById(Long id); // получение юзера по id

    List<User> allUsers(); // выводит всех юзеров

    User findByUserName(String name); // получение юзера по имени
}
