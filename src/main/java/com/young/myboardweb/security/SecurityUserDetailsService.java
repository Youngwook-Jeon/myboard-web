package com.young.myboardweb.security;

import com.young.myboardweb.domain.User;
import com.young.myboardweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findById(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        } else {
            User user = optional.get();
            return new SecurityUser(user);
        }

    }
}
