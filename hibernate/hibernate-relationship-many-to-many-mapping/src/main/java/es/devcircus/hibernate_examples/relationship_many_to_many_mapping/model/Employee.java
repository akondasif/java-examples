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
package es.devcircus.hibernate_examples.relationship_many_to_many_mapping.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description Clase que modela nuestro objeto de dominio Employee.
 */
public class Employee {

    private Long employeeId;
    private String firstname;
    private String lastname;
    private Set<Meeting> meetings = new HashSet<Meeting>();

    /**
     * Constructor por defecto
     */
    public Employee() {
    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     * @param firstname Nombre del empleado.
     * @param lastname Apellidos del empleado.
     */
    public Employee(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
  
    /**
     * Método getter del atributo employeeId.
     * @return Identificador del empleado.
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Método setter del atributo employeeId.
     * @param employeeId Identificador del empleado.
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Método getter del atributo firstname.
     * @return Nombre del empleado.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Método setter del atributo firstname.
     * @param firstname Apellidos del empleado.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Método getter del atributo lastname.
     * @return Apellidos del empleado.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Método setter del atributo lastname.
     * @param lastname Apellidos del empleado.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Método getter del atributo meetings.
     * @return Lista de reuniones a las que tiene que asistir el empleado.
     */
    public Set<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Método setter del atributo meetings.
     * @param meetings Lista de reuniones a las que tiene que asistir el 
     * empleado.
     */
    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

}
