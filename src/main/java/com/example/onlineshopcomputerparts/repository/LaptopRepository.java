package com.example.onlineshopcomputerparts.repository;

import com.example.onlineshopcomputerparts.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для Ноутбука
 */
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
