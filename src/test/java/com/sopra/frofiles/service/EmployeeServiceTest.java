package com.sopra.frofiles.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sopra.frofiles.dao.EmployeeRepository;
import com.sopra.frofiles.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest
{
    /**
     * Pour l'injection des mocks
     */
    @InjectMocks
    EmployeeService service;

    /**
     * Pour les mocks
     */
    @Mock
    EmployeeRepository dao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllEmployees()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee("John", "John");
        Employee empTwo = new Employee("Alex", "kolenchiski");
        Employee empThree = new Employee("Steve", "Waugh");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        /**
         * ceci permet de creer une liste qui sera consideree
         * comme une table dans la base de donnees
         */
        when(dao.findAll()).thenReturn(list);

        /**
         * Cette liste sera chargée grace au Mock (dao)
         * SERVICE utilise DAO
         */
        List<Employee> empList = service.findAll();

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    void testCreateOrSaveEmployee()
    {
        Employee employee = new Employee("Ngor","SECK");

        service.save(employee);

        verify(dao, times(1)).save(employee);
    }

    @Test
    void findByFirstName() {
        Employee empEmployeMocker = new Employee("Ngor", "SECK");
        /**
         * ceci permet de creer un objet qui sera consideree
         * comme un tuple table dans la base de donnees
         * when("UN TRAITEMENT").thenReturn("UNE VALAURE");
         */
        when(dao.findByFirstName("Ngor")).thenReturn(empEmployeMocker);

        /**
         * Cet objet sera obwtenu grace au Mock (qui vient du dao)
         * SERVICE utilise DAO
         */
        Employee empService = service.findByFirstName("Ngor");
        assertNotNull(empService);
    }
}