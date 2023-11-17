package com.example.onlineshopcomputerparts.repository;

import com.example.onlineshopcomputerparts.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для Компьютера
 */
@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

}
