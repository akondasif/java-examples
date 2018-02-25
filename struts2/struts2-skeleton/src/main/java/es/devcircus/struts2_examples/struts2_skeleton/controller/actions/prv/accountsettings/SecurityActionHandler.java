/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.acl.controller.actions.prv.accountsettings;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.security.authentication.model.user.UserFacade;
import es.itnoroeste.core.security.authentication.model.user.vo.User;
import es.devcircus.acl.util.Validation;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class SecurityActionHandler extends CustomActionSupport {

    private String id;
    private String idPerson;
    private String password1;
    private String password2;
    private String password3;
    private User current;
    private String userName = getUsername();
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SecurityActionHandler.class.getName());

    public SecurityActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.current = UserFacade.obtainUserByUsername(userName, userName);
        this.id = this.current.getId().toString();
        return SUCCESS;
    }

    public String edit() throws Exception {
        try {
            UserFacade.resetPasswordUser(userName, userName, password2);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.password.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.password.message.edit.error"));
            return INPUT;
        }
    }

    @Override
    public void validate() {
        try {
            this.current = UserFacade.obtainUserByUsername(userName, userName);
        } catch (UnauthorizedAccessException ex) {
            Logger.getLogger(SecurityActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(SecurityActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Verificamos contraseñas nuevas iguales
        if (!Validation.validarIguales(password2, password3)) {
            addFieldError("password3", getText("common.field.password.notEquals"));
        }
        
        if (!Validation.validarPassword(password2)) {
            addFieldError("password2", getText("common.field.format.notValid"));
        }
        
        try {
            //Validar old pass
            if (!UserFacade.validatePassword(userName, password1)) {
                addFieldError("password1", getText("common.field.password.notValid"));
            }
        } catch (UnauthorizedAccessException ex) {
            addFieldError("password1", getText("common.field.format.notValid"));
        } catch (InternalErrorException ex) {
            addFieldError("password1", getText("common.field.format.notValid"));
        }
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword3() {
        return password3;
    }

    public void setPassword3(String password3) {
        this.password3 = password3;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getUserName() {
        return userName;
    }

 
}
