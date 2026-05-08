package ui;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("TabbedPane.tabAreaAlignment", "center");
        MainFrame mainFrame = new MainFrame();
    }
}