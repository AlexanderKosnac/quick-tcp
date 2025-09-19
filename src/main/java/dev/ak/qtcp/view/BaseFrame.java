package dev.ak.qtcp.view;

import javax.swing.*;

import dev.ak.qtcp.core.Assets;

public class BaseFrame extends JFrame {

    public BaseFrame() {
        super();
        setTitle("Quick TCP");
        setIconImages(Assets.getAllIcons());
    }
}
