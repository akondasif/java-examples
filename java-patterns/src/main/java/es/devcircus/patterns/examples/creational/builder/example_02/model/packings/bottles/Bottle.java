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
package es.devcircus.patterns.examples.creational.builder.example_02.model.packings.bottles;

import es.devcircus.patterns.examples.creational.builder.example_02.model.packings.Packing;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class Bottle implements Packing {

    /**
     * Metodo que retorna el tipo de pack con el que estamos trabajando.
     *
     * @return Tipo de pack con el que estamos trabajando.
     */
    @Override
    public String pack() {
        return "Bottle";
    }
}
