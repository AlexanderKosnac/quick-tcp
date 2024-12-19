package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;

import dev.ak.qtcp.view.*;

public class LauncherViewModel {
    
    public void HelpAboutCommand(ActionEvent e) {
        new AboutView();
    }

    public void CreateServerCommand(ActionEvent e) {
        new ServerView(new ServerViewModel());
    }

    public void CreateClientCommand(ActionEvent e) {
        new ClientView(new ClientViewModel());
    }
}
