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
package es.devcircus.patterns.examples.creational.singleton.example_02;

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
        // Solicitamos que se cree la primera instancia de Connection. En este
        // caso deberíamos de poder crear la nueva instancia sin problemas.
        try {
            // Se llama al constructor de la clase.
            Connection con = new Connection(); //ok
        } catch (Exception e) {
            System.out.println("first: " + e.getMessage());
        }
        // Solicitamos que se cree la segunda instancia de Connection. En este
        // caso el proceso de crear la instancia debería de fallar.
        try {
            // Se llama al constructor de la clase.
            Connection con2 = Connection.getConnection(); //failed.
        } catch (Exception e) {
            System.out.println("second: " + e.getMessage());
        }
    }
}
