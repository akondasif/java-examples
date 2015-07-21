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
package es.devcircus.patterns.examples.creational.singleton.example_01;

/**
 * Clase que implementa el patrón singleton.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class SingletonObject {

    // Variable estática de tipo SingleObject
    private static final SingletonObject instance = new SingletonObject();

    /**
     * Constructor privado para que nadie pueda instanciarlo
     */
    private SingletonObject() {
    }

    /**
     * Metodo que permite acceder a la instancia interna.
     *
     * @return Instancia de tipo SingletonObject
     */
    public static SingletonObject getInstance() {
        return instance;
    }

    /**
     * Metodo que muestra un mensaje por pantalla.
     */
    public void showMessage() {
        System.out.println("Hello world!!!");
    }
}
