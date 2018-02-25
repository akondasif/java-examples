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
package es.devcircus.hibernate_examples.patterns_dao_mapping.model.employee.dao.impl;

import es.devcircus.hibernate_examples.patterns_dao_mapping.model.employee.dao.IEmployeeDao;
import es.devcircus.hibernate_examples.patterns_dao_mapping.model.employee.vo.Employee;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.DefaultDao;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.HibernateUtil;
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
public class DefaultEmployeeDao extends DefaultDao implements IEmployeeDao {

    /**
     * Método que nos permite recuperar la lista de empleados desde la base de
     * datos.
     *
     * @return Lista de objetos empleados que contiene los datos de todos los
     * empleados almacenados en la base de datos.
     * @throws DaoException
     */
    @Override
    public List<Employee> list()
            throws DaoException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Recuperamos la lista de empleados
        List<Employee> employees = session.createQuery("from Employee").list();
        session.close();
        return employees;
    }

    /**
     * Método que nos permite recuperar los datos de un empleado determinado
     * especificando su identificador.
     *
     * @param id Identificador del empleado del cual deseamos recupear la
     * información almacenada en la base de datos.
     * @return Objeto empleado que contiene los datos del empleado
     * correspondientes al identificador proporcionado.
     * @throws DaoException
     */
    @Override
    public Employee read(Long id)
            throws DaoException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Recuperamos la instancia de empleado que coincide con el id que hemos
        // pasado como parámetro en la función.
        Employee employee = (Employee) session.get(Employee.class, id);
        session.close();
        return employee;
    }

    /**
     * Método que nos permite almacenar un nuevo objeto empleado en la base de
     * datos.
     *
     * @param employee Objeto empleado que deseamos almacenar en la base de
     * datos.
     * @return Objeto que contiene la información del objeto que hemos
     * almacenado en la base de datos. En este caso, a mayores de los datos
     * indicados, este objeto contiene el identificador del emepleado.
     * @throws DaoException
     * @throws ElementAlreadyExistException
     */
    @Override
    public Employee save(Employee employee)
            throws DaoException, ElementAlreadyExistException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Iniciamos el contecto de transacción.
        session.beginTransaction();
        // Creamos un nuevo elemento dentro de la base de datos.
        Long id = (Long) session.save(employee);
        // La función save nos retorna el id del nuevo elemento.
        employee.setId(id);
        // Finalizamos el contexto de transacción.
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    /**
     * Método que nos permite modificar los datos de un empleado que ya exista
     * en la base de datos.
     *
     * @param employee Objeto empleado en el que hemos almacenado la información
     * del empleado modificada.
     * @return el mismo objeto empleado que hemos actualizado en la base de
     * datos.
     * @throws DaoException
     * @throws ElementNonExistException
     */
    @Override
    public Employee update(Employee employee)
            throws DaoException, ElementNonExistException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Iniciamos el contexto de transacción.
        session.beginTransaction();
        // Actualizamos los datos del objeto en la base de datos.
        session.merge(employee);
        // Finalizamos el contexto de transacción.
        session.getTransaction().commit();
        session.close();
        return employee;

    }

    /**
     * Método que nos permite eliminar un empleado de la base de datos.
     *
     * @param employee Objeto empleado que queremos eliminar de la base de
     * datos.
     * @throws DaoException
     * @throws ElementNonExistException
     */
    @Override
    public void delete(Employee employee)
            throws DaoException, ElementNonExistException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        // Iniciamos el contexto de transacción.
        session.beginTransaction();
        // Eliminamos el objeto de la base de datos.
        session.delete(employee);
        // Finalizamos el contexto de transacción.
        session.getTransaction().commit();
        session.close();
    }

}
