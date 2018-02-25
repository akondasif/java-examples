package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.aplicaciones;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.devcircus.acl.acl.model.application.ApplicationCVOFacade;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ApplicationListActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl applications;
    private String filtronombre;
    private String filtrocode;
    private String filtrodesc;
    private String filtroactiva;
    private String filtromantenimiento;
    private String filtroemail;
    private String requestURI = "ApplicationList";
    private HttpServletRequest request;
    private String userName = getUsername();
    private Logger logger = Logger.getLogger(ApplicationListActionHandler.class.getName());

    public ApplicationListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.applications = new PaginatedListHibernate<Application>(request);
        //Si esta activo algun filtro
        if (!filtronombre.equals("") || !filtrocode.equals("") || !filtrodesc.equals("")
                || !filtroactiva.equals("-1") || !filtromantenimiento.equals("-1") || !filtroemail.equals("")) {
            this.applications = ApplicationCVOFacade.findApplication(userName, filtronombre, filtrocode,
                    filtrodesc, filtroactiva, filtromantenimiento, filtroemail, this.applications);
        } else {
            this.applications = ApplicationCVOFacade.getPaginatedlist(userName, this.applications);
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.filtronombre = null;
        this.filtrocode = null;
        this.filtrodesc = null;
        this.filtroactiva = null;
        this.filtromantenimiento = null;
        this.filtroemail = null;
        PaginatedListImpl<Application> a = new PaginatedListHibernate<Application>(request);
        this.applications = ApplicationCVOFacade.getPaginatedlist(userName, a);
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            ApplicationCVOFacade.deleteApplication(userName, Long.valueOf(id));
            addActionMessage(getText("aplicaciones.aplicaciones.delete.ok"));        
            return SUCCESS;
        } catch (InternalErrorException ex) {
            logger.error("excepcion borrando aplicación: " + ex.getMessage());
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.aplicaciones.delete.fail"));
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
        if (ApplicationCVOFacade.activateApplication(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("aplicaciones.aplicaciones.message.activar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.aplicaciones.message.activar.error"));
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
        if (ApplicationCVOFacade.deActivateApplication(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("aplicaciones.aplicaciones.message.desactivar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.aplicaciones.message.desactivar.error"));
            return INPUT;
        }
    }

    public String activeMaintenance() throws Exception {
        //Controlamos que la operacion se realizase con exito.   
        if (ApplicationCVOFacade.activateSupportApplication(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("aplicaciones.aplicaciones.message.activarmantenimiento"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.aplicaciones.message.activarmantenimiento.error"));
            return INPUT;
        }
    }

    public String deactiveMaintenance() throws Exception {
        //Controlamos que la operacion se realizase con exito.   
        if (ApplicationCVOFacade.deActivateSupportApplication(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("aplicaciones.aplicaciones.message.desactivarmantenimiento"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("aplicaciones.aplicaciones.message.desactivarmantenimiento.error"));
            return INPUT;
        }
    }

    public PaginatedListImpl getApplications() {
        return applications;
    }

    public String getFiltroactiva() {
        return filtroactiva;
    }

    public void setFiltroactiva(String filtroactiva) {
        this.filtroactiva = filtroactiva;
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

    public String getFiltroemail() {
        return filtroemail;
    }

    public void setFiltroemail(String filtroemail) {
        this.filtroemail = filtroemail;
    }

    public String getFiltromantenimiento() {
        return filtromantenimiento;
    }

    public void setFiltromantenimiento(String filtromantenimiento) {
        this.filtromantenimiento = filtromantenimiento;
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
