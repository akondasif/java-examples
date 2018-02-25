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
package es.devcircus.hibernate_examples.multiple_configuration_files.model.person.dao.impl;

import es.devcircus.hibernate_examples.multiple_configuration_files.model.person.dao.IPersonDao;
import es.devcircus.hibernate_examples.multiple_configuration_files.model.person.vo.Person;
import es.devcircus.hibernate_examples.multiple_configuration_files.util.PersonHibernateUtil;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.DaoException;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.ElementAlreadyExistException;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.ElementNonExistException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description
 */
public class DefaultPersonDao implements IPersonDao {

    /**
     * Método que nos permite recuperar la lista de personas desde la base de
     * datos.
     *
     * @return Lista de objetos personas que contiene los datos de todos las
     * personas almacenados en la base de datos.
     * @throws DaoException
     */
    @Override
    public List<Person> list()
            throws DaoException {
        SessionFactory sf = PersonHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Recuperamos la lista de empleados
        List<Person> persons = session.createQuery("from Person").list();
        session.close();
        return persons;
    }

    /**
     * Método que nos permite recuperar los datos de una persona determinada
     * especificando su identificador.
     *
     * @param id Identificador de la persona de la cual deseamos recupear la
     * información almacenada en la base de datos.
     * @return Objeto Person que contiene los datos de la persona
     * correspondientes al identificador proporcionado.
     * @throws DaoException
     */
    @Override
    public Person read(Long id)
            throws DaoException {
        SessionFactory sf = PersonHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Recuperamos la instancia de persona que coincide con el id que hemos
        // pasado como parámetro en la función.
        Person person = (Person) session.get(Person.class, id);
        session.close();
        return person;
    }

    /**
     * Método que nos permite almacenar un nuevo objeto persona en la base de
     * datos.
     *
     * @param person Objeto Person que deseamos almacenar en la base de datos.
     * @return Objeto que contiene la información del objeto que hemos
     * almacenado en la base de datos. En este caso, a mayores de los datos
     * indicados, este objeto contiene el identificador del emepleado.
     * @throws DaoException
     * @throws ElementAlreadyExistException
     */
    @Override
    public Person save(Person person)
            throws DaoException, ElementAlreadyExistException {
        SessionFactory sf = PersonHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Iniciamos el contecto de transacción.
        session.beginTransaction();
        // Creamos un nuevo elemento dentro de la base de datos.
        Long id = (Long) session.save(person);
        // La función save nos retorna el id del nuevo elemento.
        person.setId(id);
        // Finalizamos el contexto de transacción.
        session.getTransaction().commit();
        session.close();
        return person;
    }

    /**
     * Método que nos permite modificar los datos de una persona que ya exista
     * en la base de datos.
     *
     * @param person Objeto Person en el que hemos almacenado la información de
     * la persona modificada.
     * @return el mismo objeto persona que hemos actualizado en la base de
     * datos.
     * @throws DaoException
     * @throws ElementNonExistException
     */
    @Override
    public Person update(Person person)
            throws DaoException, ElementNonExistException {
        SessionFactory sf = PersonHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Iniciamos el contexto de transacción.
        session.beginTransaction();
        // Actualizamos los datos del objeto en la base de datos.
        session.merge(person);
        // Finalizamos el contexto de transacción.
        session.getTransaction().commit();
        session.close();
        return person;

    }

    /**
     * Método que nos permite eliminar una persona de la base de datos.
     *
     * @param person Objeto Person que queremos eliminar de la base de datos.
     * @throws DaoException
     * @throws ElementNonExistException
     */
    @Override
    public void delete(Person person)
            throws DaoException, ElementNonExistException {
        SessionFactory sf = PersonHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Iniciamos el contexto de transacción.
        session.beginTransaction();
        // Eliminamos el objeto de la base de datos.
        session.delete(person);
        // Finalizamos el contexto de transacción.
        session.getTransaction().commit();
        session.close();
    }

}
