/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.acl.controller.actions.prv.activity;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.security.authentication.model.group.vo.Group;

/**
 *
 * @author adrian
 */
public class ActivityActionHandler extends ActionSupport {
    
    private String id;
    private Group current;

    public ActivityActionHandler() {
    }
    
    @Override
    public String execute() throws Exception {    
        return SUCCESS;
    }

    public String view() throws Exception {
        return SUCCESS;
    }

    public String edit() throws Exception {
        return SUCCESS;
    }

    public String delete() throws Exception {

        return SUCCESS;
    }

    public String create() throws Exception {
        return SUCCESS;
    }

    public Group getCurrent() {
        return current;
    }

    public void setCurrent(Group current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
