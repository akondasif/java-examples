package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.estadisticas;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.common.territory.common.localidad.vo.Localidad;
import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.estadisticas.model.estadisticasaccion.EstadisticasAccionCVOServiceFacade;
import es.devcircus.acl.estadisticas.model.estadisticasaccion.vo.EstadisticasAccionCVO;
import java.util.ArrayList;
import java.util.List;

public class EstadisticasPorAccionActionHandler extends CustomActionSupport {

    private String filtrodesde;
    private String filtrohasta;
    private String filtroapp;
    private String filtromod;
    private List<EstadisticasAccionCVO> estadisticas;
    private String userName = getUsername();
    private List<Application> app;
    private List<Module> listaModulos;

    public EstadisticasPorAccionActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.app = ApplicationFacade.listApplication(userName);
        this.listaModulos = new ArrayList<Module>();
        this.estadisticas = EstadisticasAccionCVOServiceFacade.findEstadisticasAccionCVO(userName, filtrodesde, filtrohasta, filtroapp, filtromod);

        if (filtroapp != null && !filtroapp.equals("-1")) {
            Application ap = ApplicationFacade.obtainApplicationByCode(userName, filtroapp);
            listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, ap.getId());
            for (Module m : listaModulos) {
                m.setActions(ModuleFacade.getAllActionsFromModule(userName, m.getId()));
            }
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.filtrodesde = null;
        this.filtrohasta = null;
        this.filtroapp = "-1";
        this.app = ApplicationFacade.listApplication(userName);
        this.filtromod = "-1";
        this.listaModulos = new ArrayList<Module>();
        this.estadisticas = EstadisticasAccionCVOServiceFacade.findEstadisticasAccionCVO(userName, filtrodesde, filtrohasta, filtroapp, filtromod);
        return SUCCESS;
    }

    public List<Application> getApp() {
        return app;
    }

    public void setApp(List<Application> app) {
        this.app = app;
    }

    public List<EstadisticasAccionCVO> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(List<EstadisticasAccionCVO> estadisticas) {
        this.estadisticas = estadisticas;
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

    public String getFiltromod() {
        return filtromod;
    }

    public void setFiltromod(String filtromod) {
        this.filtromod = filtromod;
    }

    public List<Module> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<Module> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public String getUserName() {
        return userName;
    }
}
