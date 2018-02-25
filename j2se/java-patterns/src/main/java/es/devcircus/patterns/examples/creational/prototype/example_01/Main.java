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
package es.devcircus.patterns.examples.creational.prototype.example_01;

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
        // Populamos la cache de objetos
        ShapeCache.loadCache();
        // Solicitamos que se clone el prototipo de Shape con tipo = 1
        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());
        // Solicitamos que se clone el prototipo de Shape con tipo = 2
        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
        // Solicitamos que se clone el prototipo de Shape con tipo = 3
        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
