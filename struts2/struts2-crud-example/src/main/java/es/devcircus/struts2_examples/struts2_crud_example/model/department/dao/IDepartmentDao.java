/**
 * This file is part of struts2-examples.
 *
 * struts2-examples is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2, or (at your option) any later version.
 *
 * struts2-examples is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; see the file COPYING. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package es.devcircus.struts2_examples.struts2_crud_example.model.department.dao;

import es.devcircus.struts2_examples.struts2_crud_example.model.department.vo.Department;
import java.util.List;
import java.util.Map;

/**
 * @author Adrian Novegil Toledo
 * @mail adrian.novegil@gmail.com
 * @description 
 */
public interface IDepartmentDao {

    /**
     * 
     * @return 
     */
    public List<Department> getAllDepartments();

    /**
     * 
     * @return 
     */
    public Map<Integer, Department> getDepartmentsMap();
}
