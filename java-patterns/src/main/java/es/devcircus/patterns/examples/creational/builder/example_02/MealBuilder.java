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

import es.devcircus.patterns.examples.creational.builder.example_02.model.items.burgers.ChickenBurger;
import es.devcircus.patterns.examples.creational.builder.example_02.model.items.burgers.VegBurger;
import es.devcircus.patterns.examples.creational.builder.example_02.model.items.colddrinks.Coke;
import es.devcircus.patterns.examples.creational.builder.example_02.model.items.colddrinks.Pepsi;

/**
 * Clase que se encarga de la construcción de los menús.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class MealBuilder {

    /**
     * Método que se encarga de crear el objeto menú para el caso de menu
     * vegetariano
     *
     * @return Instancia de menú vegetariano.
     */
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    /**
     * Método que se encarga de crear el objeto menú para el caso de menu NO
     * vegetariano
     *
     * @return Instancia de menú NO vegetariano.
     */
    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
