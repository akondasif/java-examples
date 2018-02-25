package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.aplicaciones;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.DaoException;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.acl.model.module.ModuleCVOFacade;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.acl.model.module.vo.ModuleCVO;
import es.devcircus.acl.util.Validation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class ModuleActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private ModuleCVO current;
    private Module modulePopup;
    private Application app;
    private String appId;
    private List<Action> listaActions = new ArrayList<Action>();
    private List<Action> actions = new ArrayList<Action>();
    private List<String> accionesActivas = new ArrayList<String>();
    private PaginatedListImpl acciones;
    private HttpServletRequest request;
    //Guarda la accion de la que viene y si es View el id del elemento
    private String idElement;
    private String action;
    private String userName = getUsername();
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModuleActionHandler.class.getName());
    private String actionName;
   private String tab;
    public ModuleActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {    
         this.acciones = new PaginatedListHibernate<Action>(request);
        this.current = ModuleCVOFacade.preparaModuleCVOParaCrear(userName);
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
        this.acciones = new PaginatedListHibernate<Action>(request);
        
          if (actionName.equals("view")) {
             this.current = ModuleCVOFacade.obtainModuleCVOFromView(userName, Long.valueOf(id), acciones);
           } else if (actionName.equals("edit")) {
              this.current = ModuleCVOFacade.obtainModuleCVOFromView(userName, Long.valueOf(id), acciones);
         } 
          return SUCCESS;
        } catch(InternalErrorException ex) {
            addActionError(getText("module.module.message.view.error"));
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
        try {
            current = ModuleCVOFacade.updateModuleCVO(userName, current);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("module.module.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("module.module.message.edit.error"));
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
        if (ModuleCVOFacade.deleteModule(userName, Long.valueOf(id))) {
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
        if (current != null) {
            try {
                current = ModuleCVOFacade.createModuleCVO(userName, current);
                this.id = current.getId().toString();
                addActionMessage(getText("module.module.message.crear"));
                return SUCCESS;
            } catch (InternalErrorException ex) {
                logger.error("excepcion creando módulo: " + ex.getMessage());
                //Añadimos un mensaje que indique al usuario que ha habido errores
                //durante la realizacion de la accion.
                addActionError(getText("module.module.message.crear.error"));
                return INPUT;
            }
        }
        return INPUT;
    }

    @SkipValidation
    public String recuperarModuleAjax() throws Exception {
        this.modulePopup = ModuleFacade.obtainModule(userName, Long.valueOf(this.id));
        this.modulePopup.setActions(null);
        return SUCCESS;
    }

    @Override
    public void validate() {
        //Para que si hay algun error de validacion no de error la lista paginada
        this.acciones = new PaginatedListHibernate<Action>(request);
        this.listaActions = new ArrayList<Action>();

        try {
            //Si no estamos en creacion recuperamos las listas.
            if (current.getId() != null) {
                ModuleCVO mod = ModuleCVOFacade.obtainModuleCVOFromEdit(userName, current.getId(), acciones);
                current.setApp(mod.getApp());
            } else {//Si estamos en creacion comprobamos el select de aplicación
                current.setListaApps(ApplicationFacade.listApplication(userName));
                List<String> valores = new ArrayList<String>();
                //Sede: Anhadiríamos el -1 si fuera  campo opcional
                //valores.add("-1");

                for (Application s :  current.getListaApps()) {
                    valores.add(s.getId().toString());
                }
                if (!Validation.validarSelect(current.getAppId(), valores)) {
                    addFieldError("current.app", getText("common.field.select.required"));
                }
                //Si hubiera más select  valores.clear();  y otro bucle for

            }
        } catch (DaoException ex) {
            Logger.getLogger(ModuleActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnauthorizedAccessException ex) {
            Logger.getLogger(ModuleActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(ModuleActionHandler.class.getName()).log(Level.SEVERE, null, ex);
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
        
    }

    public ModuleCVO getCurrent() {
        return current;
    }

    public void setCurrent(ModuleCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PaginatedListImpl getAcciones() {
        return acciones;
    }

    public void setAcciones(PaginatedListImpl acciones) {
        this.acciones = acciones;
    }

    public List<Action> getListaActions() {
        return listaActions;
    }

    public void setListaActions(List<Action> listaActions) {
        this.listaActions = listaActions;
    }

    public List<String> getAccionesActivas() {
        return accionesActivas;
    }

    public void setAccionesActivas(List<String> accionesActivas) {
        this.accionesActivas = accionesActivas;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Module getModulePopup() {
        return modulePopup;
    }

    public void setModulePopup(Module modulePopup) {
        this.modulePopup = modulePopup;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
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
}
