package net.chomookun.apps.sdk.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.chomookun.apps.sdk.core.Pagination;

@Service
public class UserService {

    public List<User> getUsers(User user, Pagination pagination) throws Exception {
        return new ArrayList<User>();
    }

    public User getUser(User user) throws Exception {
        return User.builder().id("test").name("test_name").build();
    }

    public void saveUser(User user) throws Exception {
        return;
    }

    public void deleteUser(User user) throws Exception {
        return;
    }
}
