package gogreenclient.datamodel;

import gogreenclient.screens.InformController;
import gogreenclient.screens.ScreenConfiguration;
import gogreenclient.screens.window.ConfirmDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ExceptionHandler {

    @Autowired
    private ScreenConfiguration screens;

    /**
     * Handler of IllegalArgumentException, which will get the message from the exception,
     * and make the informDialog shown with the message.
     *
     * @param exception an instance of IllegalArgumentException.
     */
    public void illegalArgumentExceptionhandler(IllegalArgumentException exception) {
        ConfirmDialog informDialog = screens.regularInformDialog();
        InformController controller = (InformController) informDialog.getConfirmDialogController();
        controller.setInformation(exception.getMessage());
        informDialog.showAndWait();
    }
}
