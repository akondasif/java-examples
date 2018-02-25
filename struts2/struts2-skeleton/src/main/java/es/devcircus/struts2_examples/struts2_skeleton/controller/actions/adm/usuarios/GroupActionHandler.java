package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.DaoException;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.devcircus.acl.authentication.model.group.GroupCVOFacade;
import es.devcircus.acl.authentication.model.group.vo.GroupCVO;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;
import es.devcircus.acl.util.Validation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class GroupActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private GroupCVO current;
    private PaginatedListImpl usuarios2;
    private HttpServletRequest request;
    private String idGroup;
    //Id del elemento del boton volver
    private String idElement;
    private String action;
    private String actionName;
    private String userName = getUsername();
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GroupActionHandler.class.getName());
    private String tab;

    public GroupActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.usuarios2 = new PaginatedListHibernate<UserCVO>(request);
        this.current = GroupCVOFacade.preparaGroupCVOParaCrear(userName);
        return SUCCESS;
    }

    @SkipValidation
    public String view() throws Exception {
        try {
            //Comprobacion de si recoge el dato de un boton volver
            if (this.id == null && this.idElement != null) {
                this.id = this.idElement;
            }
            this.usuarios2 = new PaginatedListHibernate<UserCVO>(request);
            if (actionName.equals("view")) {
                this.current = GroupCVOFacade.obtainGroupCVOFromView(userName, Long.valueOf(id), usuarios2);
            } else if (actionName.equals("edit")) {
                this.current = GroupCVOFacade.obtainGroupCVOFromEdit(userName, Long.valueOf(id), usuarios2);
            }
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("group.group.message.view.error"));
            return INPUT;
        }
    }

    public String edit() throws Exception {
        try {
            this.current = GroupCVOFacade.updateGroupCVO(userName, current);
            addActionMessage(getText("group.group.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("group.group.message.edit.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String del() throws Exception {
        try {
            GroupCVOFacade.delUserFromGroup(userName, Long.valueOf(this.idGroup), Long.valueOf(id));
            addActionMessage(getText("group.group.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("group.group.message.del.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String delete() throws Exception {
        GroupCVOFacade.deleteGroup(userName, Long.valueOf(id));
        return SUCCESS;
    }

    public String create() throws Exception {
        if (current != null) {
            try {
                this.current = GroupCVOFacade.createGroupCVO(userName, current);
                this.id = current.getId().toString();
                addActionMessage(getText("group.group.message.crear"));
                return SUCCESS;
            } catch (InternalErrorException ex) {
                logger.error("excepcion creando grupo: " + ex.getMessage());
                //Añadimos un mensaje que indique al usuario que ha habido errores
                //durante la realizacion de la accion.
                addActionError(getText("group.group.message.crear.error"));
                return INPUT;
            }
        }
        return INPUT;
    }

    @Override
    public void validate() {
        //Para que si hay algun error de validacion no de error la lista paginada
        this.usuarios2 = new PaginatedListHibernate<UserCVO>(request);
        try {
            if (current.getId() != null) {
                GroupCVO g = GroupCVOFacade.obtainGroupCVOFromEdit(userName, Long.valueOf(current.getId()), usuarios2);
                current.setUsuarios(g.getUsuarios());
            }
        } catch (DaoException ex) {
            Logger.getLogger(GroupActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnauthorizedAccessException ex) {
            Logger.getLogger(GroupActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(GroupActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Verificamos que el nombre no sea vacío
        if (!Validation.validarVacio(current.getName())) {
            addFieldError("current.name", getText("common.field.required"));
        } else {//Verificamos que no supere la longitud permitida para el campo
            if (current.getName().length() > 60) {
                addFieldError("current.name", getText("common.field.format.notValid"));
            }
        }
        //Verificamos que no supere la longitud permitida para el campo
        if (current.getDescription().length() > 300) {
            addFieldError("current.description", getText("common.field.format.notValid"));
        }
    }

    public GroupCVO getCurrent() {
        return current;
    }

    public void setCurrent(GroupCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public PaginatedListImpl getUsuarios2() {
        return usuarios2;
    }

    public void setUsuarios2(PaginatedListImpl usuarios2) {
        this.usuarios2 = usuarios2;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}