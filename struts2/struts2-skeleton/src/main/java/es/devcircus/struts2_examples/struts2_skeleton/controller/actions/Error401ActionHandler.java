/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.acl.controller.actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author adriannovegiltoledo
 */
public class Error401ActionHandler extends ActionSupport {
    
    public Error401ActionHandler() {
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
