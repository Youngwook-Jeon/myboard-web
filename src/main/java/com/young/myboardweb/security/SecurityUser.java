package com.young.myboardweb.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;
    private com.young.myboardweb.domain.User user;

    public SecurityUser(com.young.myboardweb.domain.User user){
        super(user.getId(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public com.young.myboardweb.domain.User getUser() {
        return user;
    }
}
