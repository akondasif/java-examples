package es.devcircus.acl.controller.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.security.core.context.SecurityContext;
public class LoginActionHandler extends ActionSupport {

    /**
     * 
     */
    public LoginActionHandler() {
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
    public String login() {
        try {
            if (((SecurityContext) ActionContext.getContext().getSession().get("SPRING_SECURITY_CONTEXT")).getAuthentication().isAuthenticated()) {                
                return SUCCESS;
            }
        } catch (Exception ex) {
            return INPUT;
        }
        return INPUT;
    }

    /**
     * 
     * @return 
     */
    @SkipValidation
    public String logout() {
        try {
            System.out.println("Intentando Usuario saliendo de la sesión " + ActionContext.getContext().getSession().get("dni"));
            if (ActionContext.getContext().getSession().containsKey("dni")) {

                System.out.println("Usuario saliendo de la sesión " + ActionContext.getContext().getSession().get("dni"));
//                empleado = (EmpleadoVO) ActionContext.getContext().getSession().get("empleado");

                ActionContext.getContext().getSession().remove("empleado");
                ActionContext.getContext().getSession().remove("dni");
//                logger.info("El empleado " + empleado.getNombre() + " con dni " + empleado.getDni() + " salió de la sesión.");
            }
        } catch (Exception e) {
            System.err.println("Error saliendo de la sesión con el actionContext");
            return ERROR;
        }
        return SUCCESS;
    }
}
