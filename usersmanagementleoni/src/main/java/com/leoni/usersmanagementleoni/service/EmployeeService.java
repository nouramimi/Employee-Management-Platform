package com.leoni.usersmanagementleoni.service;

import com.leoni.usersmanagementleoni.input.EmployeeWorkingData;
import com.leoni.usersmanagementleoni.input.ExcelReader;
import com.leoni.usersmanagementleoni.repository.EmployeeWorkingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    private EmployeeWorkingDataRepository repository;
    @Autowired
    public EmployeeService(EmployeeWorkingDataRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeWorkingData> readAndProcessExcelFile(String filePath) throws IOException {
        return ExcelReader.readExcelFile(filePath);
    }
    @Transactional
    public void saveEmployeeData(List<EmployeeWorkingData> employeeList) {
        for (EmployeeWorkingData employee : employeeList) {
            if (!repository.existsByUserLoginAndDay(employee.getUserLogin(), employee.getDay())) {
                repository.save(employee);
            }
        }
    }

    public List<EmployeeWorkingData> getAllEmployees() {
        return repository.findAll();
    }

    // Retrieve a single employee by ID
    public Optional<EmployeeWorkingData> getEmployeeById(Integer id) {
        return repository.findById(id);
    }

    // Update an existing employee
    @Transactional
    public EmployeeWorkingData updateEmployee(Integer id, EmployeeWorkingData updatedEmployee) {
        return repository.findById(id).map(employee -> {
            employee.setUserName(updatedEmployee.getUserName());
            employee.setUserLogin(updatedEmployee.getUserLogin());
            employee.setWorkedHours(updatedEmployee.getWorkedHours());
            employee.setDay(updatedEmployee.getDay());
            return repository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Delete an employee by ID
    @Transactional
    public void deleteEmployee(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with id " + id);
        }
    }
    @Transactional
    public EmployeeWorkingData addEmployee(EmployeeWorkingData employee) {
        return repository.save(employee);
    }

}
