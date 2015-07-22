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

import es.devcircus.patterns.examples.creational.builder.example_02.model.items.Item;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasé menú. Esta clase se compone de diferente número de elementos de tipo
 * Item. Esta clase será la que se encargará de generar el Builder.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class Meal {

    private List<Item> items = new ArrayList();

    /**
     * Método que permite añadir un nuevo elemento al Menú
     *
     * @param item Nuevo elemento que queremos añadir.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Método que calcula el precio de todos los elementos del menú.
     *
     * @return Valor calculado del menú.
     */
    public float getCost() {
        float cost = 0.0f;
        // Iteramos sobre los elementos del menú y sumamos el coste de dichos 
        // elementos.
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    /**
     * Método que muestra los datos de los elementos del menú.
     */
    public void showItems() {
        // Recorremos los elementos del menú y mostramos los datos de cada uno 
        // de estos
        for (Item item : items) {
            System.out.print("Item" + item.name());
            System.out.print(", Packing : " + item.packing().pack());
            System.out.println(", Price : " + item.price());
        }
    }
}
