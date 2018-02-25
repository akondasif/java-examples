package es.devcircus.acl.controller.actions;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.config.ConfigurationManager;

public class IndexActionHandler extends ActionSupport {

    /**
     * 
     */
    public IndexActionHandler() {
    }

    /**
     * 
     * @return
     * @throws Exception 
     */
    @Override
    public String execute() throws Exception {    
        return SUCCESS;
    }

    /**
     * 
     * @return 
     */
    public String index() {
        //Cargamos el fichero de configuración con las propiedades del portal
        ConfigurationManager.configure("portal.properties");
        //Retornamos ok
        return SUCCESS;
    }

}
