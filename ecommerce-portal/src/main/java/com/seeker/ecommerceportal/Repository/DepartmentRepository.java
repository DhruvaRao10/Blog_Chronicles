package com.seeker.ecommerceportal.Repository;

import com.seeker.ecommerceportal.entity.Department;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DepartmentRepository  extends  JpaRepository<Department,Long> {
    public Department findBydepartmentNameIgnoreCase(String departmentName);
}
