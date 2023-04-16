package com.sqa.g06.n03.WaterBilling.repository;

import com.sqa.g06.n03.WaterBilling.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}