package com.test.springboot.cache.mapper;

import com.test.springboot.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("UPDATE employee SET last_name = #{lastName}, email=#{email},gender=#{gender},dept_id=#{deptId} WHERE id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    public void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(last_name, email, gender, dept_id) VALUES(#{lastName},#{email},#{gender},#{deptId})")
    public void insertEmp(Employee emp);

    @Select("SELECT * FROM employee WHERE last_name = #{lastName}")
    Employee getEmpByLastName(String lastName);
}
