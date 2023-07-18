package com.seeker.ecommerceportal.service;

import com.seeker.ecommerceportal.Repository.DepartmentRepository;
import com.seeker.ecommerceportal.entity.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;
    @AfterEach
    void setUp() {
        Department department = Department.builder().
                departmentName("IT").
                departmentAddress("Bengaluru").
                departmentCode("IT-01").
                departmentId(1L).
                build();
        Mockito.when(departmentRepository.findBydepartmentNameIgnoreCase("IT")).thenReturn(department);
    }

   // @Autowired
    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("Validate get Department by name ignore case")
    public void whenValidDepartmentName_thenDepartmentShouldBeFound(){
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByNameIgnoreCase(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }
}