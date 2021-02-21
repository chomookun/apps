package net.chomookun.apps.sdk.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.chomookun.apps.sdk.core.Pagination;

@Service
public class AuthorityService {

    public List<Authority> getAuthorities(Authority authority, Pagination pagination) throws Exception {
        return new ArrayList<Authority>();
    }

    public Authority getAuthority(Authority authority) throws Exception {
        return Authority.builder().id("id").name("name").build();
    }

    public void saveAuthority(Authority authority) throws Exception {
        return;
    }

    public void deleteAuthority(Authority authority) throws Exception {
        return;
    }
}
