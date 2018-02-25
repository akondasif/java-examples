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
package es.devcircus.hibernate_examples.patterns_facade_dao_mapping.model.employee.dao;

import es.devcircus.hibernate_examples.patterns_facade_dao_mapping.model.employee.vo.Employee;
import es.devcircus.hibernate_examples.patterns_facade_dao_mapping.util.IDao;
import es.devcircus.hibernate_examples.patterns_facade_dao_mapping.util.exceptions.DaoException;
import es.devcircus.hibernate_examples.patterns_facade_dao_mapping.util.exceptions.ElementAlreadyExistException;
import es.devcircus.hibernate_examples.patterns_facade_dao_mapping.util.exceptions.ElementNonExistException;
import java.util.List;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description
 */
public interface IEmployeeDao extends IDao {

    /**
     *  Método que nos permite recuperar la lista de empleados desde la base de
     * datos.
     * @return Lista de objetos empleados que contiene los datos de todos los
     * empleados almacenados en la base de datos.
     * @throws DaoException 
     */
    public List<Employee> list()
            throws DaoException;

    /**
     * Método que nos permite recuperar los datos de un empleado determinado
     * especificando su identificador.
     * @param id Identificador del empleado del cual deseamos recupear la
     * información almacenada en la base de datos.
     * @return Objeto empleado que contiene los datos del empleado
     * correspondientes al identificador proporcionado.
     * @throws DaoException 
     */
    public Employee read(Long id)
            throws DaoException;

    /**
     * Método que nos permite almacenar un nuevo objeto empleado en la base de
     * datos.
     * @param employee Objeto empleado que deseamos almacenar en la base de
     * datos.
     * @return Objeto que contiene la información del objeto que hemos
     * almacenado en la base de datos. En este caso, a mayores de los datos
     * indicados, este objeto contiene el identificador del emepleado.
     * @throws DaoException
     * @throws ElementAlreadyExistException 
     */
    public Employee save(Employee employee)
            throws DaoException, ElementAlreadyExistException;

    /**
     * Método que nos permite modificar los datos de un empleado que ya exista
     * en la base de datos.
     * @param employee Objeto empleado en el que hemos almacenado la información
     * del empleado modificada.
     * @return el mismo objeto empleado que hemos actualizado en la base de
     * datos.
     * @throws DaoException
     * @throws ElementNonExistException 
     */
    public Employee update(Employee employee)
            throws DaoException, ElementNonExistException;

    /**
     * Método que nos permite eliminar un empleado de la base de datos.
     * @param employee Objeto empleado que queremos eliminar de la base de
     * datos.
     * @throws DaoException
     * @throws ElementNonExistException 
     */
    public void delete(Employee employee)
            throws DaoException, ElementNonExistException;

}
