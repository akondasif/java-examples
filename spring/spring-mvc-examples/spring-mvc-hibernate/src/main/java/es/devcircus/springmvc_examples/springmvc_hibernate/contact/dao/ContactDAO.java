/**
 * This file is part of spring-mvc-examples.
 *
 * springmvc-examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * springmvc-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.springmvc_examples.springmvc_hibernate.contact.dao;

import es.devcircus.springmvc_examples.springmvc_hibernate.contact.form.Contact;
import java.util.List;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
public interface ContactDAO {

    public void addContact(Contact contact);

    public List<Contact> listContact();

    public void removeContact(Integer id);
}
