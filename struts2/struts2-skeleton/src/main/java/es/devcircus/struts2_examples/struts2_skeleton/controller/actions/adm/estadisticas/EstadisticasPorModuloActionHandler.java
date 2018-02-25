package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.estadisticas;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.estadisticas.model.estadisticasmodulo.EstadisticasModuloCVOServiceFacade;
import es.devcircus.acl.estadisticas.model.estadisticasmodulo.vo.EstadisticasModuloCVO;
import java.util.ArrayList;
import java.util.List;



public class EstadisticasPorModuloActionHandler extends CustomActionSupport {
    private String filtrodesde;
    private String filtrohasta;
    private String filtroapp;
    private String filtromod;
    private List<EstadisticasModuloCVO> estadisticas;
    private String userName = getUsername();
    private List<Application> app;
    private List<Module> listaModulos;

   public EstadisticasPorModuloActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        this.app = ApplicationFacade.listApplication(userName);    
        this.estadisticas = EstadisticasModuloCVOServiceFacade.findEstadisticasModuloCVO(userName,filtrodesde, filtrohasta, filtroapp);
        return SUCCESS;
    }

    public String list() throws Exception {
         this.filtrodesde = null;
        this.filtrohasta = null;
        this.filtroapp = "-1";
        this.app = ApplicationFacade.listApplication(userName);
        this.estadisticas = EstadisticasModuloCVOServiceFacade.findEstadisticasModuloCVO(userName,filtrodesde, filtrohasta, filtroapp);
        return SUCCESS;
    }

    public List<Application> getApp() {
        return app;
    }

    public void setApp(List<Application> app) {
        this.app = app;
    }

    public List<EstadisticasModuloCVO> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(List<EstadisticasModuloCVO> estadisticas) {
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
