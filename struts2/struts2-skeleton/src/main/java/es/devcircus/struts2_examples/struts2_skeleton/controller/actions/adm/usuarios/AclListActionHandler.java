package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.model.impl.PaginatedListImpl;
import es.itnoroeste.core.security.acl.model.acl.AclFacade;
import es.itnoroeste.core.security.acl.model.acl.vo.Acl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AclListActionHandler extends ActionSupport implements ServletRequestAware {

    private String id;
    private PaginatedListImpl acls;
    private String filtroid;
    private String requestURI = "AclsList";
    private HttpServletRequest request;

    public AclListActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        //comprobar los filtros de busqueda Ahora mismo si cumple alguno de los filtros se muestra en el listado
        //Comprobamos si se ha introducido el filtro del codigo
        if (!filtroid.equals("")) {
            this.acls = new PaginatedListImpl<Acl>(request);
            List<Acl> p = new ArrayList<Acl>();
            Acl acl = AclFacade.obtainAcl("userId", Long.valueOf(filtroid));
            if (acl != null) {
                p.add(acl);
                this.acls.setList(p);
            }
        }
        return SUCCESS;
    }

    public String list() throws Exception {
        this.filtroid = null;
        PaginatedListImpl<Acl> p = new PaginatedListImpl<Acl>(request);
        this.acls = AclFacade.getPaginatedlist("userId", p);
        return SUCCESS;
    }

    public String delete() throws Exception {
        AclFacade.deleteAcl("userId", Long.valueOf(id));
        return SUCCESS;
    }

    /**
     * Método que activa el elemento en cuestión con el que estamos trabajando
     *
     * @return
     * @throws Exception
     */
    public String active() throws Exception {
        //Controlamos que la operacion se realizase con exito.           
        if (AclFacade.activateAcl("userId", Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.
            addActionMessage(getText("usuarios.acl.message.activar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("usuarios.acl.message.activar.error"));
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
        if (AclFacade.deActivateAcl("userId", Long.valueOf(id))) {
            //Añadimos un mensaje que indique al usuario que la acción se ha 
            //realizado con exito.            
            addActionMessage(getText("usuarios.acl.message.desactivar"));
            return SUCCESS;
        } else {
            //Añadimos un mensaje que indique al usuario que ha habido errores
            //durante la realizacion de la accion.
            addActionError(getText("usuarios.acl.message.dsactivar.error"));
            return INPUT;
        }
    }

    public PaginatedListImpl getAcls() {
        return acls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFiltroid() {
        return filtroid;
    }

    public void setFiltroid(String filtroid) {
        this.filtroid = filtroid;
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
