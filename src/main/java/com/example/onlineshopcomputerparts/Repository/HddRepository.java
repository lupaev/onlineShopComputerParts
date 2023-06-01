package com.example.onlineshopcomputerparts.Repository;

import com.example.onlineshopcomputerparts.Entity.Hdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HddRepository extends JpaRepository<Hdd, Long> {

}
