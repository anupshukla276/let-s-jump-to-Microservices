package com.anup.user.service.repositories;

import com.anup.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    // if you want to write any custome method You can  write here

}
