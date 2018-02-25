package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.auditory.model.httpauditory.HttpAuditoryCVOServiceFacade;
import es.itnoroeste.core.security.auditory.model.httpauditory.HttpAuditoryServiceFacade;
import es.itnoroeste.core.security.auditory.model.httpauditory.vo.HttpAuditory;
import es.devcircus.acl.auditory.model.httpauditory.vo.HttpAuditoryCVO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AuditoriaWebListActionHandler extends ActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl auditorias;
    private String filtrodesde;
    private String filtrohasta;
    private String filtroapp;
    
    private String filtroresult;
    private String filtrolevel;
     private List<Application> app;
    private String requestURI = "AuditoriaWebList";
    private HttpServletRequest request;

    public AuditoriaWebListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
          this.app = ApplicationFacade.listApplication("userId");
        this.auditorias = new PaginatedListHibernate<HttpAuditoryCVO>(request);
        //Si esta activo algun filtro
        if (!filtrodesde.equals("")||!filtrohasta.equals("")||!filtroapp.equals("-1")
             || !filtrolevel.equals("-1") || !filtroresult.equals("") ) {
            this.auditorias = HttpAuditoryCVOServiceFacade.findHttpAuditoryCVO(filtrodesde, filtrohasta, filtroapp,this.auditorias);
        }
        else{
            this.auditorias = HttpAuditoryCVOServiceFacade.getPaginatedlist(this.auditorias);
        } 
        return SUCCESS;
    }

    public String list() throws Exception {

        this.filtrodesde = null;
        this.filtrohasta = null;
        this.filtroapp = "-1";
        this.app = ApplicationFacade.listApplication("userId");
        this.filtroresult = null;
        PaginatedListImpl<HttpAuditoryCVO> aud = new PaginatedListHibernate<HttpAuditoryCVO>(request);
        this.auditorias = HttpAuditoryCVOServiceFacade.getPaginatedlist(aud);
        return SUCCESS;
    }

    public String delete() throws Exception {
        HttpAuditoryServiceFacade.deleteAuditory(Long.valueOf(id));
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

    public String getFiltrohasta() {
        return filtrohasta;
    }

    public void setFiltrohasta(String filtrohasta) {
        this.filtrohasta = filtrohasta;
    }

    public String getFiltroresult() {
        return filtroresult;
    }

    public void setFiltroresult(String filtroresult) {
        this.filtroresult = filtroresult;
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

    public List<Application> getApp() {
        return app;
    }

    public void setApp(List<Application> app) {
        this.app = app;
    }

    public String getFiltrolevel() {
        return filtrolevel;
    }

    public void setFiltrolevel(String filtrolevel) {
        this.filtrolevel = filtrolevel;
    }

}
