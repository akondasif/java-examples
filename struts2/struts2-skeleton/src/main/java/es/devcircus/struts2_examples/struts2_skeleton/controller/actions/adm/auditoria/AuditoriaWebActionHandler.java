package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import com.opensymphony.xwork2.ActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.devcircus.acl.auditory.model.httpauditory.HttpAuditoryCVOServiceFacade;
import es.itnoroeste.core.security.auditory.model.httpauditory.HttpAuditoryServiceFacade;
import es.itnoroeste.core.security.auditory.model.httpauditory.vo.HttpAuditory;
import es.devcircus.acl.auditory.model.httpauditory.vo.HttpAuditoryCVO;

public class AuditoriaWebActionHandler extends ActionSupport {

    private String id;
    private HttpAuditoryCVO current;

    public AuditoriaWebActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String view() throws Exception {
          try {
        this.current = HttpAuditoryCVOServiceFacade.obtainHttpAuditoryCVO(Long.valueOf(id));
         return SUCCESS;
        } catch(InternalErrorException ex) {
            addActionError(getText("auditory.auditory.message.view.error"));
            return INPUT;
        }
    }

//    public String edit() throws Exception {
//        this.current = HttpAuditoryServiceFacade.updateAuditory(current);
//        return SUCCESS;
//    }

//    public String delete() throws Exception {
//        Boolean result = HttpAuditoryServiceFacade.deleteAuditory(Long.valueOf(id));
//        return SUCCESS;
//    }

//    public String create() throws Exception {
//        if (current != null) {
//            HttpAuditoryServiceFacade.createAuditory(current);
//        }
//        return SUCCESS;
//    }

    public HttpAuditoryCVO getCurrent() {
        return current;
    }

    public void setCurrent(HttpAuditoryCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
