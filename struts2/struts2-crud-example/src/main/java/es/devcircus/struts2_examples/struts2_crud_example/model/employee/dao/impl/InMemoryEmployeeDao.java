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
package es.devcircus.struts2_examples.struts2_crud_example.model.employee.dao.impl;

import es.devcircus.struts2_examples.struts2_crud_example.model.department.vo.Department;
import es.devcircus.struts2_examples.struts2_crud_example.model.employee.vo.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import es.devcircus.struts2_examples.struts2_crud_example.model.department.dao.IDepartmentDao;
import es.devcircus.struts2_examples.struts2_crud_example.model.department.dao.impl.InMemoryDepartmentDao;
import es.devcircus.struts2_examples.struts2_crud_example.model.employee.dao.IEmployeeDao;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description
 */
public class InMemoryEmployeeDao implements IEmployeeDao {

    private static Map<Integer, Department> departmentsMap;
    private static ArrayList<Employee> employees;

    /**
     *
     */
    static {
        employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "John", "Doe", 36, new Department(100, "Accounting")));
        employees.add(new Employee(2, "Bob", "Smith", 25, new Department(300, "Sales")));
        IDepartmentDao deptDao = new InMemoryDepartmentDao();
        departmentsMap = deptDao.getDepartmentsMap();
    }

    /**
     *
     * @return
     */
    @Override
    public List getAllEmployees() {
        return employees;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Employee getEmployee(Integer id) {
        Employee emp = null;
        for (Employee employee : employees) {
            emp = employee;
            if (emp.getEmployeeId().equals(id)) {
                break;
            }
        }
        return emp;
    }

    /**
     *
     * @param emp
     */
    @Override
    public void update(Employee emp) {
        Integer id = emp.getEmployeeId();
        for (int i = 0; i < employees.size(); i++) {
            Employee tempEmp = employees.get(i);
            if (tempEmp.getEmployeeId().equals(id)) {
                emp.setDepartment(departmentsMap.get(emp.getDepartment().getDepartmentId()));
                employees.set(i, emp);
                break;
            }
        }
    }

    /**
     *
     * @param emp
     */
    @Override
    public void insert(Employee emp) {
        int lastId = 0;
        for (Employee temp : employees) {
            if (temp.getEmployeeId() > lastId) {
                lastId = temp.getEmployeeId();
            }
        }
        emp.setDepartment(departmentsMap.get(emp.getDepartment().getDepartmentId()));
        emp.setEmployeeId(lastId + 1);
        employees.add(emp);
    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        for (int i = 0; i < employees.size(); i++) {
            Employee tempEmp = employees.get(i);
            if (tempEmp.getEmployeeId().equals(id)) {
                employees.remove(i);
                break;
            }
        }
    }
}
