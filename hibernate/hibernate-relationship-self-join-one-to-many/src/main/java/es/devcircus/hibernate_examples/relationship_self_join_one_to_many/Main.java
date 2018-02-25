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
package es.devcircus.hibernate_examples.relationship_self_join_one_to_many;

import es.devcircus.hibernate_examples.relationship_self_join_one_to_many.model.Employee;
import es.devcircus.hibernate_examples.relationship_self_join_one_to_many.util.HibernateUtil;
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
     *
     * @param args Array de argumentos del programa.
     */
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        Employee manager1 = new Employee("Chuck", "Norris");

        Employee employee1 = new Employee("Sergey", "Brin");
        Employee employee2 = new Employee("Larry", "Page");

        employee1.setManager(manager1);
        employee2.setManager(manager1);

        session.save(employee1);
        session.save(employee2);

        session.getTransaction().commit();
        session.close();

        // End
        System.exit(0);
    }

}
