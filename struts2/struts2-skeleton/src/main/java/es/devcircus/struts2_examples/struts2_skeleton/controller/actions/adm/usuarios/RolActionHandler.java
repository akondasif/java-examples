package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.DaoException;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.security.acl.model.acl.vo.Acl;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.authentication.model.rol.RolCVOFacade;
import es.itnoroeste.core.security.authentication.model.rol.RolFacade;
import es.itnoroeste.core.security.authentication.model.rol.vo.Rol;
import es.devcircus.acl.authentication.model.rol.vo.RolCVO;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.devcircus.acl.util.Validation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class RolActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private String idRol;

    private RolCVO current;
    private Rol rolPopup;
    private Acl acl;
    private String appId;
    private HttpServletRequest request;
    //Guarda la accion de la que viene y si es View el id del elemento
    private String idElement;
    private String action;
    private String userName = getUsername();
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RolActionHandler.class.getName());
    private String actionName;
    //Para guardar la pestaña a la que tenemos que volver
    private String tab;

    public RolActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
      
        this.current = RolCVOFacade.preparaRolCVOParaCrear(userName);
        return SUCCESS;
    }

    @SkipValidation
    public String view() throws Exception {
        try {
            //Comprobacion de si recoge el dato de un boton volver
            if (this.id == null && this.idElement != null) {
                this.id = this.idElement;
            }

            if (actionName.equals("view")) {
                this.current = RolCVOFacade.obtainRolCVOFromView(userName, Long.valueOf(id));
            } else if (actionName.equals("edit")) {
                this.current = RolCVOFacade.obtainRolCVOFromEdit(userName, Long.valueOf(id));
            }
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("rol.rol.message.view.error"));
            return INPUT;
        }
    }

    public String edit() throws Exception {
        try {
            this.current = RolCVOFacade.updateRolCVO(userName, current);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("rol.rol.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("rol.rol.message.edit.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String delete() throws Exception {
        RolCVOFacade.deleteRol(userName, Long.valueOf(id));
        return SUCCESS;
    }

    @SkipValidation
    public String add() throws Exception {
        try {
            UserCVOFacade.addRol2UserCVO(userName, Long.valueOf(id), Long.valueOf(idRol));
//           Añadimos un mensaje que indique al usuario que la acción se ha 
//           realizado con exito.
            addActionMessage(getText("rol.rol.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("rol.rol.message.add.error"));
            return INPUT;
        }
    }

    public String create() throws Exception {
        if (current != null) {
            try {
                this.current = RolCVOFacade.createRolCVO(userName, current, Long.valueOf(this.appId));
                this.id = this.current.getId().toString();
                addActionMessage(getText("rol.rol.message.crear"));
                return SUCCESS;
            } catch (InternalErrorException ex) {
                logger.error("excepcion creando rol: " + ex.getMessage());
                //Añadimos un mensaje que indique al usuario que ha habido errores
                //durante la realizacion de la accion.
                addActionError(getText("rol.rol.message.crear.error"));
                return INPUT;
            }
        }
        return INPUT;
    }

    @SkipValidation
    public String del() throws Exception {
        try {
            RolCVOFacade.delUserFromRol(userName, Long.valueOf(id), Long.valueOf(idRol));
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("rol.rol.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("rol.rol.message.del.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String permisoRolDelete() throws Exception {
        RolCVOFacade.deleteAclEntryRol(userName, Long.valueOf(this.id));
        return SUCCESS;
    }

    @SkipValidation
    public String aumentaOrdenAclEntry() throws Exception {
        RolCVOFacade.aumentaOrdenAclEntryRol(userName, Long.valueOf(id));
        return SUCCESS;
    }

    @SkipValidation
    public String disminuyeOrdenAclEntry() throws Exception {
        RolCVOFacade.disminuyeOrdenAclEntryRol(userName, Long.valueOf(id));
        return SUCCESS;
    }

    @Override
    public void validate() {
//  Para que si hay algun error de validacion no de error la lista paginada
    
        try {
            if (current.getId() != null) {
                RolCVO r = RolCVOFacade.obtainRolCVOFromEdit(userName, this.current.getId());
                current.setApp(r.getApp());
                current.setUsuarios(r.getUsuarios());
        
                current.setModulos(r.getModulos());
            } else {//Si estamos en creacion comprobamos el select de aplicación
                current.setListaApps(ApplicationFacade.listApplication(userName));
                List<String> valores = new ArrayList<String>();
                //Sede: Anhadiríamos el -1 si fuera  campo opcional
                //valores.add("-1");
                for (Application s : current.getListaApps()) {
                    valores.add(s.getId().toString());
                }
                if (!Validation.validarSelect(this.appId, valores)) {
                    addFieldError("current.application", getText("common.field.select.required"));
                }
            }
        } catch (DaoException ex) {
            Logger.getLogger(RolActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnauthorizedAccessException ex) {
            Logger.getLogger(RolActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(RolActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Verificamos que el nombre no sea vacío
        if (!Validation.validarVacio(current.getName())) {
            addFieldError("current.name", getText("common.field.required"));
        } else {//Verificamos que no supere la longitud permitida para el campo
            if (current.getName().length() > 100) {
                addFieldError("current.name", getText("common.field.format.notValid"));
            }
        }
        //Verificamos que no supere la longitud permitida para el campo
        if (current.getDescription().length() > 300) {
            addFieldError("current.description", getText("common.field.format.notValid"));
        }
    }

    @SkipValidation
    public String recuperarRolAjax() throws Exception {
        rolPopup = RolFacade.obtainRol(userName, Long.valueOf(this.id));
        rolPopup.setApplication(null);
        rolPopup.setAcl(null);      
        return SUCCESS;
    }

    public String getUserName() {
        return userName;
    }

    public RolCVO getCurrent() {
        return current;
    }

    public void setCurrent(RolCVO current) {
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
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

    public Rol getRolPopup() {
        return rolPopup;
    }

    public void setRolPopup(Rol rolPopup) {
        this.rolPopup = rolPopup;
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
