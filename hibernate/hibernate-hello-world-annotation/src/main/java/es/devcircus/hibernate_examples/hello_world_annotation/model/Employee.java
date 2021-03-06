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
package es.devcircus.hibernate_examples.hello_world_annotation.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description Clase que modela nuestro objeto de dominio Employee.
 */
@Entity
@Table(name="employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name="birth_date")
    private Date birthDate;
    @Column(name="cell_phone")
    private String cellphone;

    /**
     * Constructor por defecto
     */
    public Employee() {
    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     * @param firstname Nombre del empleado.
     * @param lastname Apellidos del empleado.
     * @param birthdate Fecha de nacimiento del empleado.
     * @param phone Número de teléfono del empleado.
     */
    public Employee(String firstname, String lastname, Date birthdate, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthdate;
        this.cellphone = phone;

    }

    /**
     * Método getter del atributo id.
     * @return Identificador del empleado.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método setter del atributo id.
     * @param id Identificador del empleado.
     */
    public void setId(Long id) {
        this.id = id;
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
     * Método getter del atributo birthDate.
     * @return Fecha de nacimiento del empleado.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Método setter del atributo birthDate.
     * @param birthDate Fecha de nacimiento del empleado.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Método getter del atributo cellphone.
     * @return Número de teléfono del empleado.
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * Método setter del atributo cellphone.
     * @param cellphone Número de teléfono del empleado.
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
