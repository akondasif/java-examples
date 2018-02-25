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
public class NoAccessAppActionHandler extends ActionSupport {
    
    public NoAccessAppActionHandler() {
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
