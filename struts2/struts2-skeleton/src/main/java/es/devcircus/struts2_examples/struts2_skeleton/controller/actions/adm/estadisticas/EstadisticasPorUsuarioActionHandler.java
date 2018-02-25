package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.estadisticas;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.estadisticas.model.estadisticasusuario.EstadisticasUsuarioCVOServiceFacade;
import es.devcircus.acl.estadisticas.model.estadisticasusuario.vo.EstadisticasUsuarioCVO;
import java.util.ArrayList;
import java.util.List;

public class EstadisticasPorUsuarioActionHandler extends CustomActionSupport {

    private String filtrodesde;
    private String filtrohasta;
    private String filtroapp;
    private String filtromod;
    private String filtroaccion;
    private List<EstadisticasUsuarioCVO> estadisticas;
    private String userName = getUsername();
    private List<Application> app;
    private List<Module> listaModulos;
    private List<Action> acciones;

    public EstadisticasPorUsuarioActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.app = ApplicationFacade.listApplication(userName);
        this.listaModulos = new ArrayList<Module>();
        this.acciones = new ArrayList<Action>();
        this.estadisticas = EstadisticasUsuarioCVOServiceFacade.findEstadisticasUsuarioCVO(userName,filtrodesde, filtrohasta, filtroapp, filtromod, filtroaccion);
 
        //recarga de combos de accion y módulo
        if ( filtroapp != null && !filtroapp.equals("-1") ) {
            Application ap = ApplicationFacade.obtainApplicationByCode(userName, filtroapp);
            listaModulos = ApplicationFacade.getAllModulesFromApplication(userName, ap.getId());
            for (Module m : listaModulos) {
                m.setActions(ModuleFacade.getAllActionsFromModule(userName, m.getId()));
            }
        }

        if (filtromod != null &&!filtromod.equals("-1")  ) {
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
        this.filtroaccion = "-1";
        this.acciones = new ArrayList<Action>();
        this.listaModulos = new ArrayList<Module>();
        this.estadisticas = EstadisticasUsuarioCVOServiceFacade.findEstadisticasUsuarioCVO(userName,filtrodesde, filtrohasta, filtroapp, filtromod, filtroaccion);
        return SUCCESS;
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

    public List<EstadisticasUsuarioCVO> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(List<EstadisticasUsuarioCVO> estadisticas) {
        this.estadisticas = estadisticas;
    }

    public String getFiltroaccion() {
        return filtroaccion;
    }

    public void setFiltroaccion(String filtroaccion) {
        this.filtroaccion = filtroaccion;
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
