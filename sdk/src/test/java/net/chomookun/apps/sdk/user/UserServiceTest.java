package net.chomookun.apps.sdk.user;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.chomookun.apps.sdk.AppsSdkTest;
import net.chomookun.apps.sdk.core.Pagination;

@Slf4j
public class UserServiceTest extends AppsSdkTest {

    @Autowired
    UserService userService;

    @Test
    public void testGetUsers() throws Exception {
        User user = User.builder().id("id").build();
        Pagination pagination = Pagination.builder().rows(10).page(1).build();
        List<User> users = userService.getUsers(user, pagination);
        log.info("users:{}", users);
    }

}
