package dev.ak.qtcp;

import dev.ak.qtcp.view.LauncherView;
import dev.ak.qtcp.viewModel.LauncherViewModel;

public class App 
{
    public static void main(String[] args)
    {
        new LauncherView(new LauncherViewModel());
    }
}
