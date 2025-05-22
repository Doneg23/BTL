package GUI.Menu;

import javax.swing.*;
import GUI.Frame;

public class Menu extends JMenuBar {
    public Menu(Frame frame) {
        add(new GameMenu(frame));
        add(new OptionsMenu(frame));
        add(new HelpMenu(frame));
    }
}
