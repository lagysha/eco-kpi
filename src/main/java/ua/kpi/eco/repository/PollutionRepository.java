package ua.kpi.eco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.eco.model.Pollution;

public interface PollutionRepository extends JpaRepository<Pollution, Long> {
}