/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.acl.controller.actions.prv.messages;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.security.authentication.model.group.GroupFacade;

/**
 *
 * @author adrian
 */
public class MessagesListActionHandler extends ActionSupport {
    
    private String id;

    public MessagesListActionHandler() {
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String list() throws Exception {
        return SUCCESS;
    }

    public String delete() throws Exception {
       
        return SUCCESS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
