/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.hibernate_examples.relationship_one_to_many_annotation_list.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author adrian
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "dept_name")
    private String departmentName;
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="department_id")
    @IndexColumn(name="idx")
    private List<Employee> employees;

    /**
     * Constructor por defecto
     */
    public Department() {
    }

    /**
     * Constructor al que se pasamos como argumento los atributos del objeto.
     *
     * @param departmentName Nombre del nuevo departamento.
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Método getter del atributo departmentId.
     *
     * @return Identificador del departamento.
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * Método setter del atributo departmentId.
     *
     * @param departmentId Identificador del departamento.
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * Método getter del atributo departmentName.
     *
     * @return Nombre del departamento.
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Método setter del atributo departmentName.
     *
     * @param departmentName Nombre del departamento.
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Método getter del atributo employees.
     *
     * @return Lista de elementos Employee que contienen los datos de los
     * empleados que pertenecen a este departamento.
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Método setter del atributo employees.
     *
     * @param employees Lista de elementos Employee que contienen los datos de
     * los empleados que pertenecen a este departamento.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
