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
package es.devcircus.springmvc_examples.springmvc_hibernate.contact.controller;

import es.devcircus.springmvc_examples.springmvc_hibernate.contact.form.Contact;
import es.devcircus.springmvc_examples.springmvc_hibernate.contact.service.ContactService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Adrian Novegil Toledo <adrian.novegil@gmail.com>
 */
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public String listContacts(Map<String, Object> map) {

        map.put("contact", new Contact());
        map.put("contactList", contactService.listContact());

        return "contact";
    }

    /**
     *
     * @param contact
     * @param result
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact, BindingResult result) {

        contactService.addContact(contact);

        return "redirect:/index";
    }

    /**
     *
     * @param contactId
     * @return
     */
    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId") Integer contactId) {

        contactService.removeContact(contactId);

        return "redirect:/index";
    }

}
