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
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public abstract class Shape implements Cloneable {

    private String id;
    protected String type;

    /**
     * Método draw que obligamos a todas las subclases de Shape a implementar.
     */
    abstract void draw();

    /**
     * Método que retorna el valor del atributo type.
     *
     * @return Nuevo valor del atributo type.
     */
    public String getType() {
        return type;
    }

    /**
     * Método que nos retorna el valor del atributo id.
     *
     * @return Valor del atributo id.
     */
    public String getId() {
        return id;
    }

    /**
     * Método que modifica el valor del atributo id.
     *
     * @param id Nuevo valor del atributo id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método que se encarga de clonar la instancia actual.
     *
     * @return Nueva instancia resultado de clonar la actual.
     */
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
