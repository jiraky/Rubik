package it.univr.rubikcube.gui;

import it.univr.rubikcube.algorithms.Move;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main window for the Rubik Cube Solver.
 * @author Alessandro Menti
 */
public class MainWindow extends JApplet implements ActionListener {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Specifies if this program was started as an applet or as an application.
     */
    static boolean startedAsApplication = false;

    /**
     * List of moves.
     */
    JList<Move> movesList;

    /**
     * Entry point for the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocale(new Locale("en", "US"));
        // FIXME Set the correct minimum dimension below and in the manifest
        mainWindow.setMinimumSize(new Dimension(200, 200));
        mainWindow.setResizable(true);
        mainWindow.setTitle("Rubik Cube Solver");
        startedAsApplication = true;
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
                    JRootPane appRootPane = MainWindow.this.getRootPane();
                    JPanel buttonsPanel = new JPanel();
                    JButton executeButton = new JButton();
                    JButton quitButton = new JButton();
                    // Set the native look and feel for the platform
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        SwingUtilities.updateComponentTreeUI(MainWindow.this);
                        // FIXME - do we need to call pack() afterwards?
                    } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                        // Nothing to do
                    }
                    appRootPane.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    // FIXME Add the 3D view here
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    MainWindow.this.movesList = new JList<>();
                    MainWindow.this.movesList.getAccessibleContext()
                        .setAccessibleDescription("List of moves that take the cube in its final configuration.");
                    MainWindow.this.movesList.setMinimumSize(new Dimension(50, 50));
                    appRootPane.add(MainWindow.this.movesList, gbc);
                    // Frame con algoritmo e numero facce
                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    // Execute and Quit buttons
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    buttonsPanel.setLayout(new FlowLayout());
                    buttonsPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    executeButton.setText("Execute");
                    executeButton.setMnemonic('E');
                    executeButton.setToolTipText("Executes the selected algorithm.");
                    executeButton.setActionCommand("execute");
                    executeButton.addActionListener(MainWindow.this);
                    quitButton.setText("Quit");
                    quitButton.setMnemonic('Q');
                    quitButton.setToolTipText("Quits the program.");
                    quitButton.setActionCommand("quit");
                    quitButton.addActionListener(MainWindow.this);
                    buttonsPanel.add(executeButton);
                    // FIXME Add the check back when the UI is finished
                    //if (startedAsApplication) {
                        buttonsPanel.add(quitButton);
                    //}
                    appRootPane.add(buttonsPanel, gbc);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            System.err.println("Unable to create the GUI");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("execute".equals(e.getActionCommand())) {
            // FIXME
        } else if ("quit".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }
}
