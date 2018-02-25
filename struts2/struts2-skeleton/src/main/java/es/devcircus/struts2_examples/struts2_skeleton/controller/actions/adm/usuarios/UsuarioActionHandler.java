package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.imaro.pp.common.territorio.comunidad.ComunidadFacade;
import es.imaro.pp.common.territorio.comunidad.vo.Comunidad;
import es.imaro.pp.common.territorio.provincia.vo.Provincia;
import es.imaro.pp.common.territorio.vo.Territorio;
import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.aclentry.AclEntryFacade;
import es.devcircus.acl.acl.model.aclentry.vo.AclEntryCVO;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.auditory.model.userauditory.vo.UserAuditory;
import es.devcircus.acl.auditory.model.userauditory.vo.UserAuditoryCVO;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;
import es.devcircus.acl.util.Validation;
import es.itnoroeste.social.address.vo.Address;
import es.itnoroeste.social.address.vo.Address.TipoVia;
import es.itnoroeste.social.listfield.vo.ListField;
import es.itnoroeste.social.person.vo.Person;
import es.itnoroeste.social.person.vo.Person.TipoDocumento;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class UsuarioActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private String idApp;
    private UserCVO current;
    private UserCVO usuarioPopup;
    private String direccionPrincipal;
    private String telefonoPrincipal;
    private String emailPrincipal;
    private String nuevoTelefono;
    private String nuevaAddress;
    private String nuevoEmail;
    private List<Action> actions = new ArrayList<Action>();
  

    private PaginatedListImpl incidencias;
    private HttpServletRequest request;
//Guarda la accion de la que viene y si es View el id del elemento
    private String idElement;
    private Address direccion;
    private String formulario;
    private List<Person.TipoDocumento> tiposDocumento = Arrays.asList(Person.TipoDocumento.values());
    private List<Comunidad> listaComunidades;
    private List<Provincia> listaProvincias = new ArrayList<Provincia>();
    private List<Territorio> listaIslas = new ArrayList<Territorio>();
    private List<Territorio> listaLocalidades = new ArrayList<Territorio>();
    private String filtrocomunidad;
    private String filtroprovincia;
    private String filtroisla;
    private List<Address.TipoVia> tipos = Arrays.asList(Address.TipoVia.values());
    private String tipovia;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UsuarioActionHandler.class.getName());
    private String actionName;
    private String action;
    private String userName = getUsername();
    private Boolean aclActive;
    //Para guardar la pestaña a la que tenemos que volver
    private String tab;
    private String requestURI = "UsuarioPermisos2";
    private String idUser;

    public UsuarioActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
        this.listaComunidades = ComunidadFacade.listComunidad(userName);
        return SUCCESS;
    }

    @SkipValidation
    public String view() throws Exception {
        try {
            //Comprobacion de si recoge el dato de un boton volver
            if (this.id == null && this.idElement != null) {
                this.id = this.idElement;
            }
            if (this.id == null && current.getId().toString() != null) {
                this.id = current.getId().toString();
            }
            List<Application> apps = ApplicationFacade.listApplication(userName);
            this.idApp = apps.get(0).getId().toString();
            this.incidencias = new PaginatedListHibernate<UserAuditory>(request);

            if (actionName.equals("view")) {
                this.current = UserCVOFacade.obtainUserCVOFromView(userName, Long.valueOf(id), incidencias);
            } else if (actionName.equals("edit")) {
                this.current = UserCVOFacade.obtainUserCVOFromEdit(userName, Long.valueOf(id), incidencias);
            }
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.usuarios.message.view.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String recuperarPermisos() throws Exception {
        this.incidencias = new PaginatedListHibernate<UserAuditory>(request);
        List<Application> apps = ApplicationFacade.listApplication(userName);
        if (current == null) {
            this.idApp = apps.get(0).getId().toString();
            this.current = UserCVOFacade.recuperarPermisosFromUserAndApp(userName, Long.valueOf(this.id), apps.get(0).getId(), incidencias);
        } else {
            this.current = UserCVOFacade.recuperarPermisosFromUserAndApp(userName, current.getId(), Long.valueOf(this.idApp), incidencias);
        }
        return SUCCESS;
    }

    @SkipValidation
    public String permisoDelete() throws Exception {
        UserCVOFacade.deleteAclEntryUser(userName, Long.valueOf(this.id));
        return SUCCESS;
    }

    @SkipValidation
    public String aumentaOrdenAclEntry() throws Exception {
        UserCVOFacade.aumentaOrdenAclEntryUser(userName, Long.valueOf(id));
        return SUCCESS;
    }

    @SkipValidation
    public String disminuyeOrdenAclEntry() throws Exception {
        UserCVOFacade.disminuyeOrdenAclEntryUser(userName, Long.valueOf(id));
        return SUCCESS;
    }

    public String edit() throws Exception {
        try {           
            this.current = UserCVOFacade.updateUserCVO(userName, current);
            this.id = current.getId().toString();
//            Añadimos un mensaje que indique al usuario que la acción se ha realizado con exito.     
            addActionMessage(getText("usuarios.usuarios.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.usuarios.message.edit.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String delete() throws Exception {
        UserCVOFacade.deleteUser(userName, Long.valueOf(id));
        return SUCCESS;
    }

    public String create() throws Exception {
        return SUCCESS;
    }

    @SkipValidation
    public String checkTelefono() throws Exception {
        try {
            UserCVOFacade.checkTelefono(userName, Long.valueOf(id), this.nuevoTelefono);
            addActionMessage(getText("usuarios.usuarios.message.checktelefono"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.usuarios.message.checktelefono.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    @SkipValidation
    public String checkEmail() throws Exception {
        try {
            UserCVOFacade.checkEmail(userName, Long.valueOf(id), this.nuevoEmail);
            addActionMessage(getText("usuarios.usuarios.message.checkemail"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.usuarios.message.checkemail.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    @SkipValidation
    public String checkAddress() throws Exception {
        try {
            UserCVOFacade.checkAddress(userName, Long.valueOf(id), this.direccionPrincipal);
            addActionMessage(getText("usuarios.usuarios.message.checkdireccion"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.usuarios.message.checkdireccion.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    @SkipValidation
    public String delAddress() throws Exception {
        try {
            UserCVOFacade.delAddress(userName, Long.valueOf(id), this.direccion, this.direccionPrincipal);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.address.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.address.message.del.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    public String addAddress() throws Exception {
        try {
            //Creamos el objeto direccion
            if (!tipovia.equals("-1")) {
                this.direccion.setType(tipovia);
            }
            UserCVOFacade.addAddress(userName, Long.valueOf(id), this.direccion);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.address.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.address.message.add.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    @SkipValidation
    public String delTelefono() throws Exception {
        try {
            UserCVOFacade.delTelefono(userName, Long.valueOf(id), this.nuevoTelefono);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.telefono.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.telefono.message.del.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    @SkipValidation
    public String delEmail() throws Exception {
        try {
            UserCVOFacade.delEmail(userName, Long.valueOf(id), this.nuevoEmail);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.mail.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.mail.message.del.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    public String addTelefono() throws Exception {
        try {
            UserCVOFacade.addTelefono(userName, Long.valueOf(id), this.nuevoTelefono);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.telefono.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.telefono.message.add.error"));
            return INPUT;
        }
    }

    public String addEmail() throws Exception {
        try {
            UserCVOFacade.addEmail(userName, Long.valueOf(id), this.nuevoEmail);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.mail.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("usuarios.mail.message.add.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String recuperarUserAjax() throws Exception {
        this.incidencias = new PaginatedListHibernate<UserAuditory>(request);
        this.usuarioPopup = UserCVOFacade.obtainUserCVOFromView(userName, Long.valueOf(this.id), incidencias);

        for (Address a : this.usuarioPopup.getAddresses()) {
            if (a.getPrimar().equals(Boolean.TRUE)) {
                usuarioPopup.setDireccionPrincipal(a.getFormatted().replace("<br>", "\n"));
            }
        }
        this.usuarioPopup.setAccionesActivas(null);
        this.usuarioPopup.setAccionesDenegadas(null);
        this.usuarioPopup.setApp(null);
        this.incidencias = null;
        this.usuarioPopup.setEmails(null);
        this.usuarioPopup.setIncidencias(null);
        this.usuarioPopup.setListanoroles(null);
        this.usuarioPopup.setListaroles(null);
        this.usuarioPopup.setModulos(null);
        this.usuarioPopup.setRoles(null);
        this.usuarioPopup.setAcls(null);
        this.usuarioPopup.setAddresses(null);
        this.usuarioPopup.setEmails(null);
        this.usuarioPopup.setPhoneNumbers(null);
        this.usuarioPopup.setSedes(null);
        this.usuarioPopup.setDepartamentos(null);
        this.usuarioPopup.setAclEntries(null);
        return SUCCESS;
    }

    @Override
    public void validate() {
        try {
            if (this.formulario.equals("formUpdate") || this.formulario.equals("formCuenta")) {
                this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
                UserCVO user = UserCVOFacade.obtainUserCVOFromEdit(userName, current.getId(), incidencias);
                this.current.setAddresses(user.getAddresses());
                this.current.setPhoneNumbers(user.getPhoneNumbers());
                this.current.setEmails(user.getEmails());
                this.current.setApp(user.getApp());
                this.current.setListaroles(user.getListaroles());
                this.current.setListanoroles(user.getListanoroles());
                this.current.setModulos(user.getModulos());
                this.current.setDepartamentos(user.getDepartamentos());
                this.current.setSedes(user.getSedes());
                this.current.setIncidencias(user.getIncidencias());
                //si no se ha cambiado la foto recuperamos la que estaba guardada
                if (this.current.getFileUpload() == null) {
                    this.current.setFileUpload(user.getFileUpload());
                    this.current.setFileUploadContentType(user.getFileUploadContentType());
                    this.current.setFileUploadFileName(user.getFileUploadFileName());
                }
            } else {
                this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
                this.current = UserCVOFacade.obtainUserCVOFromEdit(userName, Long.valueOf(id), incidencias);
            }

            Boolean b;
            for (Address a : this.current.getAddresses()) {
                b = a.getPrimar();
                if (b) {
                    current.setDireccionPrincipal(a.getId().toString());
                }
            }
            for (ListField t : this.current.getPhoneNumbers()) {
                if (t.getPrimar().equals(Boolean.TRUE)) {
                    current.setTelefonoPrincipal(t.getValue());
                }
            }
            for (ListField t : this.current.getEmails()) {
                if (t.getPrimar()) {
                    current.setEmailPrincipal(t.getValue());
                }
            }

            if (this.formulario.equals("formTelefono")) {
                if (!Validation.validarVacio(this.nuevoTelefono)) {
                    addFieldError("nuevoTelefono", getText("common.field.required"));
                } else {
                    //Verificamos que no supere la longitud permitida para el campo
                    if (this.nuevoTelefono.length() > 13) {
                        addFieldError("nuevoTelefono", getText("common.field.format.notValid"));
                    } else {
                        if (!Validation.validarNumeros(this.nuevoTelefono)) {
                            addFieldError("nuevoTelefono", getText("common.field.format.notValid"));
                        }
                    }
                }
                
            } else if (this.formulario.equals("formEmail")) {
                //Validacion email
                if (!Validation.validarVacio(this.nuevoEmail)) {
                    addFieldError("nuevoEmail", getText("common.field.required"));
                } else {
                    //Verificamos que no supere la longitud permitida para el campo
                    if (this.nuevoEmail.length() > 50) {
                        addFieldError("nuevoEmail", getText("common.field.format.notValid"));
                    }
                    //Email formato correcto                
                    if (!Validation.validarEmail(this.nuevoEmail)) {
                        addFieldError("nuevoEmail", getText("common.field.format.notValid"));
                    }
                }
                
            } else {
                if (current.getPassword2() != null && !current.getPassword2().isEmpty()) {
                    if (current.getPassword2() != null && !current.getPassword2().isEmpty()) {
                        //validamos que sean iguales
                        if (!Validation.validarIguales(current.getPassword2(), current.getPassword3())) {
                            addFieldError("password3", getText("common.field.password.notEquals"));
                        }
                        if (!Validation.validarPassword(current.getPassword2())) {
                            addFieldError("password2", getText("common.field.format.notValid"));
                        }
                    }
                }
                //Validacion update
                //Verificamos que el nombre no sea vacío
                if (!Validation.validarVacio(current.getName().getGivenName())) {
                    addFieldError("current.nombre", getText("common.field.required"));
                } else {//Verificamos que no supere la longitud permitida para el campo
                    if (current.getName().getGivenName().length() > 50) {
                        addFieldError("current.nombre", getText("common.field.format.notValid"));
                    }
                }

                //Verificamos que los apellidos no sea vacío
                if (!Validation.validarVacio(current.getName().getFamilyName())) {
                    addFieldError("current.familyName", getText("common.field.required"));
                } else {//Verificamos que no supere la longitud permitida para el campo
                    if (current.getName().getFamilyName().length() > 50) {
                        addFieldError("current.familyName", getText("common.field.format.notValid"));
                    }
                }

                if (current.getBirthday() != null) {
                    if (!es.devcircus.acl.util.Validation.validarFechaString(current.getBirthday())) {
                        addFieldError("current.birthday", getText("common.field.data.format.notValid"));
                    }
                }

                //Verificamos que el nombre de usuario no sea vacío
                if (!Validation.validarVacio(current.getUsername())) {
                    addFieldError("current.username", getText("common.field.required"));
                } else {//Verificamos que no supere la longitud permitida para el campo
                    if (current.getUsername().length() > 30) {
                        addFieldError("current.username", getText("common.field.format.notValid"));
                    }
                }

                //Verificamos que el tipo de documento no sea vacío
                if (!Validation.validarVacio(current.getTipoDocumento().toString())) {
                    addFieldError("current.tipoDocumento", getText("common.field.required"));
                }

                //Verificamos que el doi no sea vacío
                if (!es.itnoroeste.empleados.util.Validation.validarVacio(current.getDoi())) {
                    addFieldError("current.doi", getText("common.field.required"));
                } else {//Verificamos que no supere la longitud permitida para el campo
                    if (current.getDoi().length() > 13) {
                        addFieldError("current.doi", getText("common.field.format.notValid"));
                    } else {//si no es pasaporte hacemos la validacion
                        if (!current.getTipoDocumento().equals(Person.TipoDocumento.PASAPORTE)) {
                            if (!es.itnoroeste.empleados.util.Validation.validaNifNie(current.getDoi())) {
                                addFieldError("current.doi", getText("common.field.format.notValid"));
                            }
                        }
                    }
                }
            }
        } catch (UnauthorizedAccessException ex) {
            Logger.getLogger(UsuarioActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InternalErrorException ex) {
            Logger.getLogger(UsuarioActionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUserName() {
        return userName;
    }

    public UserCVO getCurrent() {
        return current;
    }

    public void setCurrent(UserCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(String direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public String getNuevoTelefono() {
        return nuevoTelefono;
    }

    public void setNuevoTelefono(String nuevoTelefono) {
        this.nuevoTelefono = nuevoTelefono;
    }

    public String getNuevoEmail() {
        return nuevoEmail;
    }

    public void setNuevoEmail(String nuevoEmail) {
        this.nuevoEmail = nuevoEmail;
    }

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public String getTelefonoPrincipal() {
        return telefonoPrincipal;
    }

    public void setTelefonoPrincipal(String telefonoPrincipal) {
        this.telefonoPrincipal = telefonoPrincipal;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public PaginatedListImpl getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(PaginatedListImpl incidencias) {
        this.incidencias = incidencias;
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

    public String getNuevaAddress() {
        return nuevaAddress;
    }

    public void setNuevaAddress(String nuevaAddress) {
        this.nuevaAddress = nuevaAddress;
    }

    public Address getDireccion() {
        return direccion;
    }

    public void setDireccion(Address direccion) {
        this.direccion = direccion;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public UserCVO getUsuarioPopup() {
        return usuarioPopup;
    }

    public void setUsuarioPopup(UserCVO usuarioPopup) {
        this.usuarioPopup = usuarioPopup;
    }


    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getFiltrocomunidad() {
        return filtrocomunidad;
    }

    public void setFiltrocomunidad(String filtrocomunidad) {
        this.filtrocomunidad = filtrocomunidad;
    }

    public String getFiltroisla() {
        return filtroisla;
    }

    public void setFiltroisla(String filtroisla) {
        this.filtroisla = filtroisla;
    }

    public String getFiltroprovincia() {
        return filtroprovincia;
    }

    public void setFiltroprovincia(String filtroprovincia) {
        this.filtroprovincia = filtroprovincia;
    }

    public List<Comunidad> getListaComunidades() {
        return listaComunidades;
    }

    public void setListaComunidades(List<Comunidad> listaComunidades) {
        this.listaComunidades = listaComunidades;
    }

    public List<Territorio> getListaIslas() {
        return listaIslas;
    }

    public void setListaIslas(List<Territorio> listaIslas) {
        this.listaIslas = listaIslas;
    }

    public List<Territorio> getListaLocalidades() {
        return listaLocalidades;
    }

    public void setListaLocalidades(List<Territorio> listaLocalidades) {
        this.listaLocalidades = listaLocalidades;
    }

    public List<Provincia> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<Provincia> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<TipoVia> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoVia> tipos) {
        this.tipos = tipos;
    }

    public String getTipovia() {
        return tipovia;
    }

    public void setTipovia(String tipovia) {
        this.tipovia = tipovia;
    }

    public Boolean getAclActive() {
        return aclActive;
    }

    public void setAclActive(Boolean aclActive) {
        this.aclActive = aclActive;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public List<TipoDocumento> getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getIdApp() {
        return idApp;
    }

    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}