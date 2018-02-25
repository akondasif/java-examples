/**
 * This file is part of hibernate-examples.
 *
 * hibernate-examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * hibernate-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.hibernate_examples.patterns_dao_mapping.util;

import es.devcircus.hibernate_examples.patterns_dao_mapping.model.employee.dao.IEmployeeDao;
import es.devcircus.hibernate_examples.patterns_dao_mapping.model.employee.dao.impl.DefaultEmployeeDao;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description Clase que modela nuestro objeto de dominio Employee.
 */
public abstract class DaoFactory {

    private static IEmployeeDao employeeDao;
  
    /**
     * 
     * @return 
     */
    public static IEmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new DefaultEmployeeDao();
        }
        return employeeDao;
    }

}
