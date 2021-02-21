package net.chomookun.apps.sdk.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.chomookun.apps.sdk.core.Pagination;

@Service
public class RoleService {

    public List<Role> getRoles(Role role, Pagination pagination) throws Exception {
        return new ArrayList<Role>();
    }

    public Role getRole(Role role) throws Exception {
        return Role.builder().id("id").name("name").build();
    }

    public void saveRole(Role role) throws Exception {
        return;
    }

    public void deleteRole(Role role) throws Exception {
        return;
    }
}
