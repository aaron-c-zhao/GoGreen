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

    public void illegalArgumentExceptionhandler(IllegalArgumentException e){
        ConfirmDialog informDialog = screens.regularInformDialog();
        InformController controller =(InformController) informDialog.getConfirmDialogController();
        controller.setInformation(e.getMessage());
        informDialog.showAndWait();
    }
}
