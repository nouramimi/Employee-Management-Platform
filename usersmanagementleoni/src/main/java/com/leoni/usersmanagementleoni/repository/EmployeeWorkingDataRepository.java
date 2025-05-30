package com.leoni.usersmanagementleoni.repository;
import com.leoni.usersmanagementleoni.input.EmployeeWorkingData;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

public interface EmployeeWorkingDataRepository extends JpaRepository<EmployeeWorkingData, Integer> {
    boolean existsByUserLoginAndDay(String userLogin, Date day);
    List<EmployeeWorkingData> findAll();

    Page<EmployeeWorkingData> findAll(Pageable pageable);
}
