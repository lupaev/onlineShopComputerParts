package com.example.onlineshopcomputerparts.repository;

import com.example.onlineshopcomputerparts.entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для Монитора
 */
@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

}
