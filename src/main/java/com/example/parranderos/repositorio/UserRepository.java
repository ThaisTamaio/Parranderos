package com.example.parranderos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;
import com.example.parranderos.modelo.User;


public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "SELECT * FROM users", nativeQuery=true)
    Collection<User> findAllActiveUsers();
}
