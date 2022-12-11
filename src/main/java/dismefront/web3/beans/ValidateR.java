package dismefront.web3.beans;

import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
@FacesValidator("RFieldValidator")
public class ValidateR implements Validator, Serializable {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object checkbox5) throws ValidatorException {
        Boolean ch1 = ((HtmlSelectBooleanCheckbox) uiComponent.getAttributes().get("checkbox1"))
                .isSelected();
        Boolean ch2 = ((HtmlSelectBooleanCheckbox) uiComponent.getAttributes().get("checkbox2"))
                .isSelected();
        Boolean ch3 = ((HtmlSelectBooleanCheckbox) uiComponent.getAttributes().get("checkbox3"))
                .isSelected();
        Boolean ch4 = ((HtmlSelectBooleanCheckbox) uiComponent.getAttributes().get("checkbox4"))
                .isSelected();
        Boolean ch5 = (Boolean) checkbox5;
        if (!(ch1 || ch2 || ch3 || ch4 || ch5)) {
            FacesMessage msg = new FacesMessage("Choose at least one element");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
