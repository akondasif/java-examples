/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.hibernate_examples.relationship_one_to_many_mapping_array.model;

/**
 *
 * @author adrian
 */
public class Department {

    private Long departmentId;
    private String departmentName;
    private Employee[] employees;

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
     * @return Array de elementos Employee que contienen los datos de los 
     * empleados que pertenecen a este departamento.
     */
    public Employee[] getEmployees() {
        return employees;
    }

    /**
     * Método setter del atributo employees.
     * @param employees Array de elementos Employee que contienen los datos de los 
     * empleados que pertenecen a este departamento.
     */
    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }
    
}
