package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;


public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "SELECT * FROM users", nativeQuery=true)
    Collection<User> findAllActiveUsers();
}
