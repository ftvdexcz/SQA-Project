package com.sqa.g06.n03.WaterBilling.repository;

import com.sqa.g06.n03.WaterBilling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRoleId(Integer id);

    List<User> findByUsername(String username);
}
