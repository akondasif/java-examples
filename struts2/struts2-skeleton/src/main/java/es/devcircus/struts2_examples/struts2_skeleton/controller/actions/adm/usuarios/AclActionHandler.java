package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.usuarios;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.security.acl.model.acl.AclFacade;
import es.itnoroeste.core.security.acl.model.acl.vo.Acl;
import es.itnoroeste.core.security.acl.model.aclentry.vo.AclEntry;
import java.util.List;

public class AclActionHandler extends ActionSupport {

    private String id;
    private Acl current;
    private String aclEntry;
    
    public AclActionHandler() {
    }

    @Override
    public String execute() throws Exception {    
        return SUCCESS;
    }

    public String view() throws Exception {
        this.aclEntry=" ";
        this.current = AclFacade.obtainAcl("userId", Long.valueOf(id));
        
         List<AclEntry> aclEntrys = AclFacade.getAllAclEntrysFromAcl("userId", Long.valueOf(id));
        for(AclEntry a : aclEntrys) {
            this.aclEntry = this.aclEntry.concat("acción "+a.getSid()+" permiso"+a.getPermission() +" \n");
        }
        return SUCCESS;
        
        
        
    }

    public String edit() throws Exception {
        this.current = AclFacade.updateAcl("userId", current);
        return SUCCESS;
    }

    public String delete() throws Exception {

        Boolean result = AclFacade.deleteAcl("userId", Long.valueOf(id));
        return SUCCESS;
    }

    public String create() throws Exception {
        if (current != null) {
            AclFacade.createAcl("userId", current);
        }
        return SUCCESS;
    }

    public Acl getCurrent() {
        return current;
    }

    public void setCurrent(Acl current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
