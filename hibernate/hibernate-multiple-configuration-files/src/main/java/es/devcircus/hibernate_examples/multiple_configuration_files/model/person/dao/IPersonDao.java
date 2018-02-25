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
package es.devcircus.hibernate_examples.multiple_configuration_files.model.person.dao;

import es.devcircus.hibernate_examples.multiple_configuration_files.model.person.vo.Person;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.IDao;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.DaoException;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.ElementAlreadyExistException;
import es.devcircus.hibernate_examples.patterns_dao_mapping.util.exceptions.ElementNonExistException;
import java.util.List;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description
 */
public interface IPersonDao extends IDao {

    /**
     * Método que nos permite recuperar la lista de personas desde la base de
     * datos.
     *
     * @return Lista de objetos personas que contiene los datos de todos las
     * personas almacenados en la base de datos.
     * @throws DaoException
     */
    public List<Person> list()
            throws DaoException;

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
    public Person read(Long id)
            throws DaoException;

    /**
     * Método que nos permite almacenar un nuevo objeto persona en la base de
     * datos.
     *
     * @param person Objeto Person que deseamos almacenar en la base de
     * datos.
     * @return Objeto que contiene la información del objeto que hemos
     * almacenado en la base de datos. En este caso, a mayores de los datos
     * indicados, este objeto contiene el identificador del emepleado.
     * @throws DaoException
     * @throws ElementAlreadyExistException
     */
    public Person save(Person person)
            throws DaoException, ElementAlreadyExistException;

    /**
     * Método que nos permite modificar los datos de una persona que ya exista
     * en la base de datos.
     *
     * @param person Objeto Person en el que hemos almacenado la información
     * de la persona modificada.
     * @return el mismo objeto persona que hemos actualizado en la base de
     * datos.
     * @throws DaoException
     * @throws ElementNonExistException
     */
    public Person update(Person person)
            throws DaoException, ElementNonExistException;

    /**
     * Método que nos permite eliminar una persona de la base de datos.
     *
     * @param person Objeto Person que queremos eliminar de la base de
     * datos.
     * @throws DaoException
     * @throws ElementNonExistException
     */
    public void delete(Person person)
            throws DaoException, ElementNonExistException;

}
