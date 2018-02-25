/**
 * This file is part of spring-mvc-examples.
 *
 * springmvc-examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * springmvc-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.springmvc_examples.springmvc_i18n_tiles.form;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
public class Contact {

    private String firstname;
    private String lastname;
    private String email;
    private String telephone;

    /**
     * Método que retorna el valor del atributo firstname.
     *
     * @return valor del atributo firstname.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Método que modifica el valor del atributo firstname.
     *
     * @param firstname valor del atributo firstname.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Método que retorna el valor del atributo lastname.
     *
     * @return valor del atributo lastname.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Método que modifica el valor del atributo lastname.
     *
     * @param lastname valor del atributo lastname.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Método que retorna el valor del atributo email.
     *
     * @return valor del atributo email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que modifica el valor del atributo email.
     *
     * @param email valor del atributo email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que retorna el valor del atributo telephone.
     *
     * @return valor del atributo telephone.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Método que modifica el valor del atributo telephone.
     *
     * @param telephone valor del atributo telephone.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
