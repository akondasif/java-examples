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
package es.devcircus.udp.example.data;

/**
 * Clase que nos vale para simular el almacen de datos.
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class Inventory {

    // The resource id
    String id;
    // The resource description.
    private String description;
    // The resource price
    private double price;
    // The number available in inventory
    private int quantity;

    /**
     * Instantiates a new list and populates with specified values.
     */
    public Inventory() {
    }

    /**
     * Crea una instancia a partir de los atributos del objeto.
     *
     * @param id the id Identificador de la instancia.
     * @param desc the description Descripción d ela instancia.
     * @param price the price Precio de la instancia.
     * @param quantity the quantity Cantidad de la instanacia.
     */
    public Inventory(String id, String desc, double price, int quantity) {
        this.id = id;
        this.description = desc;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Método que permite recuperar el valor del atributo id.
     *
     * @return Valor del atributo id.
     */
    public String getId() {
        return id;
    }

    /**
     * Método que permite recuperar el valor del atributo description.
     *
     * @return Valor del atributo description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Método que permite recuperar el valor del atributo price.
     *
     * @return Valor del atributo price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Método que permite recuperar el valor del atributo quantity.
     *
     * @return Valor del atributo quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Accepts an item id and concatenates all related information into a single
     * string.
     *
     * @return the string with all of the resource's attributes
     */
    public String toFullString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("ID: ").append(this.getId()).append(
                "\tItem Description: ").append(this.getDescription()).append(
                        "\t\tUnit Price: $").append(this.getPrice()).append(
                        "\t\tInventory: ").append(this.getQuantity()).toString()
                + "\n";
    }
}
