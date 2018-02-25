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
package es.devcircus.hibernate_examples.multiple_configuration_files.model.person.vo;

import java.sql.Date;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description Clase que modela nuestro objeto de dominio Employee.
 */
public class Person {

    private Long id;
    private String firstname;
    private String lastname;
    private Date birthDate;
    private String cellphone;

    /**
     * Constructor por defecto
     */
    public Person() {
    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     *
     * @param firstname Nombre de la persona.
     * @param lastname Apellidos de la persona.
     * @param birthdate Fecha de nacimiento de la persona.
     * @param phone Número de teléfono de la persona.
     */
    public Person(String firstname, String lastname, Date birthdate, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthdate;
        this.cellphone = phone;

    }

    /**
     * Método getter del atributo id.
     *
     * @return Identificador de la persona.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método setter del atributo id.
     *
     * @param id Identificador de la persona.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método getter del atributo firstname.
     *
     * @return Nombre de la persona.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Método setter del atributo firstname.
     *
     * @param firstname Apellidos de la persona.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Método getter del atributo lastname.
     *
     * @return Apellidos de la persona.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Método setter del atributo lastname.
     *
     * @param lastname Apellidos de la persona.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Método getter del atributo birthDate.
     *
     * @return Fecha de nacimiento de la persona.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Método setter del atributo birthDate.
     *
     * @param birthDate Fecha de nacimiento de la persona.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Método getter del atributo cellphone.
     *
     * @return Número de teléfono de la persona.
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * Método setter del atributo cellphone.
     *
     * @param cellphone Número de teléfono de la persona.
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

}
