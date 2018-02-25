package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.action.ActionFacade;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.auditory.model.auditory.AuditoryCVOServiceFacade;
import es.itnoroeste.core.security.auditory.model.auditory.AuditoryServiceFacade;
import es.devcircus.acl.auditory.model.auditory.vo.AuditoryCVO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AuditoriaListActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl auditorias;
    private String filtrodesde;
    private String filtrohasta;
    private String filtroapp;
    private String filtromod;
    private String filtroaction;
    private String filtrolevel;
    private String filtrotype;
    private String requestURI = "AuditoriaList";
    private List<Application> app;
    private List<Action> acciones;
    private List<Module> listaModulos;
    private HttpServletRequest request;
    private String userName = getUsername();

    public AuditoriaListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.app = ApplicationFacade.listApplication(userName);
        this.acciones = ActionFacade.listAction(userName);
        this.listaModulos = new ArrayList<Module>();
        this.auditorias = new PaginatedListHibernate<AuditoryCVO>(request);

        //Si esta activo algun filtro

        if (!filtrodesde.equals("") || !filtrohasta.equals("") || !filtroapp.equals("-1") || !filtromod.equals("-1")
                || !filtroaction.equals("-1") || !filtrolevel.equals("-1") || !filtrotype.equals("-1")) {
            this.auditorias = AuditoryCVOServiceFacade.findAuditoryCVO(filtrodesde, filtrohasta, filtroapp, filtromod,
                    filtroaction, filtrolevel, filtrotype, this.auditorias);
        } else {
            this.auditorias = AuditoryCVOServiceFacade.getPaginatedlist(this.auditorias);
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
        this.filtroapp = "-1";
        this.app = ApplicationFacade.listApplication(userName);
        this.filtromod = "-1";
        this.acciones = new ArrayList<Action>();
        this.listaModulos = new ArrayList<Module>();
        this.filtroaction = "-1";
        this.filtrolevel = "-1";
        this.filtrotype = "-1";
        PaginatedListImpl<AuditoryCVO> aud = new PaginatedListHibernate<AuditoryCVO>(request);
        this.auditorias = AuditoryCVOServiceFacade.getPaginatedlist(aud);
        return SUCCESS;
    }

    public String delete() throws Exception {
        AuditoryServiceFacade.deleteAuditory(Long.valueOf(id));
        return SUCCESS;
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
        this.filtrodesde = filtrodesde;
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

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getFiltrolevel() {
        return filtrolevel;
    }

    public void setFiltrolevel(String filtrolevel) {
        this.filtrolevel = filtrolevel;
    }

    public String getFiltrotype() {
        return filtrotype;
    }

    public void setFiltrotype(String filtrotype) {
        this.filtrotype = filtrotype;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getUserName() {
        return userName;
    }

  
}
