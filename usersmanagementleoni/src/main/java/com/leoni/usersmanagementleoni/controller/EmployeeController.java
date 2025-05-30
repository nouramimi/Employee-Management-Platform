package com.leoni.usersmanagementleoni.controller;

import com.leoni.usersmanagementleoni.input.EmployeeWorkingData;
import com.leoni.usersmanagementleoni.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeWorkingData>> getAllEmployees() {
        List<EmployeeWorkingData> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeWorkingData> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> ResponseEntity.ok(employee))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeWorkingData> addEmployee(@RequestBody EmployeeWorkingData employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeWorkingData> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeWorkingData employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
