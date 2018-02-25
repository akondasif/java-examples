/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.acl.controller.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author xoan
 */
public class CustomActionSupport extends ActionSupport {

    public String getUsername() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
        return user.getUsername(); //get logged in username
    }
}
