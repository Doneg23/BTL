package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class InfoBox implements WindowListener {
    private final Frame frame;

    public InfoBox(Frame fr, String title, String message, int option) {
        frame = fr;
        final JFrame dialog = new JFrame(title);
        final JButton button = new JButton("Ok");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dialog.dispose();
            }
        });
        JButton[] buttons = { button };
        JOptionPane optionPane = new JOptionPane(
                message,
                option,
                0, null, buttons, button);
        dialog.getContentPane().add(optionPane);
        dialog.setSize(500,300);
        dialog.setLocationRelativeTo(fr);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        frame.pauseGame();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        frame.resumeGame();
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
