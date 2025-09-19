package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;

import dev.ak.qtcp.view.*;

public class LauncherViewModel {

    public void helpAboutCommand(ActionEvent e) {
        new AboutView();
    }

    public void createServerCommand(ActionEvent e) {
        new ServerView(new ServerViewModel());
    }

    public void createClientCommand(ActionEvent e) {
        new ClientView(new ClientViewModel());
    }
}
