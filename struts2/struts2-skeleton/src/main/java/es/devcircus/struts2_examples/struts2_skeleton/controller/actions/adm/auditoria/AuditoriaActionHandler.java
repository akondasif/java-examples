package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import com.opensymphony.xwork2.ActionSupport;
import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.security.acl.model.action.ActionFacade;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.ApplicationFacade;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.devcircus.acl.acl.model.module.ModuleCVOFacade;
import es.itnoroeste.core.security.acl.model.module.ModuleFacade;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.auditory.model.auditory.AuditoryCVOServiceFacade;
import es.itnoroeste.core.security.auditory.model.auditory.AuditoryServiceFacade;
import es.itnoroeste.core.security.auditory.model.auditory.vo.Auditory;
import es.devcircus.acl.auditory.model.auditory.vo.AuditoryCVO;
import es.itnoroeste.core.security.authentication.model.user.UserFacade;

public class AuditoriaActionHandler extends CustomActionSupport {

    private String id;
    private AuditoryCVO current;

    private String appId;
    private Application app;
    private Action action;
    private String modId;
    private Module module;
      private AuditoryCVO auditoryPopup;
     private String userName=getUsername();
    public AuditoriaActionHandler() {
    }

    @Override
    public String execute() throws Exception {    
        return SUCCESS;
    }

    public String view() throws Exception {
          try {
        this.current = AuditoryCVOServiceFacade.obtainAuditoryCVO(Long.valueOf(id));
   
        this.app=ApplicationFacade.obtainApplication(userName, Long.valueOf(this.current.getApp().getId()));
        this.module=ModuleFacade.obtainModule(userName, Long.valueOf(this.current.getModule().getId()));
        this.action=ActionFacade.obtainAction(userName, Long.valueOf(this.current.getAction().getId()));
         return SUCCESS;
        } catch(InternalErrorException ex) {
            addActionError(getText("auditory.auditory.message.view.error"));
            return INPUT;
        }
   
    }

 public String recuperarAuditoryAjax() throws Exception {
        auditoryPopup=AuditoryCVOServiceFacade.obtainAuditoryCVO(Long.valueOf(this.id));
     
        auditoryPopup.setApp(null);  
        auditoryPopup.setModule(null);  
        return SUCCESS;
    }
    public AuditoryCVO getCurrent() {
        return current;
    }

    public void setCurrent(AuditoryCVO current) {
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public AuditoryCVO getAuditoryPopup() {
        return auditoryPopup;
    }

    public void setAuditoryPopup(AuditoryCVO auditoryPopup) {
        this.auditoryPopup = auditoryPopup;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getUserName() {
        return userName;
    }

    
    
}
