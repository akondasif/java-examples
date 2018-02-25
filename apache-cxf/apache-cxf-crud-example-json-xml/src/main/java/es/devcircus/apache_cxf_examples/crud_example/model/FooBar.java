/**
 * This file is part of apache-cxf-examples.
 *
 * apache-cxf-examples is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * apache-cxf-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.apache_cxf_examples.crud_example.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
@XmlRootElement
public class FooBar {
    
    int id;
    String name;

    /**
     * 
     */
    public FooBar() {
        this.id = 1;
        this.name = "Techie Kernel";

    }

    /**
     * 
     * @param id
     * @param name 
     */
    public FooBar(int id, String name) {
        this.id = id;
        this.name = name;

    }

    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "FooBar [id=" + id + ", name=" + name + ", hashCode()="
                + hashCode() + "]";
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return this.id;
    }

    /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FooBar) {
            if (this.id == ((FooBar) obj).id) {
                return true;
            }
        }
        return false;
    }

}
