package it.univr.rubikcube.gui;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Main window for the Rubik Cube Solver.
 * @author Alessandro Menti
 */
public class MainWindow extends JApplet {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Entry point for the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocale(new Locale("en", "US"));
        // FIXME Set the correct minimum dimension below
        mainWindow.setMinimumSize(new Dimension(200, 200));
        mainWindow.setResizable(true);
        mainWindow.setTitle("Rubik Cube Solver");
        JApplet applet = new MainWindow();
        applet.init();
        applet.start();
        mainWindow.add(applet);
        mainWindow.setVisible(true);
    }

    /**
     * Initializes the applet.
     */
    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    // FIXME Create the GUI here
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            System.err.println("Unable to create the GUI");
        }
    }
}
