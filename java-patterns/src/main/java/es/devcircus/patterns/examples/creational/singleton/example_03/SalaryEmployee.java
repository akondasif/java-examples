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
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class SalaryEmployee extends Employee {

    public double salary;

    /**
     * Constructor por defecto.
     */
    public SalaryEmployee() {
    }

    /**
     * Método que retorna el valor del atributo salary.
     *
     * @return Valor del atributo salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Método que modifica el valor del atributo salary.
     *
     * @param salary Nuevo valor del atributo salary.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

}
