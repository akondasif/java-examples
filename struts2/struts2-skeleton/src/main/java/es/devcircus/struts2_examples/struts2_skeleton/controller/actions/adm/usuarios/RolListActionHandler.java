package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.authentication.model.rol.RolCVOFacade;
import es.itnoroeste.core.security.authentication.model.rol.RolFacade;
import es.itnoroeste.core.security.authentication.model.rol.vo.Rol;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class RolListActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl roles;
    private String filtroapp;
    private String filtrouser;
    private String filtronombre;
    private String filtrodesc;
    private String filtroactivo;
    private List<Application> listaApps;
    private List<Rol> listaRol;
    private String requestURI = "RolList";
    private HttpServletRequest request;
    private String userName = getUsername();
    private Logger logger = Logger.getLogger(RolListActionHandler.class.getName());

    public RolListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.roles = new PaginatedListHibernate<Rol>(request);
        this.listaApps = ApplicationFacade.listApplication(userName);
        this.filtrouser = "";
        //Si esta activo algun filtro
        if (!filtroapp.equals("-1") || !filtronombre.equals("") || !filtrodesc.equals("") || !filtrouser.equals("") || !filtroactivo.equals("-1")) {
            this.roles = RolCVOFacade.findRol(userName, filtroapp, filtronombre, filtrodesc, filtrouser,filtroactivo,this.roles);
        } else {
            this.roles = RolCVOFacade.getPaginatedlist(userName, this.roles);
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.filtroapp = "-1";
        this.filtroactivo = "-1";
        this.filtronombre = null;
        this.filtrouser = null;
        this.filtrodesc = null;
        this.listaApps = ApplicationFacade.listApplication(userName);
        PaginatedListImpl<Rol> u = new PaginatedListHibernate<Rol>(request);
        this.roles = RolCVOFacade.getPaginatedlist(userName, u);
        return SUCCESS;
    }

    public String delete() throws Exception {   
        try {
            RolCVOFacade.deleteRol(userName, Long.valueOf(id));
            addActionMessage(getText("rol.delete.ok"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            logger.error("excepcion borrando rol: " + ex.getMessage());
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("rol.delete.fail"));
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
        if (RolCVOFacade.activateRol(userName, Long.valueOf(id))) {
            addActionMessage(getText("rol.rol.message.activar"));
            return SUCCESS;
        } else {
            addActionError(getText("rol.rol.message.activar.error"));
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
        if (RolCVOFacade.deActivateRol(userName, Long.valueOf(id))) {
            addActionMessage(getText("rol.rol.message.desactivar"));
            return SUCCESS;
        } else {
            addActionError(getText("rol.rol.message.desactivar.error"));
            return INPUT;
        }
    }
    
    public String rolesAjax() throws Exception {
        listaRol = RolFacade.obtainRolByApp(userName, Long.valueOf(filtroapp));
        for (Rol r : listaRol) {
            r.setApplication(null);
            r.setAcl(null);
        }
        return SUCCESS;
    }

    public PaginatedListImpl getRoles() {
        return roles;
    }

    public String getFiltroactivo() {
        return filtroactivo;
    }

    public void setFiltroactivo(String filtroactivo) {
        this.filtroactivo = filtroactivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFiltroapp() {
        return filtroapp;
    }

    public void setFiltroapp(String filtroapp) {
        this.filtroapp = filtroapp;
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

    public List<Application> getListaApps() {
        return listaApps;
    }

    public void setListaApps(List<Application> listaApps) {
        this.listaApps = listaApps;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public String getFiltrouser() {
        return filtrouser;
    }

    public void setFiltrouser(String filtrouser) {
        this.filtrouser = filtrouser;
    }
    
}
