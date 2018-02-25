package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.ElementAlreadyExistException;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.exceptions.UnauthorizedAccessException;
import es.itnoroeste.core.security.authentication.model.rol.RolFacade;
import es.itnoroeste.core.security.authentication.model.rol.vo.Rol;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.itnoroeste.core.security.authentication.model.user.UserFacade;
import es.itnoroeste.core.security.authentication.model.user.vo.User;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;
import es.devcircus.acl.util.Validation;
import es.imaro.pp.common.territorio.comunidad.ComunidadFacade;
import es.imaro.pp.common.territorio.comunidad.vo.Comunidad;
import es.imaro.pp.common.territorio.isla.IslaFacade;
import es.imaro.pp.common.territorio.isla.vo.Isla;
import es.imaro.pp.common.territorio.localidad.LocalidadFacade;
import es.imaro.pp.common.territorio.localidad.vo.Localidad;
import es.imaro.pp.common.territorio.provincia.ProvinciaFacade;
import es.imaro.pp.common.territorio.provincia.vo.Provincia;
import es.imaro.pp.common.territorio.vo.Territorio;
import es.itnoroeste.social.address.AddressFacade;
import es.itnoroeste.social.address.vo.Address;
import es.itnoroeste.social.listfield.vo.ListField;
import es.itnoroeste.social.name.NameFacade;
import es.itnoroeste.social.name.vo.Name;
import es.itnoroeste.social.person.PersonFacade;
import es.itnoroeste.social.person.vo.Person;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import es.itnoroeste.social.address.vo.Address.TipoVia;
import es.itnoroeste.social.person.vo.Person.Gender;
import es.itnoroeste.social.person.vo.Person.TipoDocumento;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class UsuarioCreateFromScratchActionHandler extends CustomActionSupport {

    private String id;
    private Name nombre;
    private String birthday;
    private Address direccion;
    private String username1; 
    private String telephone;
    private String mail;
    private String doi;
    private String password;
    private String password2;
    private List<Rol> rol = new ArrayList<Rol>();
    private List<String> rolActivos = new ArrayList<String>();
    private String requestURI = "UsuarioList";
    private String formulario;
    private List<Comunidad> listaComunidades;
    private List<Provincia> listaProvincias = new ArrayList<Provincia>();
    private List<Territorio> listaIslas = new ArrayList<Territorio>();
    private List<Territorio> listaLocalidades = new ArrayList<Territorio>();
    private String filtrocomunidad;
    private String filtroprovincia;
    private String filtroisla;
    private String userName = getUsername();
//    private List<Address.TipoVia> tipos = Arrays.asList(Address.TipoVia.values());
//    private String tipovia;
    private Person current;
     private List<Person.Gender> generos = Arrays.asList(Person.Gender.values());
    private List<Person.TipoDocumento> tiposDocumento = Arrays.asList(Person.TipoDocumento.values());
   

    public UsuarioCreateFromScratchActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
       current=UserCVOFacade.preparaUsuarioFromScratchParaCrear(userName);
        return SUCCESS;
    }

    public String goToStep1() throws Exception {
        try {
            Date fechaNac = null;
            if (this.birthday != null && !this.birthday.equals("")) {
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                fechaNac = formatoDelTexto.parse(this.birthday);
            }

            //Asociamos el numero de telefono
            List<ListField> phoneNumbers = new ArrayList<ListField>();
            if (!this.telephone.equals("")) {
                ListField phoneNumber = new ListField();
                phoneNumber.setPrimar(Boolean.TRUE);
                phoneNumber.setValue(this.telephone);
                phoneNumber.setType("telefono");
                phoneNumbers.add(phoneNumber);
            }
            current.setPhoneNumbers(phoneNumbers);

            List<ListField> emails = new ArrayList<ListField>();
            //Si hay direccion de email la asociamos
            if (!this.mail.equals("")) {
                ListField email = new ListField();
                email.setPrimar(Boolean.TRUE);
                email.setValue(this.mail);
                email.setType("email");
                emails.add(email);
            }
            current.setEmails(emails);
            current = UserCVOFacade.createUserFromScratch(userName, current, fechaNac, direccion);
            this.id = current.getId().toString();
            return SUCCESS;
        } catch (InternalErrorException ex) {
            if (ex.getCause() instanceof ElementAlreadyExistException) {
                //Añadimos un mensaje que indique al usuario que ha habido errores
                //durante la realizacion de la accion.
                addActionError(getText("usuarios.usuarios.message.create.error.yaexiste"));
            } else {
                addActionError(getText("usuarios.usuarios.message.create.error"));
            }
            return INPUT;
        }

    }

    public String create() throws Exception {
        try {
            User user = new User();
            user.setIdPerson(Long.valueOf(this.id));
            user.setUsername(username1);
            user = UserCVOFacade.createUser(userName, user, Long.valueOf(id).toString(), password);
            this.id = user.getId().toString();
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.usuarios.message.create"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            if (ex.getCause() instanceof ElementAlreadyExistException) {
                //Añadimos un mensaje que indique al usuario que ha habido errores
                //durante la realizacion de la accion.
                addActionError(getText("usuarios.usuarios.message.create.error.yaexiste"));
            } else {
                addActionError(getText("usuarios.usuarios.message.create.error"));
            }
            return INPUT;
        }
    }

    @SkipValidation
    public String provinciasAjax() throws Exception {
        this.listaProvincias = ProvinciaFacade.findProvincia(userName, "", Long.valueOf(this.filtrocomunidad));
        return SUCCESS;
    }

    @SkipValidation
    public String islasAjax() throws Exception {
        List<Isla> listaIsla = IslaFacade.findIsla(userName, "", Long.valueOf(this.filtroprovincia));
        for (Isla i : listaIsla) {
            Territorio t = new Territorio();
            t.setId(i.getId());
            t.setNombre(i.getNombre());
            this.listaIslas.add(t);
        }
        return SUCCESS;
    }

    @SkipValidation
    public String localidadesAjax() throws Exception {
        List<Localidad> listaLocalidads;
        if (!this.filtroisla.equals("-1")) {
            listaLocalidads = LocalidadFacade.findLocalidad(userName, "", Long.valueOf(this.filtroisla), Long.valueOf(this.filtroprovincia));
        } else {
            listaLocalidads = LocalidadFacade.findLocalidad(userName, "", null, Long.valueOf(this.filtroprovincia));
        }
        for (Localidad l : listaLocalidads) {
            Territorio t = new Territorio();
            t.setId(l.getId());
            t.setNombre(l.getNombre());
            this.listaLocalidades.add(t);
        }
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (this.formulario.equals("form1")) {
//            try {
//                //Recarga de combos de territorios
//                this.listaComunidades = ComunidadFacade.listComunidad(userName);
//                if (!this.filtrocomunidad.equals("-1") && !this.filtrocomunidad.equals("0")) {
//                    this.listaProvincias = ProvinciaFacade.findProvincia(userName, "", Long.valueOf(this.filtrocomunidad));
//                    if (!this.filtroprovincia.equals("-1")) {
//                        List<Isla> listaIsla = IslaFacade.findIsla(userName, "", Long.valueOf(this.filtroprovincia));
//                        for (Isla is : listaIsla) {
//                            Territorio t = new Territorio();
//                            t.setId(is.getId());
//                            t.setNombre(is.getNombre());
//                            this.listaIslas.add(t);
//                        }
//                        List<Localidad> listaLocalidads;
//                        if (this.filtroisla != null && !this.filtroisla.equals("-1")) {
//                            listaLocalidads = LocalidadFacade.findLocalidad(userName, "", Long.valueOf(this.filtroisla), Long.valueOf(this.filtroprovincia));
//                        } else {
//                            listaLocalidads = LocalidadFacade.findLocalidad(userName, "", null, Long.valueOf(this.filtroprovincia));
//                        }
//                        for (Localidad loc : listaLocalidads) {
//                            Territorio t = new Territorio();
//                            t.setId(loc.getId());
//                            t.setNombre(loc.getNombre());
//                            this.listaLocalidades.add(t);
//                        }
//
//                    }
//                }
//
//            } catch (UnauthorizedAccessException ex) {
//                Logger.getLogger(UsuarioCreateFromScratchActionHandler.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (InternalErrorException ex) {
//                Logger.getLogger(UsuarioCreateFromScratchActionHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
            //Verificamos que el nombre no sea vacío
            if (!Validation.validarVacio(current.getName().getGivenName())) {
                addFieldError("current.name.givenName", getText("common.field.required"));
            } else {//Verificamos que no supere la longitud permitida para el campo
                if (current.getName().getGivenName().length() > 50 || !Validation.validarLetras(current.getName().getGivenName())) {
                    addFieldError("current.name.givenName", getText("common.field.format.notValid"));
                }
            }
            //Verificamos que el nombre no sea vacío
            if (!Validation.validarVacio(current.getName().getFamilyName())) {
                addFieldError("current.name.familyName", getText("common.field.required"));
            } else {//Verificamos que no supere la longitud permitida para el campo
                if (current.getName().getFamilyName().length() > 50 || !Validation.validarLetras(current.getName().getFamilyName())) {
                    addFieldError("current.name.familyName", getText("common.field.format.notValid"));
                }
            }

////verificamos que se haya seleccionado alguno de los campos para crear la direccion 
//            //para hacer las comprobaciones
//            if (Validation.validarVacio(direccion.getStreetAddress())
//                    || Validation.validarVacio(direccion.getPostalCode())
//                    || Validation.validarVacio(direccion.getCountry())
//                    || (direccion.getLocality() != null && !direccion.getLocality().equals("-1"))
//                    || !this.tipovia.equals("-1")) {
//                //Verificamos si se ha seleccionado una localidad
//                if (direccion.getLocality() != null && !direccion.getLocality().equals("-1")) {
//                    List<String> valores = new ArrayList<String>();
//                    List<Localidad> localidads;
//                    try {
//                        if (this.filtroisla != null && !this.filtroisla.equals("-1")) {
//                            localidads = LocalidadFacade.findLocalidad(userName, "", Long.valueOf(this.filtroisla), Long.valueOf(this.filtroprovincia));
//                        } else {
//                            localidads = LocalidadFacade.findLocalidad(userName, "", null, Long.valueOf(this.filtroprovincia));
//                        }
//                        for (Localidad l : localidads) {
//                            valores.add(l.getId().toString());
//                        }
//                        if (!Validation.validarSelect(direccion.getLocality(), valores)) {
//                            addFieldError("direccion.locality", getText("common.field.format.notValid"));
//                        }
//                    } catch (UnauthorizedAccessException ex) {
//                        Logger.getLogger(UsuarioCreateFromScratchActionHandler.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (InternalErrorException ex) {
//                        Logger.getLogger(UsuarioCreateFromScratchActionHandler.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                //Verificamos que el tipo sea uno de los mostrados
//                List<String> tiposvia = new ArrayList<String>();
//                for (Address.TipoVia t : tipos) {
//                    tiposvia.add(t.name().toLowerCase());
//                }
//                if (!this.tipovia.equals("-1") && !Validation.validarSelect(this.tipovia.toLowerCase(), tiposvia)) {
//                    addFieldError("direccion.type", getText("common.field.format.notValid"));
//                }
//                //Verificamos que la calle no supere la longitud permitida
//                if (direccion.getStreetAddress().length() > 50) {
//                    addFieldError("direccion.streetAddress", getText("common.field.format.notValid"));
//                }
//                //Verificamos que el codigo postal no supere la longitud
//                if (Validation.validarVacio(direccion.getPostalCode())
//                        && (direccion.getPostalCode().length() > 5
//                        || !Validation.validarNumeros(direccion.getPostalCode()))) {
//                    addFieldError("direccion.postalCode", getText("common.field.format.notValid"));
//                }
//                //Verificamos que el pais no sea vacio
//                if (Validation.validarVacio(direccion.getCountry())
//                        && (direccion.getCountry().length() > 50
//                        || !Validation.validarLetras(this.direccion.getCountry()))) {
//                    addFieldError("direccion.country", getText("common.field.format.notValid"));
//                }
//            }
//            //Verificamos que no supere la longitud permitida para el campo
//            if (!direccion.getStreetAddress().equals("") && direccion.getStreetAddress().length() > 50) {
//                addFieldError("direccion.streetAddress", getText("common.field.format.notValid"));
//            }


//            //Verificamos que el sexo sea una de las opciones
//            List<String> valores = new ArrayList<String>();
//            valores.add("1");
//            valores.add("2");
//
//            if (!Validation.validarSelect(this.gender, valores)) {
//                addFieldError("current.sexo", getText("common.field.select.required"));
//            }
//            //Verificamos que el tipo de documento sea una de las opciones
//            valores.add("3");
            //Verificamos que no supere la longitud permitida para el campo
            if (telephone.length() > 13) {
                addFieldError("telephone", getText("common.field.format.notValid"));
            } else {
                if (!this.telephone.equals("") && !Validation.validarNumeros(telephone)) {
                    addFieldError("mail", getText("common.field.format.notValid"));
                }
            }

            if (this.mail.length() > 50) {
                addFieldError("mail", getText("common.field.format.notValid"));
            }
            if (!this.mail.equals("") && !Validation.validarEmail(mail)) {
                addFieldError("mail", getText("common.field.format.notValid"));
            }

            //Verificamos que si la fecha de nacimiento no es vacia que sea valida
            if (this.birthday != null && !this.birthday.equals("")) {
                if (!Validation.validarFechaString(this.birthday)) {
                    addFieldError("birthday", getText("common.field.data.format.notValid"));
                }
            }

//            if (!Validation.validarSelect(this.tipoDocumento, valores)) {
//                addFieldError("current.tipoDocumento", getText("common.field.select.required"));
//            }
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
        } else {
            //Verificamos que el nombre no sea vacío
            if (!Validation.validarVacio(username1)) {
                addFieldError("username1", getText("common.field.required"));
            } else {//Verificamos que no supere la longitud permitida para el campo
                if (username1.length() > 30) {
                    addFieldError("username1", getText("common.field.format.notValid"));
                }
            }

            //validamos que sean iguales
            if (!Validation.validarIguales(password, password2)) {
                addFieldError("password2", getText("common.field.password.notEquals"));
            } else if (password.length() > 50) {
                addFieldError("password", getText("common.field.format.notValid"));
            }
            if (!Validation.validarPassword(password)) {
                addFieldError("password", getText("common.field.format.notValid"));
            }
        }
    }

    public Address getDireccion() {
        return direccion;
    }

    public void setDireccion(Address direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getNombre() {
        return nombre;
    }

    public void setNombre(Name nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public Person getCurrent() {
        return current;
    }

    public void setCurrent(Person current) {
        this.current = current;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Rol> getRol() {
        return rol;
    }

    public void setRol(List<Rol> rol) {
        this.rol = rol;
    }

    public List<String> getRolActivos() {
        return rolActivos;
    }

    public void setRolActivos(List<String> rolActivos) {
        this.rolActivos = rolActivos;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

//    public String getTipoDocumento() {
//        return tipoDocumento;
//    }
//
//    public void setTipoDocumento(String tipoDocumento) {
//        this.tipoDocumento = tipoDocumento;
//    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
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
