package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import com.opensymphony.xwork2.ActionSupport;
import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.model.impl.PaginatedListHibernate;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.auditory.model.userhttpauditory.UserHttpAuditoryCVOServiceFacade;
import es.itnoroeste.core.security.auditory.model.userhttpauditory.UserHttpAuditoryServiceFacade;
import es.itnoroeste.core.security.auditory.model.userhttpauditory.vo.UserHttpAuditory;
import es.devcircus.acl.auditory.model.userhttpauditory.vo.UserHttpAuditoryCVO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AuditoriaWebUsuarioListActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl auditorias;
    private String filtrodesde;
    private String filtrohasta;
    private String filtroapp;

    private String filtrouser;
    private String requestURI = "AuditoriaWebUsuarioList";
    private HttpServletRequest request;
    private List<Application> app;
     private String userName = getUsername();
    public AuditoriaWebUsuarioListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.auditorias = new PaginatedListHibernate<UserHttpAuditoryCVO>(request);
         this.app = ApplicationFacade.listApplication(userName);
        //Si esta activo algun filtro
        if (!filtrodesde.equals("")||!filtrohasta.equals("")||!filtroapp.equals("")||
                !filtrouser.equals("")) {
            this.auditorias = UserHttpAuditoryCVOServiceFacade.findUserHttpAuditoryCVO(
                    filtrodesde, filtrohasta, filtroapp, filtrouser, this.auditorias);
        }
        else{
            this.auditorias = UserHttpAuditoryCVOServiceFacade.getPaginatedlist(this.auditorias);
        } 
        return SUCCESS;
    }

    public String list() throws Exception {
 this.app = ApplicationFacade.listApplication(userName);
        this.filtrodesde = null;
        this.filtrohasta = null;
        this.filtroapp = null;
     
        this.filtrouser = null;
        PaginatedListImpl<UserHttpAuditoryCVO> aud = new PaginatedListHibernate<UserHttpAuditoryCVO>(request);
        this.auditorias = UserHttpAuditoryCVOServiceFacade.getPaginatedlist(aud);
        return SUCCESS;
    }

    public String delete() throws Exception {
        UserHttpAuditoryServiceFacade.deleteAuditory(Long.valueOf(id));
        return SUCCESS;
    }

    public PaginatedListImpl getAuditorias() {
        return auditorias;
    }

    public List<Application> getApp() {
        return app;
    }

    public void setApp(List<Application> app) {
        this.app = app;
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


    public String getFiltrouser() {
        return filtrouser;
    }

    public void setFiltrouser(String filtrouser) {
        this.filtrouser = filtrouser;
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

}
