package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.devcircus.acl.authentication.model.group.GroupCVOFacade;
import es.itnoroeste.core.security.authentication.model.group.vo.Group;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class GroupListActionHandler extends CustomActionSupport implements ServletRequestAware {
    private String id;
    private PaginatedListImpl groups;
    private String filtrouser;
    private String filtronombre;
    private String filtrodesc;
    private String filtroactivo;
    private String requestURI = "GroupList";
    private HttpServletRequest request;
    private String userName = getUsername();
    private Logger logger = Logger.getLogger(GroupListActionHandler.class.getName());

    public GroupListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.groups = new PaginatedListHibernate<Group>(request);
        //Si esta activo algún filtro.
        if (!filtrouser.equals("") || !filtronombre.equals("") || !filtrodesc.equals("") || !filtroactivo.equals("-1")) {
            this.groups = GroupCVOFacade.findGroup(getUsername(), filtrouser, filtronombre, filtrodesc, filtroactivo, this.groups);
        } else {
            this.groups = GroupCVOFacade.getPaginatedlist(userName, this.groups);
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.filtrouser = null;
        this.filtronombre = null;
        this.filtrodesc = null;
        this.filtroactivo = "-1";
        PaginatedListImpl<Group> u = new PaginatedListHibernate<Group>(request);
        this.groups = GroupCVOFacade.getPaginatedlist(userName, u);
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            GroupCVOFacade.deleteGroup(userName, Long.valueOf(id));
            addActionMessage(getText("group.delete.ok"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            logger.error("excepcion borrando grupo: " + ex.getMessage());
            addActionError(getText("group.delete.fail"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    public String active() throws Exception {
        if (GroupCVOFacade.activateGroup(userName, Long.valueOf(id))) {
            addActionMessage(getText("group.group.message.activar"));
            return SUCCESS;
        } else {
            addActionError(getText("group.group.message.activar.error"));
            return INPUT;
        }
    }

    /**
     * Método que desactiva el elemento en cuestión con el que estamos
     * trabajando
     *
     * @return
     * @throws Exception
     */
    public String deactive() throws Exception {
        if (GroupCVOFacade.deActivateGroup(userName, Long.valueOf(id))) {
            addActionMessage(getText("group.group.message.desactivar"));
            return SUCCESS;
        } else {
            addActionError(getText("group.group.message.desactivar.error"));
            return INPUT;
        }
    }

    public PaginatedListImpl getGroups() {
        return groups;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFiltroactivo() {
        return filtroactivo;
    }

    public void setFiltroactivo(String filtroactivo) {
        this.filtroactivo = filtroactivo;
    }

    public String getFiltrouser() {
        return filtrouser;
    }

    public void setFiltrouser(String filtrouser) {
        this.filtrouser = filtrouser;
    }

    public String getFiltrodesc() {
        return filtrodesc;
    }

    public void setFiltrodesc(String filtrodesc) {
        this.filtrodesc = filtrodesc;
    }

    public String getFiltronombre() {
        return filtronombre;
    }

    public void setFiltronombre(String filtronombre) {
        this.filtronombre = filtronombre;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getUserName() {
        return userName;
    }
}
