package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.aplicaciones;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.acl.model.module.ModuleCVOFacade;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ModuleListActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl modules;
    private String filtroapp;
    private String filtronombre;
    private String filtrocode;
    private String filtrodesc;
    private String filtroactiva;
    private String requestURI = "ModuleList";
    private HttpServletRequest request;
    private List<Application> listaApps;
    private String userName = getUsername();
    private Logger logger = Logger.getLogger(ApplicationListActionHandler.class.getName());

    public ModuleListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.modules = new PaginatedListHibernate<Module>(request);
        this.listaApps = ApplicationFacade.listApplication(userName);
        //Si esta activo algun filtro
        if (!filtroapp.equals("-1") || !filtronombre.equals("") || !filtrocode.equals("") || !filtrodesc.equals("")
                || !filtroactiva.equals("-1")) {
            this.modules = ModuleCVOFacade.findModule(userName, filtroapp, filtronombre, filtrocode,
                    filtrodesc, filtroactiva, this.modules);
        } else {
            this.modules = ModuleCVOFacade.getPaginatedlist(userName, this.modules);
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.listaApps = ApplicationFacade.listApplication(userName);
        this.filtroapp = "-1";
        this.filtronombre = null;
        this.filtrocode = null;
        this.filtrodesc = null;
        this.filtroactiva = "-1";
        PaginatedListImpl<Module> m = new PaginatedListHibernate<Module>(request);
        this.modules = ModuleCVOFacade.getPaginatedlist(userName, m);
        return SUCCESS;
    }

    public String delete() throws Exception {
    try {
            ModuleCVOFacade.deleteModule(userName, Long.valueOf(id));
            addActionMessage(getText("aplicaciones.modulos.delete.ok"));      
            return SUCCESS;
        } catch (InternalErrorException ex) {
            logger.error("excepcion borrando aplicación: " + ex.getMessage());
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.modulos.delete.fail"));   
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
        //Controlamos que la operacion se realizase con exito.   
        if (ModuleCVOFacade.activateModule(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("aplicaciones.modulos.message.activar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.modulos.message.activar.error"));
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
        //Controlamos que la operacion se realizase con exito.  
        if (ModuleCVOFacade.deActivateModule(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("aplicaciones.modulos.message.desactivar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.modulos.message.desactivar.error"));
            return INPUT;
        }
    }

    public PaginatedListImpl getModules() {
        return modules;
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

    public String getUserName() {
        return userName;
    }

   
}
