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
package es.devcircus.hibernate_examples.patterns_facade_dao_mapping.util.exceptions;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description
 */
public class DuplicatedElementException extends ModelException {

    /**
     *
     */
    public DuplicatedElementException() {
        super();
    }

    /**
     *
     * @param s
     */
    public DuplicatedElementException(String s) {
        super(s);
    }

    /**
     *
     * @param exception
     */
    public DuplicatedElementException(Throwable exception) {
        super(exception);
    }
}
