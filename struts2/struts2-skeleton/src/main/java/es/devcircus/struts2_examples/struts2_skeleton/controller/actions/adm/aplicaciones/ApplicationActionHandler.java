package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.aplicaciones;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.DaoException;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.devcircus.acl.acl.model.application.ApplicationCVOFacade;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.acl.model.application.vo.ApplicationCVO;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.auditory.model.userauditory.vo.UserAuditoryCVO;
import es.devcircus.acl.util.Validation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class ApplicationActionHandler extends CustomActionSupport implements ServletRequestAware {
    private String id;
    private ApplicationCVO current;
    private Application app;
    private List<Module> listaModules = new ArrayList<Module>();
    private List<String> modulosActivos = new ArrayList<String>();
    private List<Action> listaActions = new ArrayList<Action>();
    private List<String> accionesActivas = new ArrayList<String>();
    private PaginatedListImpl incidencias;
    private List<String> usuariosActivos = new ArrayList<String>();
    private HttpServletRequest request;
//Guarda la accion de la que viene y si es View el id del elemento
    private String idElement;
    private String action;
    private String userName = getUsername();
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ApplicationActionHandler.class.getName());
    private String actionName;
   private String tab;
    public ApplicationActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
        this.current = ApplicationCVOFacade.preparaApplicationCVOParaCrear(userName);
        return SUCCESS;
    }

    /**
     * Método que se encarga de la recuperación del elemento que queremos ver y
     * llevarnos a la jsp correspondientes para poder visualizarlo.
     *
     * @return
     * @throws Exception
     */
    @SkipValidation
    public String view() throws Exception {
          try {
        //Comprobacion de si recoge el dato de un boton volver
        if (this.id == null && this.idElement != null) {
            this.id = this.idElement;
        }
        this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);

        if (actionName.equals("view")) {
            this.current = ApplicationCVOFacade.obtainApplicationCVOFromView(userName, Long.valueOf(id), incidencias);
        } else if (actionName.equals("edit")) {
            this.current = ApplicationCVOFacade.obtainApplicationCVOFromEdit(userName, Long.valueOf(id), incidencias);
        }
  return SUCCESS;
        } catch(InternalErrorException ex) {
            addActionError(getText("application.application.message.view.error"));
            return INPUT;
        }
    }

    /**
     * Método que se encarga de la recuperación del elemento que queremos editar
     * y llevarnos a la jsp correspondientes para poder editarlo.
     *
     * @return
     * @throws Exception
     */
    public String edit() throws Exception {
        this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
        try {
            this.current = ApplicationCVOFacade.updateApplicationCVO(userName, current);
            //Añadimos un mensaje que indique al usuario que la acción se ha realizado con exito.        
            addActionMessage(getText("application.application.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("application.application.message.edit.error"));
            return INPUT;
        }
    }

    /**
     * Método que se encarga de eliminar el elemento de la base de datos.
     *
     * @return
     * @throws Exception
     */
    public String delete() throws Exception {
        //Verificamos que el elemento se ha eliminado correctamente
        if (ApplicationCVOFacade.deleteApplication(userName, Long.valueOf(id))) {
            //Cargamos el mensaje que indique que todo ha ido ok
            addActionMessage(ERROR);
            return SUCCESS;
        } else {
            //Si por el motivo que sea se han producido errores durante la
            //eliminación del elemento, cargamos el mensaje de error, y redirigimos
            //a la página correspondiente.
            addActionError(ERROR);
            return INPUT;
        }
    }

    /**
     * Método que se encarga de la creación del elemento en la base de datos.
     *
     * @return
     * @throws Exception
     */
    public String create() throws Exception {
        this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
        //Verificamos que el elemento que vamos a crear no es nulo.
        if (current != null) {
            try {
                this.current = ApplicationCVOFacade.createApplicationCVO(userName, current);
                this.id = this.current.getId().toString();
                addActionMessage(getText("application.application.message.crear"));
                return SUCCESS;
            } catch (InternalErrorException ex) {
                logger.error("excepcion creando aplicación: " + ex.getMessage());
                //Añadimos un mensaje que indique al usuario que ha habido errores durante la realizacion de la accion.
                addActionError(getText("application.application.message.crear.error"));
                return INPUT;
            }
        }
        return INPUT;
    }

    @SkipValidation
    public String recuperarApplicationAjax() throws Exception {
        this.app = ApplicationFacade.obtainApplication(userName, Long.valueOf(this.id));
        app.setModules(null);
        return SUCCESS;
    }

    @Override
    public void validate() {
        //Para que si hay algun error de validacion no de error la lista paginada
        this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
        try {
            if (current.getId() != null) {
                this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
                ApplicationCVO app = ApplicationCVOFacade.obtainApplicationCVOFromEdit(userName, current.getId(), incidencias);
                current.setActions(app.getActions());
                current.setModules(app.getModules());
                current.setUsuarios(app.getUsuarios());
                current.setIncidencias(app.getIncidencias());
            }
        } catch (DaoException ex) {
            Logger.getLogger(ApplicationActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnauthorizedAccessException ex) {
            Logger.getLogger(ApplicationActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(ApplicationActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Verificamos que el nombre no sea vacío
        if (!Validation.validarVacio(current.getName())) {
            addFieldError("current.name", getText("common.field.required"));
        } else {//Verificamos que no supere la longitud permitida para el campo
            if (current.getName().length() > 60) {
                addFieldError("current.name", getText("common.field.format.notValid"));
            }
        }
        //Verificamos que el code no sea vacío
        if (!Validation.validarVacio(current.getCode())) {
            addFieldError("current.code", getText("common.field.required"));
        } else {//Verificamos que no supere la longitud permitida para el campo
            if (current.getCode().length() > 60) {
                addFieldError("current.code", getText("common.field.format.notValid"));
            }
        }

   //Verificamos que no supere la longitud permitida para el campo
            if (current.getDescription().length() > 300) {
                addFieldError("current.description", getText("common.field.format.notValid"));
            }
        
        //Email obligatorio
        if (!Validation.validarVacio(current.getAdminEmail())) {
            addFieldError("current.adminEmail", getText("common.field.required"));
        } else {
            //Verificamos que no supere la longitud permitida para el campo
            if (current.getAdminEmail().length() > 60) {
                addFieldError("current.adminEmail", getText("common.field.format.notValid"));
            }
            //Email formato correcto                
            if (!Validation.validarEmail(current.getAdminEmail())) {
                addFieldError("current.adminEmail", getText("common.field.format.notValid"));
            }
        }
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public ApplicationCVO getCurrent() {
        return current;
    }

    public void setCurrent(ApplicationCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Action> getListaActions() {
        return listaActions;
    }

    public void setListaActions(List<Action> listaActions) {
        this.listaActions = listaActions;
    }

    public List<Module> getListaModules() {
        return listaModules;
    }

    public void setListaModules(List<Module> listaModules) {
        this.listaModules = listaModules;
    }

    public List<String> getAccionesActivas() {
        return accionesActivas;
    }

    public void setAccionesActivas(List<String> accionesActivas) {
        this.accionesActivas = accionesActivas;
    }

    public List<String> getModulosActivos() {
        return modulosActivos;
    }

    public void setModulosActivos(List<String> modulosActivos) {
        this.modulosActivos = modulosActivos;
    }

    public List<String> getUsuariosActivos() {
        return usuariosActivos;
    }

    public void setUsuariosActivos(List<String> usuariosActivos) {
        this.usuariosActivos = usuariosActivos;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;

    }

    public PaginatedListImpl getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(PaginatedListImpl incidencias) {
        this.incidencias = incidencias;
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
