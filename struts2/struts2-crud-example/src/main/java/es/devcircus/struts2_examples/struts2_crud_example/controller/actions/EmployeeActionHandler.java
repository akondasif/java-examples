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
package es.devcircus.struts2_examples.struts2_crud_example.controller.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import es.devcircus.struts2_examples.struts2_crud_example.model.employee.vo.Employee;
import es.devcircus.struts2_examples.struts2_crud_example.model.department.service.DefaultDepartmentService;
import es.devcircus.struts2_examples.struts2_crud_example.model.employee.service.DefaultEmployeeService;
import es.devcircus.struts2_examples.struts2_crud_example.model.employee.service.EmployeeService;
import es.devcircus.struts2_examples.struts2_crud_example.model.department.service.DepartmentService;

import java.util.List;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description 
 */
public class EmployeeActionHandler extends ActionSupport implements Preparable {

    private EmployeeService empService = new DefaultEmployeeService();
    private DepartmentService deptService = new DefaultDepartmentService();
    private Employee employee;
    private List employees;
    private List departments;

    /**
     * Método que carga los datos de un empleado si estamos en el caso de 
     * edición, y carga los departamentos en cualquier caso para mostrarlos en
     * el listado.
     * @throws Exception En caso de error.
     */
    @Override
    public void prepare() throws Exception {
        //deparments list will be always displayed, even if validation fails
        departments = deptService.getAllDepartments();
        if (employee != null && employee.getEmployeeId() != null) {
            //retrieves the employee from data source in case of editing and 
            //employee id. exists
            employee = empService.getEmployee(employee.getEmployeeId());
        }
    }

    /**
     * Método que nos permite almacenar los datos de un empleado. Si el 
     * identificador el igual a null, ejecutamos la acción de inserción y 
     * creamos un elemento nuevo en nuestra fuente de datos. Si el identificador
     * del emepleado no es nulo, entendemos que ya existe un empleado en nuestra
     * fuente de datos que se corresponde con el que estamos procesando, con lo
     * que simplemente ejecutamos la acción de actualización del registro.
     * @return Estado de la operación.
     */
    public String save() {
        if (employee.getEmployeeId() == null) {
            empService.insertEmployee(employee);
        } else {
            empService.updateEmployee(employee);
        }
        return SUCCESS;
    }

    /**
     * Método que nos permite eliminar los datos de un empleado de nuestra 
     * fuente de datos.
     * @return Estado de la operación.
     */
    public String delete() {
        empService.deleteEmployee(employee.getEmployeeId());
        return SUCCESS;
    }

    /**
     * Método que recupera la lista de empleados que están almacenados en nuetra
     * fuente de datos.
     * La recuperació la hacemos a través de la variable employees.
     * @return Estado de la operación.
     */
    public String list() {
        employees = empService.getAllEmployees();
        return SUCCESS;
    }

    /**
     * Método getter del atributo employee.
     * @return instancia del objeto empleado.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Método setter del atributo employee.
     * @param employee instancia del objeto empleado.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Método getter del atributo employees.
     * @return instancia del objeto empleados que contiene la lista de los 
     * empleados recuperados desde nuestra fuente de datos.
     */
    public List getEmployees() {
        return employees;
    }

    /**
     * Método getter del atributo departments.
     * @return instancia del objeto departaments que contiene la lista de los
     * departamentos recuperados desde nuestra fuente de datos.
     */
    public List getDepartments() {
        return departments;
    }
}
