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
package es.devcircus.patterns.examples.creational.builder.example_02.model;

import es.devcircus.patterns.examples.creational.builder.example_02.model.packings.bottles.Bottle;
import es.devcircus.patterns.examples.creational.builder.example_02.model.packings.Packing;
import es.devcircus.patterns.examples.creational.builder.example_02.model.items.Item;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public abstract class ColdDrink implements Item {

    /**
     * Método que se encarga del empaquetado.
     *
     * @return Retorna el Packing.
     */
    @Override
    public Packing packing() {
        return new Bottle();
    }

    /**
     * Definimos el método que se encarga de calcular el precio.
     *
     * @return Precio calculado.
     */
    @Override
    public abstract float price();
}
