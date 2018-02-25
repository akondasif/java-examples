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
package es.devcircus.hibernate_examples.patterns_dao_mapping;

import es.devcircus.hibernate_examples.patterns_dao_mapping.model.employee.dao.IEmployeeDao;
import es.devcircus.hibernate_examples.patterns_dao_mapping.model.employee.vo.Employee;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.DaoFactory;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.DaoException;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.ElementAlreadyExistException;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.ElementNonExistException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description
 */
public class Main {

    /**
     * Método main.
     *
     * @param args Array de argumentos del programa.
     */
    public static void main(String[] args) {

        // Recuperamos el DAO para poder interactuar con la base de datos.
        IEmployeeDao employeeDao = DaoFactory.getEmployeeDao();

        try {
            // -----------------------------------------------------------------
            // Read
            // -----------------------------------------------------------------
            System.out.println("******* READ *******");

            List<Employee> employees;
            employees = employeeDao.list();
            System.out.println("Total Employees: " + employees.size());

            // -----------------------------------------------------------------
            // Write
            // -----------------------------------------------------------------
            System.out.println("******* WRITE *******");

            Employee empl = new Employee("Jack", "Bauer", new Date(System.currentTimeMillis()), "911");
            empl = employeeDao.save(empl);
            empl = employeeDao.read(empl.getId());
            System.out.printf("%d %s %s \n", empl.getId(), empl.getFirstname(), empl.getLastname());

            // -----------------------------------------------------------------
            // Update
            // -----------------------------------------------------------------
            System.out.println("******* UPDATE *******");

            Employee empl2;
            empl2 = employeeDao.read(empl.getId()); // read employee with id 1
            System.out.println("Name Before Update:" + empl2.getFirstname());
            empl2.setFirstname("James");
            employeeDao.update(empl2);  // save the updated employee details
            empl2 = employeeDao.read(empl.getId()); // read again employee with id 1
            System.out.println("Name Aftere Update:" + empl2.getFirstname());

            // -----------------------------------------------------------------
            // Delete
            // -----------------------------------------------------------------
            System.out.println("******* DELETE *******");

            employeeDao.delete(empl);
            Employee empl3;
            empl3 = employeeDao.read(empl.getId());
            System.out.println("Object:" + empl3);

        } catch (DaoException | ElementAlreadyExistException | ElementNonExistException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            // End
            System.exit(1);
        }

        // End
        System.exit(0);
    }
}
