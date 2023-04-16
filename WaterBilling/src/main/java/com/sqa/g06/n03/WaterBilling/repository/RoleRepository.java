package com.sqa.g06.n03.WaterBilling.repository;

import com.sqa.g06.n03.WaterBilling.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer id);
    Optional<Role> findByName(String name);
}
