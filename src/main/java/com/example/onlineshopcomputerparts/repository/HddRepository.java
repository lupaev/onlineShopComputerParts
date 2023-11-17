package com.example.onlineshopcomputerparts.repository;

import com.example.onlineshopcomputerparts.entity.Hdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для Жесткого диска
 */
@Repository
public interface HddRepository extends JpaRepository<Hdd, Long> {

}
