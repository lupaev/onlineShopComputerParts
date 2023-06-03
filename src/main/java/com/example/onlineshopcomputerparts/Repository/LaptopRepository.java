package com.example.onlineshopcomputerparts.Repository;

import com.example.onlineshopcomputerparts.Entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * репозиторий для Ноутбука
 */
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
