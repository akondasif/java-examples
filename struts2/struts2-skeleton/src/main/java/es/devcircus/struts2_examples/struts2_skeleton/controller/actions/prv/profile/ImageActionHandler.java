/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.acl.controller.actions.prv.profile;

import es.devcircus.acl.controller.actions.CustomActionSupport;
import es.itnoroeste.core.security.authentication.model.user.UserFacade;
import es.itnoroeste.core.security.authentication.model.user.vo.User;
import es.itnoroeste.social.person.PersonFacade;
import es.itnoroeste.social.util.FileUtils;
import java.io.File;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 *
 * @author jose
 */
public class ImageActionHandler extends CustomActionSupport implements ServletRequestAware {

    private String id;
    private String fileUploadContentType = "";
    private String fileUploadBase64 = "";
    private String userName = getUsername();
    private HttpServletRequest request;

    public ImageActionHandler() {
    }

    @SkipValidation 
    @Override
    public String execute() throws Exception {
        User current = UserFacade.obtainUserByUsername(userName, id);
        current.setAcls(null);
        current.setRoles(null);
        File fileUpload = PersonFacade.obtainPersonProfileImage(userName, current.getIdPerson());
         if (fileUpload != null && fileUpload.exists()) {
            this.fileUploadContentType = new MimetypesFileTypeMap().getContentType(fileUpload.getName());
            this.fileUploadBase64 = FileUtils.getBase64(fileUpload);
            return SUCCESS;
        }
          return SUCCESS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadBase64() {
        return fileUploadBase64;
    }

    public void setFileUploadBase64(String fileUploadBase64) {
        this.fileUploadBase64 = fileUploadBase64;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}
