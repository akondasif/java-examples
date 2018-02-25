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
package es.devcircus.hibernate_examples.relationship_one_to_many_annotation_list;

import es.devcircus.hibernate_examples.relationship_one_to_many_annotation_list.model.Department;
import es.devcircus.hibernate_examples.relationship_one_to_many_annotation_list.model.Employee;
import es.devcircus.hibernate_examples.relationship_one_to_many_annotation_list.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description 
 */
public class Main {
    
    /**
     * Método main.
     * @param args Array de argumentos del programa. 
     */
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
         
        Department department = new Department();
        department.setDepartmentName("Sales");
 
        Employee emp1 = new Employee("Nina", "Mayers", "111");
        Employee emp2 = new Employee("Tony", "Almeida", "222");
         
        department.setEmployees(new ArrayList<Employee>());
        department.getEmployees().add(emp1);
        department.getEmployees().add(emp2);
         
        session.save(department);
 
        session.getTransaction().commit();
        session.close();
        
        // End
        System.exit(0);
    }

}
