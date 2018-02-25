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
package es.devcircus.patterns.examples.creational.builder.example_02;

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
        // Instanciamos el constructor de menús
        MealBuilder mealBuilder = new MealBuilder();
        // Solicitamos que se construya el menú vegetal y mostramos los elementos
        // por pantalla.
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg");
        vegMeal.showItems();
        System.out.println("Total: " + vegMeal.getCost());
        // Solicitamos que se construya el menú NO vegetal y mostramos los elementos
        // por pantalla.
        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg");
        nonVegMeal.showItems();
        System.out.println("Total: " + nonVegMeal.getCost());
    }
}
