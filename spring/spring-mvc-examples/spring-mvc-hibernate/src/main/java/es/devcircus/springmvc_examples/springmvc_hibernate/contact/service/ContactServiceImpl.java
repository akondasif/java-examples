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
package es.devcircus.springmvc_examples.springmvc_hibernate.contact.service;

import es.devcircus.springmvc_examples.springmvc_hibernate.contact.dao.ContactDAOImpl;
import es.devcircus.springmvc_examples.springmvc_hibernate.contact.form.Contact;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDAOImpl contactDAO;

    @Override
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    @Override
    public List<Contact> listContact() {

        return contactDAO.listContact();
    }

    @Override
    public void removeContact(Integer id) {
        contactDAO.removeContact(id);
    }
}
