package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.aplicaciones;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.devcircus.acl.acl.model.action.ActionCVOFacade;
import es.itnoroeste.core.security.acl.model.action.ActionFacade;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.devcircus.acl.acl.model.application.ApplicationCVOFacade;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.acl.model.application.vo.ApplicationCVO;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.auditory.model.userauditory.vo.UserAuditoryCVO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ActionListActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl actions;
    private String filtroapp;
    private String filtrorol;
    private String filtromod;
    private String filtronombre;
    private String filtrocode;
    private String filtrodesc;
    private String filtroactiva;
    private String requestURI = "ActionList";
    private HttpServletRequest request;
    private List<Application> listaApps;
    private List<Module> listaModulos;
    private String userName = getUsername();
    private Logger logger = Logger.getLogger(ApplicationListActionHandler.class.getName());
    private PaginatedListImpl incidencias;

    public ActionListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.actions = new PaginatedListHibernate<Action>(request);
        this.listaApps = ApplicationFacade.listApplication(userName);
        this.listaModulos = new ArrayList<Module>();
        //Si esta activo algun filtro
        if (!filtroapp.equals("-1") || !filtromod.equals("-1") || !filtronombre.equals("") || !filtrocode.equals("") || !filtrodesc.equals("")
                || !filtroactiva.equals("-1")) {
            this.actions = ActionCVOFacade.findAction(userName, filtroapp, filtromod, filtronombre, filtrocode, filtrodesc, filtroactiva, this.actions);
        } else {
            this.actions = ActionCVOFacade.getPaginatedlist(userName, this.actions);
        }
        /**
         * ****Comprobamos los combos marcados para cargar las listas
         * necesarias*****
         */
        if (!this.filtroapp.equals("-1")) {
            this.listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, Long.valueOf(filtroapp));
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.listaApps = ApplicationFacade.listApplication(userName);
        this.filtroapp = "-1";
        this.listaModulos = new ArrayList<Module>();
        this.filtrorol = "-1";
        this.filtromod = "-1";
        this.filtronombre = null;
        this.filtrocode = null;
        this.filtrodesc = null;
        this.filtroactiva = "-1";
        PaginatedListImpl<Action> u = new PaginatedListHibernate<Action>(request);
        this.actions = ActionCVOFacade.getPaginatedlist(userName, u);
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            ActionCVOFacade.deleteAction(userName, Long.valueOf(id));
            addActionMessage(getText("aplicaciones.acciones.delete.ok"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            logger.error("excepcion borrando aplicación: " + ex.getMessage());
            addActionError(getText("aplicaciones.acciones.delete.fail"));
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
        if (ActionCVOFacade.activateAction(userName, Long.valueOf(id))) {
            addActionMessage(getText("aplicaciones.acciones.message.activar"));
            return SUCCESS;
        } else {
            addActionError(getText("aplicaciones.acciones.message.activar.error"));
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
        if (ActionCVOFacade.deActivateAction(userName, Long.valueOf(id))) {
            addActionMessage(getText("aplicaciones.acciones.message.desactivar"));
            return SUCCESS;
        } else {
            addActionError(getText("aplicaciones.acciones.message.desactivar.error"));
            return INPUT;
        }
    }

    public String modulosAjax() throws Exception {
        listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, Long.valueOf(filtroapp));
        for (Module m : listaModulos) {
            m.setActions(ModuleFacade.getAllActionsFromModule(userName, m.getId()));
        }
        return SUCCESS;
    }

    public String modulosCodeAjax() throws Exception {
        Application ap = ApplicationFacade.obtainApplicationByCode(userName, filtroapp);
        listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, ap.getId());
        for (Module m : listaModulos) {
            m.setActions(ModuleFacade.getAllActionsFromModule(userName, m.getId()));
        }
        return SUCCESS;
    }

    public PaginatedListImpl getActions() {
        return actions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFiltroactiva() {
        return filtroactiva;
    }

    public void setFiltroactiva(String filtroactiva) {
        this.filtroactiva = filtroactiva;
    }

    public String getFiltroapp() {
        return filtroapp;
    }

    public void setFiltroapp(String filtroapp) {
        this.filtroapp = filtroapp;
    }

    public String getFiltrocode() {
        return filtrocode;
    }

    public void setFiltrocode(String filtrocode) {
        this.filtrocode = filtrocode;
    }

    public String getFiltrodesc() {
        return filtrodesc;
    }

    public void setFiltrodesc(String filtrodesc) {
        this.filtrodesc = filtrodesc;
    }

    public String getFiltromod() {
        return filtromod;
    }

    public void setFiltromod(String filtromod) {
        this.filtromod = filtromod;
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

    public String getFiltrorol() {
        return filtrorol;
    }

    public void setFiltrorol(String filtrorol) {
        this.filtrorol = filtrorol;
    }

    public List<Application> getListaApps() {
        return listaApps;
    }

    public void setListaApps(List<Application> listaApps) {
        this.listaApps = listaApps;
    }

    public List<Module> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Module> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public String getUserName() {
        return userName;
    }

   
}
