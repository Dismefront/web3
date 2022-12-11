package dismefront.web3.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;

@Named
@SessionScoped
@FacesValidator("YFieldValidator")
public class ValidateY implements Validator, Serializable {

    private void throwException(String message) throws ValidatorException {
        FacesMessage msg = new FacesMessage(message);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String model = (String) o;
        double parsed = 0;
        try {
            parsed = Double.parseDouble(model);
        }
        catch (Exception ex) {
            throwException("Incorrect input");
        }
        if (parsed <= -5 || parsed >= 3) {
            throwException("Out of borders");
        }
    }

}
