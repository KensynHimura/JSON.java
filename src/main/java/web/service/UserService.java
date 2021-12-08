package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user); // добавление юзера

    public void deleteUser(User user); // удаление

    public void updateUser(User user); // изменять

    public User getUserById(Long id); // получение юзера по id

    public List<User> allUsers(); // выводит всех юзеров
}
