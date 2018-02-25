/**
 * This file is part of struts2-examples.
 *
 * struts2-examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * struts2-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.struts2_examples.struts2_crud_example.model.employee.vo;

import es.devcircus.struts2_examples.struts2_crud_example.model.department.vo.Department;
import java.io.Serializable;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description 
 */
public class Employee implements Serializable {

    private Integer employeeId;
    private Integer age;
    private String firstName;
    private String lastName;
    private Department department;

    /**
     * 
     */
    public Employee() {
    }

    /**
     * 
     * @param employeeId
     * @param firstName
     * @param lastName
     * @param age
     * @param department 
     */
    public Employee(Integer employeeId, String firstName, String lastName, Integer age, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.department = department;
    }

    /**
     * 
     * @return 
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * 
     * @param department 
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * 
     * @return 
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * 
     * @param employeeId 
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * 
     * @return 
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * @param age 
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 
     * @return 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
