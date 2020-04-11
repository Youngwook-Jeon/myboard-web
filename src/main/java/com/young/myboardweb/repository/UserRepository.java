package com.young.myboardweb.repository;

import com.young.myboardweb.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
