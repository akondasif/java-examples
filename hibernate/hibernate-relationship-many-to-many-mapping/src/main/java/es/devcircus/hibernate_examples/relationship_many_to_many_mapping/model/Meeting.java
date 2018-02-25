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
package es.devcircus.hibernate_examples.relationship_many_to_many_mapping.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description Clase que modela nuestro objeto de dominio Employee.
 */
public class Meeting {

    private Long meetingId;
    private String subject;
    private Date meetingDate;
    private Set<Employee> employees = new HashSet<Employee>();
 
    /**
     * Constructor por defecto
     */
    public Meeting() {
    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     * @param subject Asunto de la reunión.
     */
    public Meeting(String subject) {
        this.subject = subject;
        this.meetingDate = new Date();
    }

    /**
     * Método getter del atributo meetingId.
     * @return Identificador de la reunión.
     */
    public Long getMeetingId() {
        return meetingId;
    }

    /**
     * Método setter del atributo meetingId.
     * @param meetingId Identificador de la reunión.
     */
    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    /**
     * Método getter del atributo subject.
     * @return Asunto de la reunión.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Método setter del atributo subject.
     * @param subject Asunto de la reunión.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Método getter del atributo meetingDate.
     * @return Fecha de la reunión.
     */
    public Date getMeetingDate() {
        return meetingDate;
    }

    /**
     * Método setter del atributo meetingDate.
     * @param meetingDate Fecha de la reunión.
     */
    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    /**
     * Método getter del atributo employees.
     * @return Listado de los empleados que asistirán a la reunión.
     */
    public Set<Employee> getEmployees() {
        return employees;
    }

    /**
     * Método setter del atributo employees.
     * @param employees Listado de los empleados que asistirán a la reunión.
     */
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
