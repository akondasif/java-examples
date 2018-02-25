/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.acl.controller.actions.prv.profile;

import es.imaro.pp.common.territorio.comunidad.ComunidadFacade;
import es.imaro.pp.common.territorio.comunidad.vo.Comunidad;
import es.imaro.pp.common.territorio.distrito.DistritoFacade;
import es.imaro.pp.common.territorio.elm.ELMFacade;
import es.imaro.pp.common.territorio.isla.IslaFacade;
import es.imaro.pp.common.territorio.isla.vo.Isla;
import es.imaro.pp.common.territorio.localidad.LocalidadFacade;
import es.imaro.pp.common.territorio.localidad.vo.Localidad;
import es.imaro.pp.common.territorio.pais.PaisFacade;
import es.imaro.pp.common.territorio.provincia.ProvinciaFacade;
import es.imaro.pp.common.territorio.provincia.vo.Provincia;
import es.imaro.pp.common.territorio.vo.Territorio;
import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.auditory.model.userauditory.vo.UserAuditory;
import es.devcircus.acl.auditory.model.userauditory.vo.UserAuditoryCVO;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;
import es.devcircus.acl.util.Validation;
import es.itnoroeste.empleados.departamento.DepartamentoFacade;
import es.itnoroeste.empleados.departamento.vo.Departamento;
import es.itnoroeste.empleados.departamento.vo.DepartamentoCVO;
import es.itnoroeste.empleados.empleado.vo.Empleado;
import es.itnoroeste.empleados.empleado.vo.EmpleadoCVO;
import es.itnoroeste.empleados.sede.SedeCVOFacade;
import es.itnoroeste.empleados.sede.SedeFacade;
import es.itnoroeste.empleados.sede.vo.Sede;
import es.itnoroeste.empleados.sede.vo.SedeCVOVista;
import es.itnoroeste.empleados.sedebase.vo.SedeBase;
import es.itnoroeste.empleados.sedeexterior.SedeExteriorCVOFacade;
import es.itnoroeste.empleados.sedeexterior.vo.SedeExterior;
import es.itnoroeste.empleados.sedeexterior.vo.SedeExteriorCVO;
import es.itnoroeste.social.address.AddressFacade;
import es.itnoroeste.social.address.vo.Address;
import es.itnoroeste.social.address.vo.Address.TipoVia;
import es.itnoroeste.social.listfield.vo.ListField;
import es.itnoroeste.social.person.vo.Person;
import es.itnoroeste.social.person.vo.Person.Gender;
import es.itnoroeste.social.person.vo.Person.TipoDocumento;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author adrian
 */
public class AboutActionHandler extends CustomActionSupport implements ServletRequestAware {
    private PaginatedListImpl departamentos;
    private PaginatedListImpl noempleados;
    private String id;
    private String idPerson;
    private UserCVO current;
    private Empleado empleado;
    private String formulario;
    private String direccionPrincipal;
    private String telefonoPrincipal;
    private String emailPrincipal;
    private String nuevoTelefono;
    private String nuevoEmail;
    private String nuevaAddress;
    private Address direccion;
    private SedeBase sedePopup;
    private Departamento departamentoPopup;
    private String territorio;
    private String dirSedePopup;
    private String ambito;
    private File fileUpload;
    private String fileUploadContentType = "";
    private String fileUploadFileName;
    private String userName = getUsername();
    private List<Comunidad> listaComunidades;
    private List<Provincia> listaProvincias = new ArrayList<Provincia>();
    private List<Territorio> listaIslas = new ArrayList<Territorio>();
    private List<Territorio> listaLocalidades = new ArrayList<Territorio>();
    private String filtrocomunidad;
    private String filtroprovincia;
    private String filtroisla;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AboutActionHandler.class.getName());
    private List<Address.TipoVia> tipos = Arrays.asList(Address.TipoVia.values());
    private String tipovia;
    private HttpServletRequest request;
    private PaginatedListImpl incidencias;
    private String tab;
    private List<Person.Gender> generos = Arrays.asList(Person.Gender.values());
    private List<Person.TipoDocumento> tiposDocumento = Arrays.asList(Person.TipoDocumento.values());

    public AboutActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.incidencias = new PaginatedListHibernate<UserAuditory>(request);
        this.current = UserCVOFacade.obtainUserCVOByUsername(userName, userName, incidencias);
        this.id = this.current.getId().toString();
        this.listaComunidades = ComunidadFacade.listComunidad(userName);
        return SUCCESS;

    }

    public String edit() throws Exception {
        try {
            current.setUsername(userName);
            this.current = UserCVOFacade.updateUserCVOFromProfile(userName, current);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.profile.message.edit"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.profile.message.edit.error"));
            return INPUT;
        }
    }

    @SkipValidation
    public String checkTelefono() throws Exception {
        try {
            UserCVOFacade.checkTelefonoFromProfile(userName, Long.valueOf(id), this.nuevoTelefono);
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
            UserCVOFacade.checkEmailFromProfile(userName, Long.valueOf(id), this.nuevoEmail);
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
            UserCVOFacade.checkAddressFromProfile(userName, Long.valueOf(id), this.direccionPrincipal);
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
            UserCVOFacade.delAddressFromProfile(userName, Long.valueOf(id), this.direccion, this.direccionPrincipal);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.address.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.address.message.del.error"));
            return INPUT;
        }
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    public String addAddressProfile() throws Exception {
        try {
            if (!tipovia.equals("-1")) {
                this.direccion.setType(tipovia);
            }
            UserCVOFacade.addAddressFromProfile(userName, Long.valueOf(id), this.direccion);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.address.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.address.message.add.error"));
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
            UserCVOFacade.delTelefonoFromProfile(userName, Long.valueOf(id), this.nuevoTelefono);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.telefono.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.telefono.message.del.error"));
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
            UserCVOFacade.delEmailFromProfile(userName, Long.valueOf(id), this.nuevoEmail);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.mail.message.del"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.mail.message.del.error"));
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
            UserCVOFacade.addTelefonoFromProfile(userName, Long.valueOf(id), this.nuevoTelefono);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.telefono.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.telefono.message.add.error"));
            return INPUT;
        }
    }

    public String addEmail() throws Exception {
        try {
            UserCVOFacade.addEmailFromProfile(userName, Long.valueOf(id), this.nuevoEmail);
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("profile.mail.message.add"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            addActionError(getText("profile.mail.message.add.error"));
            return INPUT;
        }
    }
  @SkipValidation
    public String recuperarSedeAjax() throws Exception {
        this.noempleados = new PaginatedListHibernate<EmpleadoCVO>(request);
        this.departamentos = new PaginatedListHibernate<DepartamentoCVO>(request);
        SedeCVOVista sede = SedeCVOFacade.obtainSedeVistaFromView(userName, Long.valueOf(id), noempleados, departamentos, "popup");

        //Guardo los datos que necesito en la vista
        sedePopup = new Sede();
     
         sedePopup.setNombre(sede.getSede().getNombre());
         sedePopup.setHorario(sede.getSede().getHorario());
         sedePopup.setMail(sede.getSede().getMail());
         sedePopup.setTelefono(sede.getSede().getTelefono());
         sedePopup.setExtension(sede.getSede().getExtension());
         sedePopup.setFax(sede.getSede().getFax());
      
        sedePopup.setWeb(sede.getWeb());
        this.dirSedePopup = sede.getSede().getAddress().getFormatted();
        this.dirSedePopup = this.dirSedePopup.replace("<br>", "\n");
        switch (sede.getSede().getAmbito()) {
            case 'M':             
                this.ambito = getText("ambitos.elm");
                this.territorio = ELMFacade.obtainELM(userName, Long.valueOf(sede.getSede().getTerritorio())).getNombre();
                break;
            case 'D':
                this.ambito = getText("ambitos.distrito");
                this.territorio = DistritoFacade.obtainDistrito(userName, Long.valueOf(sede.getSede().getTerritorio())).getNombre();
                break;
            case 'L':
                this.ambito = getText("ambitos.local");
                this.territorio = LocalidadFacade.obtainLocalidad(userName, Long.valueOf(sede.getSede().getTerritorio())).getNombre();
                break;
            case 'I':
                this.ambito = getText("ambitos.isla");
                this.territorio = IslaFacade.obtainIsla(userName, Long.valueOf(sede.getSede().getTerritorio())).getNombre();
                break;
            case 'P':
               this.ambito = getText("ambitos.provincial");
                this.territorio = ProvinciaFacade.obtainProvincia(userName, Long.valueOf(sede.getSede().getTerritorio())).getNombre();
                break;
            case 'A':
                this.ambito = getText("ambitos.autonomico");
                this.territorio = ComunidadFacade.obtainComunidad(userName, Long.valueOf(sede.getSede().getTerritorio())).getNombre();
                break;
            case 'N':
                 this.ambito = getText("ambitos.nacional");
                this.territorio = PaisFacade.obtainPais(userName, Long.valueOf(sede.getSede().getTerritorio())).getNombre();
                break;
        }
      
        this.noempleados = null;
        this.departamentos = null;
        return SUCCESS;
    }

    @SkipValidation
    public String recuperarSedeExtAjax() throws Exception {
        this.noempleados = new PaginatedListHibernate<EmpleadoCVO>(request);
        this.departamentos = new PaginatedListHibernate<DepartamentoCVO>(request);
     
           SedeExteriorCVO sede = SedeExteriorCVOFacade.obtainSedeVistaFromView(userName, Long.valueOf(id), noempleados, departamentos, "popup");

        //Guardo los datos que necesito en la vista
        sedePopup = new SedeExterior();
     
         sedePopup.setNombre(sede.getSede().getNombre());
         sedePopup.setHorario(sede.getSede().getHorario());
         sedePopup.setMail(sede.getSede().getMail());
         sedePopup.setTelefono(sede.getSede().getTelefono());
         sedePopup.setExtension(sede.getSede().getExtension());
         sedePopup.setFax(sede.getSede().getFax());
      
        sedePopup.setWeb(sede.getWeb());
        this.dirSedePopup = sede.getSede().getAddress().getFormatted();
        this.dirSedePopup = this.dirSedePopup.replace("<br>", "\n");
        
        this.noempleados = null;
        this.departamentos = null;
        return SUCCESS;
    }

    @SkipValidation
    public String recuperarDepartamentoAjax() throws Exception {
        //  departamentoPopup = DepartamentoFacade.obtainDepartamento(userName, Long.valueOf(this.id));
        //Recuperamos los datos del departamento
        Departamento d1 = DepartamentoFacade.obtainDepartamento(userName, Long.valueOf(this.id));
        //Guardamos solo aquellos datos que se van a mostrar en el popup
        this.departamentoPopup = new Departamento();
        this.departamentoPopup.setNombre(d1.getNombre());
        this.departamentoPopup.setHorario(d1.getHorario());
        this.departamentoPopup.setWeb(d1.getWeb());
        this.departamentoPopup.setMail(d1.getMail());
        this.departamentoPopup.setTelefono(d1.getTelefono());
        this.departamentoPopup.setExtension(d1.getExtension());
        this.departamentoPopup.setFax(d1.getFax());
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (this.formulario.equals("formAddress")) {
            try {
                //Recarga de combos de territorios
                this.listaComunidades = ComunidadFacade.listComunidad(userName);
                if (!this.filtrocomunidad.equals("0")) {
                    this.listaProvincias = ProvinciaFacade.findProvincia(userName, "", Long.valueOf(this.filtrocomunidad));
                    if (!this.filtroprovincia.equals("-1")) {
                        List<Isla> listaIsla = IslaFacade.findIsla(userName, "", Long.valueOf(this.filtroprovincia));
                        for (Isla is : listaIsla) {
                            Territorio t = new Territorio();
                            t.setId(is.getId());
                            t.setNombre(is.getNombre());
                            this.listaIslas.add(t);
                        }
                        List<Localidad> listaLocalidads;
                        if (this.filtroisla != null && !this.filtroisla.equals("-1")) {
                            listaLocalidads = LocalidadFacade.findLocalidad(userName, "", Long.valueOf(this.filtroisla), Long.valueOf(this.filtroprovincia));
                        } else {
                            listaLocalidads = LocalidadFacade.findLocalidad(userName, "", null, Long.valueOf(this.filtroprovincia));
                        }
                        for (Localidad loc : listaLocalidads) {
                            Territorio t = new Territorio();
                            t.setId(loc.getId());
                            t.setNombre(loc.getNombre());
                            this.listaLocalidades.add(t);
                        }
                    }
                }
            } catch (UnauthorizedAccessException ex) {
                Logger.getLogger(AboutActionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InternalErrorException ex) {
                Logger.getLogger(AboutActionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //verificamos que se haya seleccionado alguno de los campos para crear la direccion
            if (Validation.validarVacio(direccion.getStreetAddress())
                    || Validation.validarVacio(direccion.getPostalCode())
                    || Validation.validarVacio(direccion.getCountry())
                    || (direccion.getLocality() != null && !direccion.getLocality().equals("-1"))
                    || !this.tipovia.equals("-1")) {
                //Verificamos si se ha seleccionado una localidad
                if (direccion.getLocality() != null && !direccion.getLocality().equals("-1")) {
                    List<String> valores = new ArrayList<String>();
                    List<Localidad> localidads;
                    try {
                        if (this.filtroisla != null && !this.filtroisla.equals("-1")) {
                            localidads = LocalidadFacade.findLocalidad(userName, "", Long.valueOf(this.filtroisla), Long.valueOf(this.filtroprovincia));
                        } else {
                            localidads = LocalidadFacade.findLocalidad(userName, "", null, Long.valueOf(this.filtroprovincia));
                        }
                        for (Localidad l : localidads) {
                            valores.add(l.getId().toString());
                        }
                        if (!Validation.validarSelect(direccion.getLocality(), valores)) {
                            addFieldError("direccion.locality", getText("common.field.format.notValid"));
                        }
                    } catch (UnauthorizedAccessException ex) {
                        Logger.getLogger(AboutActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InternalErrorException ex) {
                        Logger.getLogger(AboutActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Verificamos que el tipo sea uno de los mostrados
                List<String> tiposvia = new ArrayList<String>();
                for (Address.TipoVia t : tipos) {
                    tiposvia.add(t.name().toLowerCase());
                }
                if (!this.tipovia.equals("-1") && !Validation.validarSelect(this.tipovia.toLowerCase(), tiposvia)) {
                    addFieldError("direccion.type", getText("common.field.format.notValid"));
                }

                //Verificamos que la calle no supere la longitud permitida
                if (direccion.getStreetAddress().length() > 50) {
                    addFieldError("direccion.streetAddress", getText("common.field.format.notValid"));
                }
                //Verificamos que el codigo postal no supere la longitud
                if (Validation.validarVacio(direccion.getPostalCode())
                        && (direccion.getPostalCode().length() > 5
                        || !Validation.validarNumeros(direccion.getPostalCode()))) {
                    addFieldError("direccion.postalCode", getText("common.field.format.notValid"));
                }
                //Verificamos que el pais no sea vacio
                if (Validation.validarVacio(direccion.getCountry())
                        && (direccion.getCountry().length() > 50
                        || !Validation.validarLetras(this.direccion.getCountry()))) {
                    addFieldError("direccion.country", getText("common.field.format.notValid"));
                }
            } else {//no se ha cubierto ningun campo
                addActionError(getText("usuarios.usuarios.message.adddireccion.error.required"));
            }
        } else {
            try {
                if (this.formulario.equals("formUpdate")) {
                    this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
                    UserCVO user = UserCVOFacade.obtainUserCVOFromProfile(userName, current.getId(), incidencias);
                    this.current.setAddresses(user.getAddresses());
                    this.current.setPhoneNumbers(user.getPhoneNumbers());
                    this.current.setEmails(user.getEmails());
                    this.current.setRoles(current.getRoles());
                    this.current.setAcls(current.getAcls());
                    this.current.setAccionesActivas(current.getAccionesActivas());
                    this.current.setApp(user.getApp());
                    this.current.setListaroles(user.getListaroles());
                    this.current.setListanoroles(user.getListanoroles());
                    this.current.setModulos(user.getModulos());
                    this.current.setIdPerson(user.getIdPerson());
                    //si no se ha cambiado la foto recuperamos la que estaba guardada
                    if (this.current.getFileUpload() == null) {
                        this.current.setFileUpload(user.getFileUpload());
                        this.current.setFileUploadContentType(user.getFileUploadContentType());
                        this.current.setFileUploadFileName(user.getFileUploadFileName());
                    }
                } else {
                    this.incidencias = new PaginatedListHibernate<UserAuditoryCVO>(request);
                    this.current = UserCVOFacade.obtainUserCVOFromProfile(userName, Long.valueOf(id), incidencias);
                    this.current.setRoles(current.getRoles());
                    this.current.setAcls(current.getAcls());
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
                } else {
                    if (this.formulario.equals("formEmail")) {
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
                        //Validamos que la foto no sea mas ancha que alta
                        if (current.getFileUpload() != null && !es.itnoroeste.empleados.util.Validation.validaImagenVertical(current.getFileUpload())) {
                            addFieldError("fileUpload", getText("common.field.imagen.tamanho"));
                        }
                        //Verificamos que el nombre no sea vacío
                        if (!Validation.validarVacio(current.getName().getGivenName())) {
                            addFieldError("current.name.givenName", getText("common.field.required"));
                        } else {//Verificamos que no supere la longitud permitida para el campo
                            if (current.getName().getGivenName().length() > 50) {
                                addFieldError("current.name.givenName", getText("common.field.format.notValid"));
                            }
                        }
                        //Verificamos que el apellido no sea vacío
                        if (!Validation.validarVacio(current.getName().getFamilyName())) {
                            addFieldError("current.name.familyName", getText("common.field.required"));
                        } else {//Verificamos que no supere la longitud permitida para el campo
                            if (current.getName().getFamilyName().length() > 50) {
                                addFieldError("current.name.familyName", getText("common.field.format.notValid"));
                            }
                        }
                        //Verificamos que la fecha de nacimiento no sea vacío y tenga un formato válido
                        if (current.getBirthday() != null) {
                            if (!es.devcircus.acl.util.Validation.validarFechaString(current.getBirthday())) {
                                addFieldError("current.birthday", getText("common.field.data.format.notValid"));
                            }
                        }
                        //Verificamos que el doi no sea vacío
                        if (!Validation.validarVacio(current.getDoi())) {
                            addFieldError("current.doi", getText("common.field.required"));
                        } else {//Verificamos que no supere la longitud permitida para el campo
                            if (current.getDoi().length() > 13) {
                                addFieldError("current.doi", getText("common.field.format.notValid"));
                            } else {//si no es pasaporte hacemos la validacion
                                if (!current.getTipoDocumento().equals(Person.TipoDocumento.PASAPORTE)) {
                                    if (!Validation.validaNifNie(current.getDoi())) {
                                        addFieldError("current.doi", getText("common.field.format.notValid"));
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (UnauthorizedAccessException ex) {
                Logger.getLogger(AboutActionHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InternalErrorException ex) {
                Logger.getLogger(AboutActionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public UserCVO getCurrent() {
        return current;
    }

    public void setCurrent(UserCVO current) {
        this.current = current;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Address getDireccion() {
        return direccion;
    }

    public void setDireccion(Address direccion) {
        this.direccion = direccion;
    }

    public String getDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(String direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefonoPrincipal() {
        return telefonoPrincipal;
    }

    public void setTelefonoPrincipal(String telefonoPrincipal) {
        this.telefonoPrincipal = telefonoPrincipal;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
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

    public String getNuevaAddress() {
        return nuevaAddress;
    }

    public void setNuevaAddress(String nuevaAddress) {
        this.nuevaAddress = nuevaAddress;
    }

    public Departamento getDepartamentoPopup() {
        return departamentoPopup;
    }

    public void setDepartamentoPopup(Departamento departamentoPopup) {
        this.departamentoPopup = departamentoPopup;
    }

    public SedeBase getSedePopup() {
        return sedePopup;
    }

    public void setSedePopup(SedeBase sedePopup) {
        this.sedePopup = sedePopup;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getDirSedePopup() {
        return dirSedePopup;
    }

    public void setDirSedePopup(String dirSedePopup) {
        this.dirSedePopup = dirSedePopup;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getUserName() {
        return userName;
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

    public PaginatedListImpl getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(PaginatedListImpl incidencias) {
        this.incidencias = incidencias;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public List<Gender> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Gender> generos) {
        this.generos = generos;
    }

    public List<TipoDocumento> getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }
}
