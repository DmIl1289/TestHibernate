package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        User user1 = new User("Олег", "Бакунин", (byte) 30);
        User user2 = new User("Георгий", "Утка", (byte) 49);
        User user3 = new User("Александр", "Несокрушимый", (byte) 29);
        User user4 = new User("Павел", "Падший", (byte) 60);

        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();

        user.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем " + user1.getName() + " добавлен добавлен в базу данных");
        user.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем " + user2.getName() + " добавлен добавлен в базу данных");
        user.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем " + user3.getName() + " добавлен добавлен в базу данных");
        user.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем " + user4.getName() + " добавлен добавлен в базу данных");

        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}

