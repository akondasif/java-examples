package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.devcircus.acl.authentication.model.group.GroupCVOFacade;
import es.itnoroeste.core.security.authentication.model.group.vo.Group;
import es.devcircus.acl.authentication.model.group.vo.GroupCVO;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class GroupAddUsuarioActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private GroupCVO current;
    private PaginatedListImpl usuarios2;
    private HttpServletRequest request;
    private String idGroup;
    //Id del elemento del boton volver
    private String idElement;
    private String action;
    private Boolean check = true;
    private String filtroNIF;
    private String filtroapellidos;
    private String filtronombre;
    private String requestURI = "GroupAddUsuarioStep0";
    private String userName = getUsername();
    private String tab;

    public GroupAddUsuarioActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        PaginatedListImpl usuarios3 = new PaginatedListHibernate<UserCVO>(request);
        this.current = GroupCVOFacade.obtainGroupCVOFromView(userName, Long.valueOf(idGroup), usuarios3);
        this.usuarios2 = new PaginatedListHibernate<UserCVO>(request);
      
             if(!filtroNIF.equals("") ||!filtronombre.equals("") ||!filtroapellidos.equals("")
                      ){
            this.usuarios2 = UserCVOFacade.findUserNoGroupFiltros(userName, idGroup, filtronombre, filtroapellidos, filtroNIF, usuarios2);
        } else {
            this.usuarios2 = UserCVOFacade.findUserNoGroup(userName, idGroup, this.usuarios2);
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.filtroNIF = null;
        this.filtronombre = null;
        this.filtroapellidos = null;
        this.usuarios2 = new PaginatedListHibernate<UserCVO>(request);
        PaginatedListImpl usuarios3 = new PaginatedListHibernate<UserCVO>(request);
        this.current = GroupCVOFacade.obtainGroupCVOFromView(userName, Long.valueOf(idGroup), usuarios3);
        this.usuarios2 = UserCVOFacade.findUserNoGroup(userName, idGroup, this.usuarios2);
        return SUCCESS;
    }

    public String add() throws Exception {
        try {
            GroupCVOFacade.addUser2Group(userName, Long.valueOf(this.idGroup), Long.valueOf(id));
            //Añadimos un mensaje que indique al usuario que la acción se ha realizado con exito.
            addActionMessage(getText("group.group.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("group.group.message.add.error"));
            return INPUT;
        }
    }

    public String addList() throws Exception {
        try {
            this.current = GroupCVOFacade.addListUser2Group(userName, current);
            this.id = current.getId().toString();
            //Añadimos un mensaje que indique al usuario que la acción se ha realizado con exito.
            addActionMessage(getText("group.group.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("group.group.message.add.error"));
            return INPUT;
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public GroupCVO getCurrent() {
        return current;
    }

    public void setCurrent(GroupCVO current) {
        this.current = current;
    }

    public String getFiltroNIF() {
        return filtroNIF;
    }

    public void setFiltroNIF(String filtroNIF) {
        this.filtroNIF = filtroNIF;
    }

    public String getFiltroapellidos() {
        return filtroapellidos;
    }

    public void setFiltroapellidos(String filtroapellidos) {
        this.filtroapellidos = filtroapellidos;
    }

    public String getFiltronombre() {
        return filtronombre;
    }

    public void setFiltronombre(String filtronombre) {
        this.filtronombre = filtronombre;
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

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getUserName() {
        return userName;
    }

    public PaginatedListImpl getUsuarios2() {
        return usuarios2;
    }

    public void setUsuarios2(PaginatedListImpl usuarios2) {
        this.usuarios2 = usuarios2;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}
