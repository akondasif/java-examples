/**
 * This file is part of hibernate-examples.
 *
 * hibernate-examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * hibernate-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.hibernate_examples.relationship_one_to_one_mapping.model;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description Clase que modela nuestro objeto de dominio Employee.
 */
public class EmployeeDetail {

    private Long employeeId;
    private String street;
    private String city;
    private String state;
    private String country;
    private Employee employee;

    /**
     * Constructor por defecto
     */
    public EmployeeDetail() {

    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     * @param street Calle en la que vive el empleado.
     * @param city Ciudad en la que vive el empleado.
     * @param state Estado en el que vive el empleado.
     * @param country País en el que vive el empleado.
     */
    public EmployeeDetail(String street, String city, String state, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    /**
     * Método getter del atributo employeeId.
     * @return Identificador del objeto EmployeeDetail.
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Método setter del atributo employeeId.
     * @param employeeId Identificador del objeto EmployeeDetail.
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Método getter del atributo street.
     * @return Calle en la que vive el empleado.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Método setter del atributo street.
     * @param street Calle en la que vive el empleado.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Método getter del atributo city.
     * @return Ciudad en la que vive el empleado.
     */
    public String getCity() {
        return city;
    }

    /**
     * Método setter del atributo city.
     * @param city Ciudad en la que vive el empleado.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Método getter del atributo state.
     * @return Estado en el que vive el empleado.
     */
    public String getState() {
        return state;
    }

    /**
     * Método setter del atributo state.
     * @param state Estado en el que vive el empleado.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Método getter del atributo country.
     * @return País en el que vive el empleado.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Método setter del atributo country.
     * @param country País en el que vive el empleado.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Método getter del atributo employee.
     * @return Objeto que contiene los datos del empleado al que pertenecen 
     * estos datos.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Método setter del atributo employee.
     * @param employee Objeto que contiene los datos del empleado al que pertenecen 
     * estos datos.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
}
