package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.ElementAlreadyExistException;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.itnoroeste.core.security.authentication.model.user.vo.User;
import es.devcircus.acl.util.Validation;
import es.itnoroeste.social.person.vo.Person;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class UsuarioCreateFromPersonActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private String username1;
    private String password;
    private String password2;
    private PaginatedListImpl personas;
    private String filtroNIF;
    private String filtroapellidos;
    private String filtronombre;
    private String requestURI = "UsuarioCreateFromPersonStep0";
    private HttpServletRequest request;
      private String userName = getUsername();

    public UsuarioCreateFromPersonActionHandler() {
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        this.personas = new PaginatedListHibernate<User>(request);
        //Si esta activo algun filtro
        if (!filtroNIF.equals("") || !filtronombre.equals("") || !filtroapellidos.equals("")) {
            this.personas = UserCVOFacade.findPeopleNoUserFiltros(userName, filtronombre, filtroapellidos, filtroNIF, personas);
        } else {
            this.personas = UserCVOFacade.findPeopleNoUser(userName, personas);
        }
        return SUCCESS;
    }

    @SkipValidation
    public String list() throws Exception {
        this.filtroNIF = null;
        this.filtronombre = null;
        this.filtroapellidos = null;
        this.personas = new PaginatedListHibernate<Person>(request);
        this.personas = UserCVOFacade.findPeopleNoUser(userName, this.personas);
        return SUCCESS;
    }

    @SkipValidation
    public String goToStep1() throws Exception {
        return SUCCESS;
    }

    public String goToStep2() throws Exception {
        try {
            User user = new User();
            user.setIdPerson(Long.valueOf(this.id));
            user.setUsername(username1);   
           user=UserCVOFacade.createUser(userName , user,Long.valueOf(id).toString(), password);
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

    @Override
    public void validate() {
        //Para que si hay algun error de validacion no de error la lista paginada
        this.personas = new PaginatedListHibernate<Person>(request);
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
        }
        if (!Validation.validarPassword(password)) {
            addFieldError("password", getText("common.field.format.notValid"));
        }

    }

    public String getFiltroNIF() {
        return filtroNIF;
    }

    public void setFiltroNIF(String filtroNIF) {
        this.filtroNIF = filtroNIF;
    }

    public PaginatedListImpl getPersonas() {
        return personas;
    }

    public String getFiltroapellidos() {
        return filtroapellidos;
    }

    public void setFiltroapellidos(String filtroapellidos) {
        this.filtroapellidos = filtroapellidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public String getUserName() {
        return userName;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
