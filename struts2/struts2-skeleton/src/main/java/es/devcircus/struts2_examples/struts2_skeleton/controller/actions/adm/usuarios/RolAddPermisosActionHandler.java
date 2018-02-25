package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.devcircus.acl.authentication.model.rol.RolCVOFacade;
import es.devcircus.acl.authentication.model.rol.vo.RolCVO;

public class RolAddPermisosActionHandler extends CustomActionSupport {

    private String id;
    private RolCVO current;
    //Id del elemento del boton volver
    private String idElement;
    private String action;
    private String userName = getUsername();
    private String tab;
    private String idUser;
    private String moduleId;
    private String applicationId;

    public RolAddPermisosActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.current = RolCVOFacade.preparaAñadirPermisosRol(userName, Long.valueOf(this.id));
          this.current = RolCVOFacade.viewPermisosRolCVO(userName, Long.valueOf(this.id));
        return SUCCESS;
    }

    public String view() throws Exception {
        try {
//            this.current = RolCVOFacade.preparaAñadirPermisosRol(userName, Long.valueOf(this.id));
            this.current = RolCVOFacade.viewPermisosRolCVO(userName, Long.valueOf(this.id));
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
            this.current = RolCVOFacade.updatePermisosRol(userName, current);
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

    public RolCVO getCurrent() {
        return current;
    }

    public void setCurrent(RolCVO current) {
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
