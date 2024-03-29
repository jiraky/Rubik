package it.univr.rubikcube.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javax.swing.JOptionPane;

/**
 * Credits form.
 * @author Mattia Zago
 */
public class Credits extends javax.swing.JDialog {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of the Credits form.
     * @param parent Parent form.
     * @param modal Whether this dialog should be modal.
     */
    public Credits(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        this.initComponents();
        setLocationRelativeTo(parent);
    }
    /**
     * Opens a Web page.
     * @param url Address to be opened.
     */
    public static final void openWebPage(final String url) {
        try {         
            Desktop.getDesktop().browse(URI.create(url));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to show the Web page"
                    + " as the default browser could not be launched.",
                    "Rubik Cube Solver", JOptionPane.ERROR_MESSAGE);
        }
     }

    // CHECKSTYLE:OFF Rationale: autogenerated code
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogoUnivr = new javax.swing.JLabel();
        Title = new javax.swing.JLabel();
        Sep = new javax.swing.JSeparator();
        Container = new javax.swing.JPanel();
        Mattia_Container = new javax.swing.JPanel();
        Mattia_Name = new javax.swing.JLabel();
        Mattia_Email = new javax.swing.JLabel();
        Mattia_Website = new javax.swing.JLabel();
        Alessandro_Container = new javax.swing.JPanel();
        Alessandro_Name = new javax.swing.JLabel();
        Alessandro_Email = new javax.swing.JLabel();
        Alessandro_Website = new javax.swing.JLabel();
        Stefano_Container = new javax.swing.JPanel();
        Stefano_Name = new javax.swing.JLabel();
        Stefano_Email = new javax.swing.JLabel();
        Stefano_Website = new javax.swing.JLabel();
        GitHub_Container = new javax.swing.JPanel();
        GitHub_Name = new javax.swing.JLabel();
        GitHub_Website = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Credits");
        setAlwaysOnTop(true);
        setResizable(false);

        LogoUnivr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogoUnivr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_univr.jpg"))); // NOI18N

        Title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Algorithms Project - Authors");
        Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Container.setLayout(new java.awt.GridLayout(2, 2));

        Mattia_Container.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Mattia_Name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Mattia_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mattia_Name.setText("Mattia Zago");

        Mattia_Email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mattia_Email.setText("mattia.zago@studenti.univr.it");
        Mattia_Email.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mattia_Email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mattia_EmailMouseClicked(evt);
            }
        });

        Mattia_Website.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mattia_Website.setText("www.zagomattia.it");
        Mattia_Website.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mattia_Website.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mattia_WebsiteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Mattia_ContainerLayout = new javax.swing.GroupLayout(Mattia_Container);
        Mattia_Container.setLayout(Mattia_ContainerLayout);
        Mattia_ContainerLayout.setHorizontalGroup(
            Mattia_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Mattia_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Mattia_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Mattia_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Mattia_Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Mattia_Website, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addContainerGap())
        );
        Mattia_ContainerLayout.setVerticalGroup(
            Mattia_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Mattia_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Mattia_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Mattia_Email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Mattia_Website)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Container.add(Mattia_Container);

        Alessandro_Container.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Alessandro_Name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Alessandro_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Alessandro_Name.setText("Alessandro Menti");

        Alessandro_Email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Alessandro_Email.setText("alessandro.menti@studenti.univr.it");
        Alessandro_Email.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Alessandro_Email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Alessandro_EmailMouseClicked(evt);
            }
        });

        Alessandro_Website.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Alessandro_Website.setText("www.alessandromenti.it");
        Alessandro_Website.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Alessandro_Website.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Alessandro_WebsiteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Alessandro_ContainerLayout = new javax.swing.GroupLayout(Alessandro_Container);
        Alessandro_Container.setLayout(Alessandro_ContainerLayout);
        Alessandro_ContainerLayout.setHorizontalGroup(
            Alessandro_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Alessandro_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Alessandro_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Alessandro_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Alessandro_Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Alessandro_Website, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addContainerGap())
        );
        Alessandro_ContainerLayout.setVerticalGroup(
            Alessandro_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Alessandro_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Alessandro_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Alessandro_Email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Alessandro_Website)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Container.add(Alessandro_Container);

        Stefano_Container.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Stefano_Name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Stefano_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Stefano_Name.setText("Stefano Angeleri");

        Stefano_Email.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Stefano_Email.setText("stefano.angeleri@studenti.univr.it");
        Stefano_Email.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Stefano_Email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Stefano_EmailMouseClicked(evt);
            }
        });

        Stefano_Website.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Stefano_Website.setText("www.angeleri.info");
        Stefano_Website.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Stefano_Website.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Stefano_WebsiteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Stefano_ContainerLayout = new javax.swing.GroupLayout(Stefano_Container);
        Stefano_Container.setLayout(Stefano_ContainerLayout);
        Stefano_ContainerLayout.setHorizontalGroup(
            Stefano_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Stefano_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Stefano_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Stefano_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Stefano_Email, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(Stefano_Website, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Stefano_ContainerLayout.setVerticalGroup(
            Stefano_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Stefano_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Stefano_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Stefano_Email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stefano_Website)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Container.add(Stefano_Container);

        GitHub_Container.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        GitHub_Name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        GitHub_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GitHub_Name.setText("Public Repository");

        GitHub_Website.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GitHub_Website.setText("https://github.com/jiraky90/Rubik/");
        GitHub_Website.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GitHub_Website.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GitHub_WebsiteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout GitHub_ContainerLayout = new javax.swing.GroupLayout(GitHub_Container);
        GitHub_Container.setLayout(GitHub_ContainerLayout);
        GitHub_ContainerLayout.setHorizontalGroup(
            GitHub_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GitHub_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GitHub_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GitHub_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GitHub_Website, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addContainerGap())
        );
        GitHub_ContainerLayout.setVerticalGroup(
            GitHub_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GitHub_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GitHub_Name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GitHub_Website, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Container.add(GitHub_Container);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogoUnivr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LogoUnivr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Container, javax.swing.GroupLayout.PREFERRED_SIZE, 170, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Alessandro_EmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Alessandro_EmailMouseClicked
        openWebPage("mailto://alessandro.menti@studenti.univr.it");
    }//GEN-LAST:event_Alessandro_EmailMouseClicked

    private void Alessandro_WebsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Alessandro_WebsiteMouseClicked
        openWebPage("http://www.alessandromenti.it");
    }//GEN-LAST:event_Alessandro_WebsiteMouseClicked

    private void Mattia_EmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mattia_EmailMouseClicked
        openWebPage("mailto://mattia.zago@studenti.univr.it");
    }//GEN-LAST:event_Mattia_EmailMouseClicked

    private void Mattia_WebsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mattia_WebsiteMouseClicked
        openWebPage("http://www.zagomattia.it");
    }//GEN-LAST:event_Mattia_WebsiteMouseClicked

    private void Stefano_EmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Stefano_EmailMouseClicked
        openWebPage("mailto://stefano.angeleri@studenti.univr.it");
    }//GEN-LAST:event_Stefano_EmailMouseClicked

    private void Stefano_WebsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Stefano_WebsiteMouseClicked
        openWebPage("http://www.angeleri.info");
    }//GEN-LAST:event_Stefano_WebsiteMouseClicked

    private void GitHub_WebsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GitHub_WebsiteMouseClicked
        openWebPage("https://github.com/jiraky90/Rubik/");
    }//GEN-LAST:event_GitHub_WebsiteMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Alessandro_Container;
    private javax.swing.JLabel Alessandro_Email;
    private javax.swing.JLabel Alessandro_Name;
    private javax.swing.JLabel Alessandro_Website;
    private javax.swing.JPanel Container;
    private javax.swing.JPanel GitHub_Container;
    private javax.swing.JLabel GitHub_Name;
    private javax.swing.JLabel GitHub_Website;
    private javax.swing.JLabel LogoUnivr;
    private javax.swing.JPanel Mattia_Container;
    private javax.swing.JLabel Mattia_Email;
    private javax.swing.JLabel Mattia_Name;
    private javax.swing.JLabel Mattia_Website;
    private javax.swing.JSeparator Sep;
    private javax.swing.JPanel Stefano_Container;
    private javax.swing.JLabel Stefano_Email;
    private javax.swing.JLabel Stefano_Name;
    private javax.swing.JLabel Stefano_Website;
    private javax.swing.JLabel Title;
    // End of variables declaration//GEN-END:variables
    // CHECKSTYLE:ON
}
