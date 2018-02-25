package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.itnoroeste.core.security.authentication.model.group.GroupFacade;
import es.itnoroeste.core.security.authentication.model.group.vo.Group;
import es.itnoroeste.core.security.authentication.model.rol.RolFacade;
import es.itnoroeste.core.security.authentication.model.rol.vo.Rol;
import es.devcircus.acl.authentication.model.user.UserCVOFacade;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;
import es.itnoroeste.social.person.vo.Person;
import es.itnoroeste.social.person.vo.Person.TipoDocumento;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class UsuarioListActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl<UserCVO> usuarios;
    private String filtrorol;
    private List<Rol> listaRol;
    private List<Application> app;
    private List<Action> acciones;
    private List<Module> listaModulos;
    private String filtronombre;
    private String filtrodoi;
    private String filtroactivo;
    private String filtroapellido;
    private String filtrousuario;
    private List<Group> grupos;
    private String filtrotipodocumento;
    private String filtroapp;
    private String filtromod;
    private String filtro;
    private String filtroacc;
    private String filtrogrupo;
    private String requestURI = "UsuarioList";
    private HttpServletRequest request;
    private Logger logger = Logger.getLogger(UsuarioListActionHandler.class.getName());
    private String userName = getUsername();
  private List<Person.TipoDocumento> tiposDocumento = Arrays.asList(Person.TipoDocumento.values());
   
    public UsuarioListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.usuarios = new PaginatedListHibernate<UserCVO>(request);
        this.listaRol = new ArrayList<Rol>();
        this.acciones = new ArrayList<Action>();
        this.listaModulos = new ArrayList<Module>();
        this.app = ApplicationFacade.listApplication(userName);
        this.grupos = GroupFacade.listGroup(userName);
        //Si esta activo algun filtro
        if (!filtrorol.equals("-1") || !filtronombre.equals("") || !filtroapellido.equals("") || !filtrousuario.equals("") || !filtrodoi.equals("") || !filtrotipodocumento.equals("-1")
                || !filtroactivo.equals("-1") || !filtroapp.equals("-1") || !filtromod.equals("-1") || !filtroacc.equals("-1") || !filtrogrupo.equals("-1")) {
            this.usuarios = UserCVOFacade.findUser(userName, filtrorol, filtronombre, filtroapellido, filtrousuario,  filtrotipodocumento, filtrodoi,
                    filtroactivo, filtroapp, filtromod, filtroacc, filtrogrupo, this.usuarios);
        } else {
            this.usuarios = UserCVOFacade.getPaginatedlist(userName, this.usuarios);
        }
        /**
         * ****Comprobamos los combos marcados para cargar las listas
         * necesarias*****
         */
        if (!this.filtroapp.equals("-1")) {
            this.listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, Long.valueOf(filtroapp));
            this.listaRol = RolFacade.obtainRolByApp(userName, Long.valueOf(filtroapp));
            if (!this.filtromod.equals("-1")) {
                this.acciones = ModuleFacade.getAllActionsFromModule(userName, Long.valueOf(filtromod));
            }
        }
        return SUCCESS;
    }
    
    public String list() throws Exception {
        this.filtrorol = "-1";
        this.listaRol = new ArrayList<Rol>();
        this.filtroapp = "-1";
        this.app = ApplicationFacade.listApplication(userName);
        this.filtroacc = "-1";
        this.filtromod = "-1";
        this.filtrogrupo = "-1";
        this.acciones = new ArrayList<Action>();
        this.listaModulos = new ArrayList<Module>();
        this.filtronombre = null;
        this.grupos = GroupFacade.listGroup(userName);
        this.filtroapellido = null;
        this.filtrousuario = null;     
        this.filtrodoi = null;
        this.filtroactivo = "-1";
        this.filtrotipodocumento = "-1";

        PaginatedListHibernate<UserCVO> u = new PaginatedListHibernate<UserCVO>(request);
        this.usuarios = UserCVOFacade.getPaginatedlist(userName, u);
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            UserCVOFacade.deleteUser(userName, Long.valueOf(id));
            addActionMessage(getText("usuarios.usuarios.delete.ok"));
            return SUCCESS;
        } catch (InternalErrorException ex) {
            logger.error("excepcion borrando usuario: " + ex.getMessage());
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("usuarios.usuarios.delete.fail"));
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
        if (UserCVOFacade.activateUser(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.usuarios.message.activar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("usuarios.usuarios.message.activar.error"));
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
        if (UserCVOFacade.deActivateUser(userName, Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito. 
            addActionMessage(getText("usuarios.usuarios.message.desactivar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("usuarios.usuarios.message.desactivar.error"));
            return INPUT;
        }
    }

    public String accionesAjax() throws Exception {
        acciones = ModuleFacade.getAllActionsFromModule(userName, Long.valueOf(filtro));
        return SUCCESS;
    }

    public String accionesCodeAjax() throws Exception {
        acciones = ModuleFacade.getAllActionsFromModuleByCode(userName, filtro);
        return SUCCESS;
    }

    public PaginatedListImpl<UserCVO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(PaginatedListImpl<UserCVO> usuarios) {
        this.usuarios = usuarios;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFiltrotipodocumento() {
        return filtrotipodocumento;
    }

    public void setFiltrotipodocumento(String filtrotipodocumento) {
        this.filtrotipodocumento = filtrotipodocumento;
    }

    public String getFiltroacc() {
        return filtroacc;
    }

    public void setFiltroacc(String filtroacc) {
        this.filtroacc = filtroacc;
    }

    public String getFiltroactivo() {
        return filtroactivo;
    }

    public void setFiltroactivo(String filtroactivo) {
        this.filtroactivo = filtroactivo;
    }

    public String getFiltroapp() {
        return filtroapp;
    }

    public void setFiltroapp(String filtroapp) {
        this.filtroapp = filtroapp;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public String getFiltrogrupo() {
        return filtrogrupo;
    }

    public void setFiltrogrupo(String filtrogrupo) {
        this.filtrogrupo = filtrogrupo;
    }

    public String getFiltromod() {
        return filtromod;
    }

    public void setFiltromod(String filtromod) {
        this.filtromod = filtromod;
    }

    public String getFiltrodoi() {
        return filtrodoi;
    }

    public void setFiltrodoi(String filtrodoi) {
        this.filtrodoi = filtrodoi;
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

    public String getFiltrorol() {
        return filtrorol;
    }

    public void setFiltrorol(String filtrorol) {
        this.filtrorol = filtrorol;
    }

    public List<Application> getApp() {
        return app;
    }

    public void setApp(List<Application> app) {
        this.app = app;
    }

    public List<Action> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Action> acciones) {
        this.acciones = acciones;
    }

    public List<Module> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Module> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getFiltroapellido() {
        return filtroapellido;
    }

    public void setFiltroapellido(String filtroapellido) {
        this.filtroapellido = filtroapellido;
    }

    public String getFiltrousuario() {
        return filtrousuario;
    }

    public void setFiltrousuario(String filtrousuario) {
        this.filtrousuario = filtrousuario;
    }

    public List<Group> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Group> grupos) {
        this.grupos = grupos;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<TipoDocumento> getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }
    
}