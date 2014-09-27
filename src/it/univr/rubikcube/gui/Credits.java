/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univr.rubikcube.gui;

/**
 *
 * @author Mattia
 */
public class Credits extends javax.swing.JDialog {

    /**
     * Creates new form Credits
     */
    public Credits(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
    }

    public void openWebPage(String url){
        try {         
          java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch (java.io.IOException e) {
            System.out.println(e.getMessage());
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
        Alessandro_Container = new javax.swing.JPanel();
        Alessandro_Name = new javax.swing.JLabel();
        Alessandro_Email = new javax.swing.JLabel();
        Alessandro_Website = new javax.swing.JLabel();
        Mattia_Container = new javax.swing.JPanel();
        Mattia_Name = new javax.swing.JLabel();
        Mattia_Email = new javax.swing.JLabel();
        Mattia_Website = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Credits");
        setAlwaysOnTop(true);
        setResizable(false);

        LogoUnivr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_univr.jpg"))); // NOI18N

        Title.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Algorithms Project - Authors");
        Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Container.setLayout(new java.awt.GridLayout(1, 2));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(LogoUnivr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LogoUnivr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Container, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    // CHECKSTYLE:ON

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Credits dialog = new Credits(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // CHECKSTYLE:OFF Rationale: autogenerated code
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Alessandro_Container;
    private javax.swing.JLabel Alessandro_Email;
    private javax.swing.JLabel Alessandro_Name;
    private javax.swing.JLabel Alessandro_Website;
    private javax.swing.JPanel Container;
    private javax.swing.JLabel LogoUnivr;
    private javax.swing.JPanel Mattia_Container;
    private javax.swing.JLabel Mattia_Email;
    private javax.swing.JLabel Mattia_Name;
    private javax.swing.JLabel Mattia_Website;
    private javax.swing.JSeparator Sep;
    private javax.swing.JLabel Title;
    // End of variables declaration//GEN-END:variables
    // CHECKSTYLE:ON
}
