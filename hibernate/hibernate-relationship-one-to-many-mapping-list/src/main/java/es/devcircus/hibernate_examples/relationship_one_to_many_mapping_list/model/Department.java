/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.hibernate_examples.relationship_one_to_many_mapping_list.model;

import java.util.List;

/**
 *
 * @author adrian
 */
public class Department {

    private Long departmentId;
    private String departmentName;
    private List<Employee> employees;

    /**
     * Constructor por defecto
     */
    public Department() {
    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     * @param departmentName Nombre del nuevo departamento.
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Método getter del atributo departmentId.
     * @return Identificador del departamento.
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * Método setter del atributo departmentId.
     * @param departmentId Identificador del departamento.
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Método getter del atributo departmentName.
     * @return Nombre del departamento.
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Método setter del atributo departmentName.
     * @param departmentName Nombre del departamento.
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Método getter del atributo employees.
     * @return Lista de elementos Employee que contienen los datos de los 
     * empleados que pertenecen a este departamento.
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Método setter del atributo employees.
     * @param employees Lista de elementos Employee que contienen los datos de 
     * los empleados que pertenecen a este departamento.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
}
