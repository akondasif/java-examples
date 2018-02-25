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

import com.google.common.collect.Lists;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
@XmlRootElement
public class FooBarSet {

    private List<FooBar> fooBars;

    /**
     * 
     */
    public FooBarSet() {
        this(Lists.<FooBar>newArrayList());
    }

    /**
     * 
     * @param fooBars 
     */
    public FooBarSet(List<FooBar> fooBars) {
        this.fooBars = fooBars;
    }

    /**
     * 
     * @return 
     */
    public List<FooBar> getFooBars() {
        return fooBars;
    }

    /**
     * 
     * @param fooBars 
     */
    public void setFooBars(List<FooBar> fooBars) {
        this.fooBars = fooBars;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "FooBarSet{" + "fooBars=" + fooBars + '}';
    }

}
