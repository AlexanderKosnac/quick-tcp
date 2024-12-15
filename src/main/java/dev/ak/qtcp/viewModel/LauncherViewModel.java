package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;

import dev.ak.qtcp.view.AboutView;

public class LauncherViewModel {
    
    public void HelpAboutCommand(ActionEvent e) {
        new AboutView();
    }

    public void CreateServerCommand(ActionEvent e) {
        System.out.println("Create server clicked");
    }

    public void CreateClientCommand(ActionEvent e) {
        System.out.println("Create client clicked");
    }
}
