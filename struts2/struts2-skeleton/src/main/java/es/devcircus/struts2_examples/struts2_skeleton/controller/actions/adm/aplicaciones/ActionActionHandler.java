package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.aplicaciones;

import com.opensymphony.xwork2.ActionSupport;
import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios.RolActionHandler;
import es.itnoroeste.core.exceptions.DaoException;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.devcircus.acl.acl.model.action.ActionCVOFacade;
import es.itnoroeste.core.security.acl.model.action.ActionFacade;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.action.vo.Action.ActionType;
import es.devcircus.acl.acl.model.action.vo.ActionCVO;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.util.Validation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class ActionActionHandler extends CustomActionSupport {

    private String id;
    private ActionCVO current;
    private Action actionPopup;
    private Application app;
    private Module module;
    private List<Module> listaModulos;
    private List<Application> listaApps;
//Guarda la accion de la que viene y si es View el id del elemento
    private String idElement;
    private String action;
    private String actionName;
    private String userName = getUsername();
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ActionActionHandler.class.getName());
    private List<Action.ActionType> tipos = Arrays.asList(Action.ActionType.values());
    private String actionType;
    private List<String> listaAmbitosActivos = new ArrayList<String>();
   private String tab;
    public ActionActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.current = ActionCVOFacade.preparaAccionCVOParaCrear(userName);
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

            if (actionName.equals("view")) {
                this.current = ActionCVOFacade.obtainActionCVOFromView(userName, Long.valueOf(id));
            } else if (actionName.equals("edit")) {
                this.current = ActionCVOFacade.obtainActionCVOFromView(userName, Long.valueOf(id));
            } 


            //Verificamos que se ha recuperado el elemento sin problemas.
            if (current == null) {
                addActionError(ERROR);
                return INPUT;
            }

            //Verificamos que se ha recuperado el objeto correctamente
            if (current.getModule() == null) {
                addActionError("Imposible recuperar el módulo de la acción");
            }
            //Verificamos que se ha recuperado el objeto correctamente.
            if (current.getApp() == null) {
                addActionError("Imposible recuperar la aplicación de la acción");
            }
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("action.action.message.view.error"));
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
            current = ActionCVOFacade.updateActionCVO(userName, current);
            addActionMessage(getText("action.action.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("action.action.message.edit.error"));
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
        if (ActionCVOFacade.deleteAction(userName, Long.valueOf(id))) {
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
                ActionCVOFacade.createActionCVO(userName, current);
                this.id = current.getId().toString();
                addActionMessage(getText("action.action.message.crear"));
                return SUCCESS;
            } catch (InternalErrorException ex) {
                logger.error("excepcion creando acción: " + ex.getMessage());
                //Añadimos un mensaje que indique al usuario que ha habido errores
                //durante la realizacion de la accion.
                addActionError(getText("action.action.message.crear.error"));
                return INPUT;
            }
        }
        return INPUT;
    }

    @SkipValidation
    public String recuperarActionAjax() throws Exception {
        this.actionPopup = ActionFacade.obtainAction(userName, Long.valueOf(this.id));
        return SUCCESS;
    }

    @Override
    public void validate() {
        //Para que si hay algun error de validacion no de error la lista paginada
        try {
            //Si no estamos en creacion recuperamos las listas.
            if (current.getId() != null) {
                this.listaApps = new ArrayList<Application>();
                this.listaModulos = new ArrayList<Module>();
                ActionCVO act = ActionCVOFacade.obtainActionCVOFromEdit(userName, current.getId());
                //Recuperamos la informacion del módulo al que pertenece la accion      
                current.setModule(act.getModule());
                current.setApp(act.getApp());
            } else {//Si estamos en creacion comprobamos el select de aplicación y módulo
                this.listaApps = ApplicationFacade.listApplication(userName);
                if (!current.getAppId().equals("-1")) {
                    this.listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, Long.valueOf(current.getAppId()));
                } else {
                    this.listaModulos = new ArrayList<Module>();
                }

                List<String> valores = new ArrayList<String>();
                for (Application s : ApplicationFacade.listApplication(userName)) {
                    valores.add(s.getId().toString());
                }

                if (!Validation.validarSelect(current.getAppId(), valores)) {
                    addFieldError("current.application", getText("common.field.select.required"));
                }

                valores.clear();
                for (Module s : ModuleFacade.listModule(userName)) {
                    valores.add(s.getId().toString());
                }
                if (!Validation.validarSelect(current.getModuloId(), valores)) {
                    addFieldError("current.module", getText("common.field.select.required"));
                }
            }

        } catch (DaoException ex) {
            Logger.getLogger(ActionActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnauthorizedAccessException ex) {
            Logger.getLogger(ActionActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(ActionActionHandler.class.getName()).log(Level.SEVERE, null, ex);
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
            if (current.getCode().length() > 150) {
                addFieldError("current.code", getText("common.field.format.notValid"));
            }
        }
        List<String> tiposAction = new ArrayList<String>();
        for (Action.ActionType t : tipos) {
            tiposAction.add(t.name().toLowerCase());
        }
        if (!current.getActionType().equals("-1") && !Validation.validarSelect(current.getActionType().toLowerCase(), tiposAction)) {
            addFieldError("current.actionType", getText("common.field.format.notValid"));
        }

 //Verificamos que no supere la longitud permitida para el campo
            if (current.getDescription().length() > 300) {
                addFieldError("current.description", getText("common.field.format.notValid"));
            }
        

    }

    public List<ActionType> getTipos() {
        return tipos;
    }

    public void setTipos(List<ActionType> tipos) {
        this.tipos = tipos;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public ActionCVO getCurrent() {
        return current;
    }

    public void setCurrent(ActionCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
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

    public Action getActionPopup() {
        return actionPopup;
    }

    public void setActionPopup(Action actionPopup) {
        this.actionPopup = actionPopup;
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

    public List<String> getListaAmbitosActivos() {
        return listaAmbitosActivos;
    }

    public void setListaAmbitosActivos(List<String> listaAmbitosActivos) {
        this.listaAmbitosActivos = listaAmbitosActivos;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
    
}
