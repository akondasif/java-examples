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
 * Clase Main.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class Main {

    /**
     * Función principal que ejecuta el código del ejemplo.
     *
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        // Recuperamos la instancia del singleton.
        SingletonObject object = SingletonObject.getInstance();
        // Invocamos al método que se encarga de mostrar el mensaje por pantalla.
        object.showMessage();
    }
}
