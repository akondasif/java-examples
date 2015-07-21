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

import java.util.Hashtable;

/**
 * Problema a resolver:
 *
 * Modo de clonar un objeto cuya creación normal supone un alto coste. En este
 * caso veremos cómo hacer una caché de objetos creados mediante acceso simulado
 * a base de datos
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class ShapeCache {

    // Cache de instancias de tipo Shape.
    private static final Hashtable<String, Shape> shapeMap = new Hashtable();

    /**
     * Método que nos retorna una nueva instancia resultado de clonar la
     * instancia prototipo que hemos metido en la cache.
     *
     * @param shapeId Identificador del tipo de Shape que queremos clonar.
     * @return Instancia clonada de Shape.
     */
    public static Shape getShape(String shapeId) {
        // Recuperamos la instancia de prototipo.
        Shape cachedShape = shapeMap.get(shapeId);
        // Retornamos una instancia clonada a partir del prototipo.
        return (Shape) cachedShape.clone();
    }

    /**
     * Para cada uno de los tipos de Shpae que hemos definido creamos una
     * instancia dentro del mapa.
     */
    public static void loadCache() {
        // Datos de Circle.
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);
        // Datos de Square
        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);
        // Datos de Rectangle.
        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}
