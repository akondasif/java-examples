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
package es.devcircus.hibernate_examples.multiple_configuration_files;

import es.devcircus.hibernate_examples.multiple_configuration_files.model.employee.dao.impl.DefaultEmployeeDao;
import es.devcircus.hibernate_examples.multiple_configuration_files.model.employee.vo.Employee;
import es.devcircus.hibernate_examples.multiple_configuration_files.model.person.dao.impl.DefaultPersonDao;
import es.devcircus.hibernate_examples.multiple_configuration_files.model.person.vo.Person;
import java.sql.Date;
import java.util.List;


/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description 
 */
public class Main {

    private static final DefaultEmployeeDao employeeDao = new DefaultEmployeeDao();
    private static final DefaultPersonDao personDao = new DefaultPersonDao();
    
    /**
     * Método main.
     * @param args Array de argumentos del programa. 
     * @throws java.lang.Exception 
     */
    public static void main(String[] args) throws Exception{

        // ---------------------------------------------------------------------
        // Bloque que testea las funciones de gestión de empleados.
        // ---------------------------------------------------------------------
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println(" Employee");
        System.out.println("------------------------------------------------------------------------");
        
        // Read
        System.out.println("******* READ *******");
        List<Employee> employees = employeeDao.list();
        System.out.println("Total Employees: " + employees.size());

        // Write
        System.out.println("******* WRITE *******");
        Employee empl = new Employee("Jack", "Bauer", new Date(System.currentTimeMillis()), "911");
        empl = employeeDao.save(empl);
        empl = employeeDao.read(empl.getId());
        System.out.printf("%d %s %s \n", empl.getId(), empl.getFirstname(), empl.getLastname());

        // Update
        System.out.println("******* UPDATE *******");
        Employee empl2 = employeeDao.read(empl.getId()); // read employee with id 1
        System.out.println("Name Before Update:" + empl2.getFirstname());
        empl2.setFirstname("James");
        employeeDao.update(empl2);  // save the updated employee details

        empl2 = employeeDao.read(empl.getId()); // read again employee with id 1
        System.out.println("Name Aftere Update:" + empl2.getFirstname());

        // Delete
        System.out.println("******* DELETE *******");
        employeeDao.delete(empl);
        Employee empl3 = employeeDao.read(empl.getId());
        System.out.println("Object:" + empl3);
        
        // ---------------------------------------------------------------------
        // Bloque que testea las funciones de gestión de personas.
        // ---------------------------------------------------------------------
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println(" Person");
        System.out.println("------------------------------------------------------------------------");
        
        // Read
        System.out.println("******* READ *******");
        List<Person> persons = personDao.list();
        System.out.println("Total Persons: " + persons.size());

        // Write
        System.out.println("******* WRITE *******");
        Person person1 = new Person("Jack", "Bauer", new Date(System.currentTimeMillis()), "911");
        person1 = personDao.save(person1);
        person1 = personDao.read(person1.getId());
        System.out.printf("%d %s %s \n", person1.getId(), person1.getFirstname(), person1.getLastname());

        // Update
        System.out.println("******* UPDATE *******");
        Person person2 = personDao.read(person1.getId()); // read person with id 1
        System.out.println("Name Before Update:" + person2.getFirstname());
        person2.setFirstname("James");
        personDao.update(person2);  // save the updated person details

        person2 = personDao.read(person1.getId()); // read again person with id 1
        System.out.println("Name Aftere Update:" + person2.getFirstname());

        // Delete
        System.out.println("******* DELETE *******");
        personDao.delete(person1);
        Person person3 = personDao.read(person1.getId());
        System.out.println("Object:" + person3);
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println(" Fin");
        System.out.println("------------------------------------------------------------------------");
        
        // End
        System.exit(0);
    }

}