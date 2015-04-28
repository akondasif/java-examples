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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class InventoryCollection {

    // Lista de elementos de la coleccion.
    private final List<Inventory> itemList;

    /**
     * Constructor por defecto.
     */
    public InventoryCollection() {
        itemList = new ArrayList<>();
        // Custom inventory class accepts parameters (id, description, price, quantity)
        itemList.add(new Inventory("00001", "New Inspiron 15", 379.99, 157));
        itemList.add(new Inventory("00002", "New Inspiron 17", 449.99, 128));
        itemList.add(new Inventory("00003", "New Inspiron 15R", 549.99, 202));
        itemList.add(new Inventory("00004", "New Inspiron 15z Ultrabook", 749.99, 315));
        itemList.add(new Inventory("00005", "XPS 14 Ultrabook", 999.99, 261));
        itemList.add(new Inventory("00006", "New XPS 12 UltrabookXPS", 1199.99, 178));
    }

    /**
     * Busca un elemento en la colección por su id y retorna, si este existe,
     * los datos del elemento serializado. Si no existe el objeto, retorna un
     * String genérico indicando que no existe ningún elemento con el id
     * especificado.
     *
     * @param id Identificador del elemento que buscamos.
     * @return String con los atributos del objeto. Mensaje genérico en caso
     * contrario.
     */
    public String getItem(String id) {
        for (Inventory itemList1 : itemList) {
            if (id.equals(itemList1.getId())) {
                return itemList1.toFullString();
            }
        }
        return "Item id not found. Please try another id.\n";
    }

    /**
     * Método que saca por pantalla los datos de todos los elementos de la
     * colección por pantalla.
     *
     * @return String con los datos de los objetos.
     */
    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Item ID\t\tItem Description\n");
        for (Inventory item : itemList) {
            strBuilder.append(item.getId() + "\t\t" + item.getDescription() + "\n");
        }
        return strBuilder.toString();
    }

}
