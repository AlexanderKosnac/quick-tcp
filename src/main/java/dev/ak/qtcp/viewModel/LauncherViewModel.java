package dev.ak.qtcp.viewModel;

import java.awt.event.ActionEvent;

public class LauncherViewModel {
    
    public void HelpAboutCommand(ActionEvent e) {
        System.out.println("'About' menu item clicked");
    }

    public void CreateServerCommand(ActionEvent e) {
        System.out.println("Create server clicked");
    }

    public void CreateClientCommand(ActionEvent e) {
        System.out.println("Create client clicked");
    }
}
