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
package es.devcircus.hibernate_examples.relationship_one_to_many_annotation_list.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Método que crea un nuevo objeto SessionFactory. Esta clase se encarga de
     * la generación de los objetos org.hibernate.Session. 
     * El objeto Factory creado está concebido para ser compartido entre todos
     * los threads de la aplicación.
     * Hibernate permite que se instancie más de un objeto 
     * org.hibernate.SessionFactory. Esto es útil si se están utilizando más de u
     * na base de datos.
     *
     * @return Objeto SessionFactory creado.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Método que nos permite recuperar el objeto SessionFactory que se se ha 
     * creado para la aplicaicón.
     *
     * @return Objeto SessionFactory creado.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
