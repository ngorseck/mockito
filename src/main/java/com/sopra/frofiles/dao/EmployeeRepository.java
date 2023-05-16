package com.sopra.frofiles.dao;


import com.sopra.frofiles.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Employee findByFirstName(String firstName);
}
