package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;

public class UsuarioAddPermisosActionHandler extends CustomActionSupport  {
    private String id;
    private UserCVO current;
    //Id del elemento del boton volver
    private String idElement;
    private String action;
    private String userName = getUsername();
    private String tab;
    private String idUser;
    private String moduleId;
    private String applicationId;

    public UsuarioAddPermisosActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.current = UserCVOFacade.preparaAñadirPermisos(userName, Long.valueOf(this.id), Long.valueOf(applicationId));
        this.current = UserCVOFacade.viewPermisosCVO(userName, current,applicationId);
        return SUCCESS;
    }

        public String view() throws Exception {
        try {
             this.current = UserCVOFacade.preparaAñadirPermisos(userName, Long.valueOf(this.id), Long.valueOf(applicationId));
       
            this.current = UserCVOFacade.viewPermisosCVO(userName, current,applicationId);
            this.id = current.getId().toString();
//            Añadimos un mensaje que indique al usuario que la acción se ha realizado con exito.     
            addActionMessage(getText("usuarios.usuarios.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.usuarios.message.edit.error"));
            return INPUT;
        }
    }
    
    public String add() throws Exception {
        try {
            this.current = UserCVOFacade.updatePermisosCVO(userName, current);
            this.id = current.getId().toString();
//            Añadimos un mensaje que indique al usuario que la acción se ha realizado con exito.     
            addActionMessage(getText("usuarios.usuarios.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.usuarios.message.edit.error"));
            return INPUT;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdElement() {
        return idElement;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }

    public String getUserName() {
        return userName;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public UserCVO getCurrent() {
        return current;
    }

    public void setCurrent(UserCVO current) {
        this.current = current;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
