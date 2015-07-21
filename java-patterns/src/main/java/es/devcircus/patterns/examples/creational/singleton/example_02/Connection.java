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
 * Clase que implementa el patrón singleton.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public final class Connection {

    // Variable estática que usamos para controlar si se ha creado o no la instancia
    // de la clase.
    // Varibale singleton
    public static boolean haveOne = false;

    /**
     * Constructor por defecto.
     *
     * @throws Exception En caso de que ya se halla creado una instancia previa
     * de la clase Connection.
     */
    public Connection() throws Exception {
        // Verificamos si se ha creado una instancia previa de la clase.
        if (!haveOne) {
            // Si no hemos creado la instancia de la clase, ejecutamos el método
            // doSomething.
            doSomething();
            // Marcamos la clase para que no se puedan crear más instancias.
            haveOne = true;
        } else {
            // Si ya se ha creado una instancia previa de la clase, lanzamos una
            // excepción que indique este hecho.
            throw new Exception("You cannot have a second instance");
        }
    }

    /**
     * Método que retorna una nueva instancia de Connection si es la primera vez
     * que se crea una instancia de este tipo. Si ya se ha creado alguna
     * instancia de Connection previa, se lanzará una excepción.
     *
     * @return Instancia de Connection.
     * @throws Exception En caso de que ya se halla creado alguna instancia
     * previa.
     */
    public static Connection getConnection() throws Exception {
        return new Connection();
    }

    /**
     * Método dummy que ilustra la posibilidad de ejecutar lógica durante el
     * proceso de creación de la instancia.
     */
    void doSomething() {
    }
}
