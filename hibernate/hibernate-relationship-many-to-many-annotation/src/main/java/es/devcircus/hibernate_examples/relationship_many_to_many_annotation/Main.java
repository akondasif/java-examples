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
package es.devcircus.hibernate_examples.relationship_many_to_many_annotation;

import es.devcircus.hibernate_examples.relationship_many_to_many_annotation.model.Employee;
import es.devcircus.hibernate_examples.relationship_many_to_many_annotation.model.Meeting;
import es.devcircus.hibernate_examples.relationship_many_to_many_annotation.util.HibernateUtil;
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

        Meeting meeting1 = new Meeting("Quaterly Sales meeting");
        Meeting meeting2 = new Meeting("Weekly Status meeting");

        Employee employee1 = new Employee("Sergey", "Brin");
        Employee employee2 = new Employee("Larry", "Page");

        employee1.getMeetings().add(meeting1);
        employee1.getMeetings().add(meeting2);
        employee2.getMeetings().add(meeting1);

        session.save(employee1);
        session.save(employee2);

        session.getTransaction().commit();
        session.close();

        // End
        System.exit(0);
    }

}
