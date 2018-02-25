package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.devcircus.acl.auditory.model.userhttpauditory.UserHttpAuditoryCVOServiceFacade;
import es.itnoroeste.core.security.auditory.model.userhttpauditory.UserHttpAuditoryServiceFacade;
import es.itnoroeste.core.security.auditory.model.userhttpauditory.vo.UserHttpAuditory;
import es.devcircus.acl.auditory.model.userhttpauditory.vo.UserHttpAuditoryCVO;

public class AuditoriaWebUsuarioActionHandler extends ActionSupport {

    private String id;
    private UserHttpAuditoryCVO current;
    
    public AuditoriaWebUsuarioActionHandler() {
    }

    @Override
    public String execute() throws Exception {    
        return SUCCESS;
    }

    public String view() throws Exception {
          try {
        this.current = UserHttpAuditoryCVOServiceFacade.obtainUserHttpAuditoryCVO(Long.valueOf(id));
         return SUCCESS;
        } catch(InternalErrorException ex) {
            addActionError(getText("auditory.auditory.message.view.error"));
            return INPUT;
        }
    }

//    public String edit() throws Exception {
//        this.current = UserHttpAuditoryServiceFacade.updateAuditory(current);
//        return SUCCESS;
//    }

//    public String delete() throws Exception {
//        Boolean result = UserHttpAuditoryServiceFacade.deleteAuditory(Long.valueOf(id));
//        return SUCCESS;
//    }

//    public String create() throws Exception {
//        if (current != null) {
//            UserHttpAuditoryServiceFacade.createAuditory(current);
//        }
//        return SUCCESS;
//    }

    public UserHttpAuditoryCVO getCurrent() {
        return current;
    }

    public void setCurrent(UserHttpAuditoryCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
