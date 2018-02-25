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
package es.devcircus.hibernate_examples.relationship_self_join_one_to_many.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description Clase que modela nuestro objeto de dominio Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue
    private Long employeeId;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="manager_id")
    private Employee manager;
    @OneToMany(mappedBy="manager")
    private Set<Employee> subordinates = new HashSet<Employee>();

    /**
     * Constructor por defecto
     */
    public Employee() {
    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     *
     * @param firstname Nombre del empleado.
     * @param lastname Apellidos del empleado.
     */
    public Employee(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Método getter del atributo employeeId.
     *
     * @return Identificador del empleado.
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * Método setter del atributo employeeId.
     *
     * @param employeeId Identificador del empleado.
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Método getter del atributo firstname.
     *
     * @return Nombre del empleado.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Método setter del atributo firstname.
     *
     * @param firstname Apellidos del empleado.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Método getter del atributo lastname.
     *
     * @return Apellidos del empleado.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Método setter del atributo lastname.
     *
     * @param lastname Apellidos del empleado.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Método getter del atributo manager.
     * @return Manager del empleado.
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * Método setter del atributo manager.
     * @param manager Manager del empleado.
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * Método getter del atributo subordinates.
     * @return Subordinados del empleado.
     */
    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    /**
     * Método setter del atributo subordinates.
     * @param subordinates Subordinados del empleado.
     */
    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

}
