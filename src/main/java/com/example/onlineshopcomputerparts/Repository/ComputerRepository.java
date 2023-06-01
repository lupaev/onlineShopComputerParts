package com.example.onlineshopcomputerparts.Repository;

import com.example.onlineshopcomputerparts.Entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

}
