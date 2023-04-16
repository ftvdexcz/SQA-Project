package com.sqa.g06.n03.WaterBilling.repository;

import com.sqa.g06.n03.WaterBilling.config.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Integer> {
}
