package com.example.onlineshopcomputerparts.Repository;

import com.example.onlineshopcomputerparts.Entity.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

}
