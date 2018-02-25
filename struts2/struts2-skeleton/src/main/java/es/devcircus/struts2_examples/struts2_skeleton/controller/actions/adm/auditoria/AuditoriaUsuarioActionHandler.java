package es.devcircus.struts2_examples.struts2_skeleton.controller.actions.adm.auditoria;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.exceptions.InternalErrorException;
import es.itnoroeste.core.security.acl.model.action.vo.Action;
import es.itnoroeste.core.security.acl.model.application.vo.Application;
import es.itnoroeste.core.security.acl.model.module.vo.Module;
import es.devcircus.acl.auditory.model.userauditory.UserAuditoryCVOServiceFacade;
import es.devcircus.acl.auditory.model.userauditory.vo.UserAuditoryCVO;
import es.itnoroeste.core.security.authentication.model.user.vo.User;
import es.devcircus.acl.authentication.model.user.vo.UserCVO;
import es.itnoroeste.empleados.empleado.vo.Empleado;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AuditoriaUsuarioActionHandler extends CustomActionSupport implements ServletRequestAware {
    private String id;
    private String appId;
//    private Application app;
//    private Action action;
    private String modId;
//    private Module module;
    private UserCVO userCVO;
    private User user;
    private UserAuditoryCVO current;
    private UserAuditoryCVO userauditoryPopup;
    private Empleado empleado;
    private HttpServletRequest request;
    private String userName = getUsername();

    public AuditoriaUsuarioActionHandler() {
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String view() throws Exception { 
          try {
        this.current = UserAuditoryCVOServiceFacade.obtainUserAuditoryCVO(userName,Long.valueOf(this.id));
        id = current.getUser().getId().toString();
         return SUCCESS;
        } catch(InternalErrorException ex) {
            addActionError(getText("auditory.auditory.message.view.error"));
            return INPUT;
        }
    }

    public String recuperarUserauditoryAjax() throws Exception { 
        this.userauditoryPopup = UserAuditoryCVOServiceFacade.obtainUserAuditoryCVO(userName,Long.valueOf(this.id));
        userauditoryPopup.setApp(null);
        userauditoryPopup.setModule(null);
        userauditoryPopup.setSedes(null);
        userauditoryPopup.setDepartamentos(null);
        return SUCCESS;        
    }

    public String getUserName() {
        return userName;
    }

  

    public UserAuditoryCVO getCurrent() {
        return current;
    }

    public void setCurrent(UserAuditoryCVO current) {
        this.current = current;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

//    public Application getApp() {
//        return app;
//    }
//
//    public void setApp(Application app) {
//        this.app = app;
//    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

//    public Module getModule() {
//        return module;
//    }
//
//    public void setModule(Module module) {
//        this.module = module;
//    }

    public UserCVO getUserCVO() {
        return userCVO;
    }

    public void setUserCVO(UserCVO userCVO) {
        this.userCVO = userCVO;
    }

//    public Action getAction() {
//        return action;
//    }
//
//    public void setAction(Action action) {
//        this.action = action;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAuditoryCVO getUserauditoryPopup() {
        return userauditoryPopup;
    }

    public void setUserauditoryPopup(UserAuditoryCVO userauditoryPopup) {
        this.userauditoryPopup = userauditoryPopup;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
