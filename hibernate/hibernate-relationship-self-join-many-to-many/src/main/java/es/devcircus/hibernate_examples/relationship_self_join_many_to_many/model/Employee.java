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
package es.devcircus.hibernate_examples.relationship_self_join_many_to_many.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
 
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
    
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="employee_colleague",
        joinColumns={@JoinColumn(name="employee_id")},
        inverseJoinColumns={@JoinColumn(name="colleague_id")})
    private Set<Employee> colleagues = new HashSet<Employee>();
 
    @ManyToMany(mappedBy="colleagues")
    private Set<Employee> teammates = new HashSet<Employee>();
 

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
     * Método getter del atributo colleagues.
     * @return Listado de colegas.
     */
    public Set<Employee> getColleagues() {
        return colleagues;
    }

    /**
     * Método setter del atributo colleagues.
     * @param colleagues Listado de colegas.
     */
    public void setColleagues(Set<Employee> colleagues) {
        this.colleagues = colleagues;
    }

    /**
     * Método getter del atributo teammates.
     * @return Listado de miembros del equipo.
     */
    public Set<Employee> getTeammates() {
        return teammates;
    }

    /**
     * Método setter del atributo teammates.
     * @param teammates Listado de miembros del equipo. 
     */
    public void setTeammates(Set<Employee> teammates) {
        this.teammates = teammates;
    }

}
