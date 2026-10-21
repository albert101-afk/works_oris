package DTO;

import entities.User;

import java.util.List;

public interface UserDto_interface {
    int getCountOfUsers();
    String insertUsers(List<User> users);
    List<User> getUsersByCity(String city);
    List<User> getUsersByAge(int age);
    List<User> getUsersByRole(String role);
}
