/**
 * This file is part of Java Examples.
 *
 * Java Examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * Java Examples is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.patterns.examples.creational.singleton.example_03;

/**
 * Clase que implementa el patrón singleton.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class Employee {

    // Varibale singleton
    public static final int companyID = 12345;
    public String address;

    /**
     * Constructor por defecto.
     */
    public Employee() {
    }

    /**
     * Método que retorna el valor del atributo address.
     *
     * @return Valor del atributo address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Método que modifica el valor del atributo address.
     *
     * @param address Nuevo valor del atributo address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
