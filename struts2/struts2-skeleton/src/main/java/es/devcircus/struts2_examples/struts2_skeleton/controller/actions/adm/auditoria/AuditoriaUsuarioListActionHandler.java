package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.auditory.model.userauditory.UserAuditoryCVOServiceFacade;
import es.itnoroeste.core.security.auditory.model.userauditory.UserAuditoryServiceFacade;
import es.devcircus.acl.auditory.model.userauditory.vo.UserAuditoryCVO;
import es.itnoroeste.social.person.vo.Person;
import es.itnoroeste.social.person.vo.Person.TipoDocumento;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AuditoriaUsuarioListActionHandler extends CustomActionSupport implements ServletRequestAware {
    private String id;
    private PaginatedListImpl auditorias;
    private String filtrodesde;
    private String filtrohasta;
    private String filtroapp;
    private String filtromod;
    private String filtroaction;
    private String filtronombre;
    private String filtroapellido;
    private String filtrousuario;
    private String filtrotipodocumento;
    private String filtrodoi;
    private String filtrolevel;
    private String filtrotype;
    private List<Application> app;
    private List<Action> acciones;
    private List<Module> listaModulos;
    private String requestURI = "AuditoriaUsuarioList";
    private HttpServletRequest request;
    private String userName = getUsername();
private List<Person.TipoDocumento> tiposDocumento = Arrays.asList(Person.TipoDocumento.values());
   
    public AuditoriaUsuarioListActionHandler() {
    }
    @Override
    public String execute() throws Exception {
        this.auditorias = new PaginatedListHibernate<UserAuditoryCVO>(request);
        this.app = ApplicationFacade.listApplication(userName);
        this.listaModulos = new ArrayList<Module>();
        this.acciones = new ArrayList<Action>();

        //Si esta activo algun filtro
        if (!filtrodesde.equals("") || !filtrohasta.equals("") || !filtroapp.equals("-1") || !filtromod.equals("-1")
                || !filtroaction.equals("-1") || !filtronombre.equals("") || !filtroapellido.equals("") || !filtrousuario.equals("") || !filtrotipodocumento.equals("-1")
                || !filtrodoi.equals("") || !filtrolevel.equals("-1") || !filtrotype.equals("-1")) {
            this.auditorias = UserAuditoryCVOServiceFacade.findUserAuditoryCVO(userName,filtrodesde, filtrohasta, filtroapp, filtromod,
                    filtroaction, filtrolevel, filtronombre, filtroapellido, filtrousuario, filtrotipodocumento, filtrodoi, filtrotype,
                    this.auditorias);
        } else {
            this.auditorias = UserAuditoryCVOServiceFacade.getPaginatedlist(userName,this.auditorias);
        }
        
        if (!filtroapp.equals("-1") && filtroapp != null) {
            Application ap = ApplicationFacade.obtainApplicationByCode(userName, filtroapp);
            listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, ap.getId());
            for (Module m : listaModulos) {
                m.setActions(ModuleFacade.getAllActionsFromModule(userName, m.getId()));
            }
        }
        
        if (!filtromod.equals("-1") && filtromod != null) {
            Module mo = ModuleFacade.obtainModuleByCode(userName, filtromod);
            acciones = ModuleFacade.getAllActionsFromModule(userName, mo.getId());
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.filtrodesde = null;
        this.filtrohasta = null;
        this.filtrotype = "-1";
        this.filtroapp = "-1";
        this.app = ApplicationFacade.listApplication(userName);
        this.filtromod = "-1";
        this.acciones = new ArrayList<Action>();
        this.listaModulos = new ArrayList<Module>();
        this.filtroaction = "-1";
        this.filtrolevel = "-1";
        this.filtronombre = null;
        this.filtroapellido = null;
        this.filtrousuario = null;
        this.filtrotipodocumento = "-1";
        this.filtrodoi = null;
        PaginatedListImpl<UserAuditoryCVO> aud = new PaginatedListHibernate<UserAuditoryCVO>(request);
        this.auditorias = UserAuditoryCVOServiceFacade.getPaginatedlist(aud);
        return SUCCESS;
    }

    public String delete() throws Exception {
        UserAuditoryServiceFacade.deleteAuditory(Long.valueOf(id));
        return SUCCESS;
    }

    public String getUserName() {
        return userName;
    }


    public PaginatedListImpl getAuditorias() {
        return auditorias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFiltroaction() {
        return filtroaction;
    }

    public void setFiltroaction(String filtroaction) {
        this.filtroaction = filtroaction;
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

    public List<Action> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Action> acciones) {
        this.acciones = acciones;
    }

    public List<Application> getApp() {
        return app;
    }

    public void setApp(List<Application> app) {
        this.app = app;
    }

    public List<Module> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Module> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public String getFiltroapp() {
        return filtroapp;
    }

    public void setFiltroapp(String filtroapp) {
        this.filtroapp = filtroapp;
    }

    public String getFiltrodesde() {
        return filtrodesde;
    }

    public void setFiltrodesde(String filtrodesde) {
        
        //modificar
        this.filtrodesde = filtrodesde;
    }

    public String getFiltrohasta() {
        return filtrohasta;
    }

    public void setFiltrohasta(String filtrohasta) {
        this.filtrohasta = filtrohasta;
    }

    public String getFiltromod() {
        return filtromod;
    }

    public void setFiltromod(String filtromod) {
        this.filtromod = filtromod;
    }

    public String getFiltrotype() {
        return filtrotype;
    }

    public void setFiltrotype(String filtrotype) {
        this.filtrotype = filtrotype;
    }

    public String getFiltrodoi() {
        return filtrodoi;
    }

    public void setFiltrodoi(String filtrodoi) {
        this.filtrodoi = filtrodoi;
    }

    public String getFiltrolevel() {
        return filtrolevel;
    }

    public void setFiltrolevel(String filtrolevel) {
        this.filtrolevel = filtrolevel;
    }

    public String getFiltronombre() {
        return filtronombre;
    }

    public void setFiltronombre(String filtronombre) {
        this.filtronombre = filtronombre;
    }

    public String getFiltrotipodocumento() {
        return filtrotipodocumento;
    }

    public void setFiltrotipodocumento(String filtrotipodocumento) {
        this.filtrotipodocumento = filtrotipodocumento;
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

    public List<TipoDocumento> getTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }
    
}
