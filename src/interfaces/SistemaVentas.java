package interfaces;

import coneccionBD.Coneccion;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SistemaVentas extends javax.swing.JFrame {

    DefaultTableModel model;
    Coneccion objconexion = new Coneccion();
    Connection objconect = objconexion.getConnection();
    private int numBoleta, id;
    private String usuario, cuenta, tipo_usuario, producto, precio;

    public SistemaVentas() {
        numBoleta = 0;
        initComponents();
        cargarDatosTabla("");
    }

    public void cargarDatosTabla(String valores) {
        usuario = Login.getUsuario();
        tipo_usuario = Login.getTipoUsuario();
        cuenta = Login.getCuenta();
        id = Login.getId();
        try {
            String[] titulosTabla = {"Código", "Descripción",
                "Precio $"};
            String[] registrosBD = new String[3];
            // llamamos a la trabla y le asignamos títulos pero aún sin valores
            model = new DefaultTableModel(null, titulosTabla);
            String consultaSQL = "SELECT * FROM productos where"
                    + " CONCAT (nombre,'', precio) "
                    + "LIKE '%" + valores + "%'";
            Statement st = objconect.createStatement();
            ResultSet result = st.executeQuery(consultaSQL);

            while (result.next()) {
                /* se utiliza el uno por que en la tabla este representa 
                el primer campo el 2 el segundo campo así para los demás
                 */
                registrosBD[0] = result.getString(1);
                registrosBD[1] = result.getString(2);
                registrosBD[2] = result.getString(3);
                model.addRow(registrosBD);
            }

            tblProductos.setModel(model);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblProductos.getColumnModel().getColumn(1).setPreferredWidth(350);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void consultarProducto(String codigo) {
        String capturarCampo = "";
        String sql = "SELECT * FROM productos where codigo='"
                + codigo + "'";
        try {
            Statement st = objconect.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                // solo en el while pudes obtener datos de la bd
                capturarCampo = result.getString("nombre");
                producto = result.getString("nombre");
                precio = result.getString("precio");
            }
            if (capturarCampo.equals("") == false) {
                if (JOptionPane.showConfirmDialog(null,
                        "¿Desea comprar el producto?", "Confirmación",
                        JOptionPane.YES_NO_CANCEL_OPTION)
                        == JOptionPane.YES_OPTION) {
                    numBoleta++;
                    ctxCodigoProducto.setText("");
                    JOptionPane.showMessageDialog(null, "Compra exitosa"
                            + " revisar boleta de venta");
                    boleta();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Código inválido");
            }
            /*
            if(capturarCampo.equals("") == false){
                System.out.println("Ingresaste");
            }*/
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
    }

    public void setPremium() {

        String SQL = "UPDATE  ventas_online.usuarios "
                + "SET tipo_usuario = 'PREMIUM' WHERE usuarios.id = " + id + ";";
        try {
            PreparedStatement pst = objconect.prepareStatement(SQL);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "¡Ya eres Premium!"
                    + "\nPara actualizar tus datos "
                    + "Inicia Sesión nuevamente");
            boletaPremium();
            this.setEnabled(false);
            this.setVisible(false);
            Login.main(null);

        } catch (Exception e) {
            System.out.println("Error de Update -- " + e.getMessage());
        }

    }

    public void boleta() {
        double precioConvertido = Double.parseDouble(precio);
        double descuento;
        double total;
        if (tipo_usuario.equals("PREMIUM")) {
            descuento = precioConvertido * 0.05;
            total = precioConvertido - descuento;
        } else {
            descuento = 0;
            total = precioConvertido;
        }

        Date ahora = new Date();

        System.out.println("BOLETA ELECTRÓNICA\n        N°" + numBoleta
                + "\n-------------------\n" + "  BEST TECHNOLOGY\n"
                + "\nVenta en línea de \nproductos electrónicos\n"
                + "Emisión : " + ahora
                + "\nUsuario :" + usuario
                + "\nCuenta : " + cuenta
                + "\n-------------------"
                + "\nDescripción : " + producto
                + "\nCantidad : 1 "
                + "\nPrecio : " + precio
                + "\n-------------------\n"
                + "Descuento premium : " + descuento
                + "\nTotal pagado : " + total + " \n\n\n");
    }

    public void boletaPremium() {
        Date ahora = new Date();
        numBoleta++;
        System.out.println("BOLETA ELECTRÓNICA\n        N°" + numBoleta
                + "\n-------------------\n" + "  BEST TECHNOLOGY\n"
                + "\nVenta en línea de \nproductos electrónicos\n"
                + "Emisión : " + ahora
                + "\nUsuario :" + usuario
                + "\nCuenta : " + cuenta
                + "\n-------------------"
                + "\nDescripción : Usuario Premium"
                + "\nPrecio : $20"
                + "\n-------------------\n"
                + "\nTotal pagado : $20 \n\n\n");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ctxCodigoProducto = new javax.swing.JTextField();
        btnComprar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnPremium = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        CerrarSecion = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        subMenuInformacion = new javax.swing.JMenuItem();
        subMenuInActualizar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tienda Online");

        jScrollPane1.setBackground(new java.awt.Color(204, 51, 0));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        tblProductos.setBackground(new java.awt.Color(102, 102, 102));
        tblProductos.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblProductos.setForeground(new java.awt.Color(255, 255, 255));
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Realiza tu compra ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Ingresa el código del producto : ");

        btnComprar.setBackground(new java.awt.Color(255, 255, 255));
        btnComprar.setForeground(new java.awt.Color(0, 0, 0));
        btnComprar.setText("🛒");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Hazte Prémium y obtén hasta un 5% de descuento");

        btnPremium.setBackground(new java.awt.Color(255, 255, 255));
        btnPremium.setForeground(new java.awt.Color(0, 0, 0));
        btnPremium.setText("♛");
        btnPremium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPremiumActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Best Tecnology te ofrece:");

        jMenu1.setText("Archivo");

        CerrarSecion.setText("Cerrar seción");
        CerrarSecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarSecionActionPerformed(evt);
            }
        });
        jMenu1.add(CerrarSecion);

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Usuario");

        subMenuInformacion.setText("Información");
        subMenuInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuInformacionActionPerformed(evt);
            }
        });
        jMenu2.add(subMenuInformacion);

        subMenuInActualizar.setText("Actualizar Información");
        subMenuInActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuInActualizarActionPerformed(evt);
            }
        });
        jMenu2.add(subMenuInActualizar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctxCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPremium, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPremium))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ctxCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnComprar)))
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarSecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarSecionActionPerformed
        this.setEnabled(false);
        this.setVisible(false);
        Login obj = new Login();
        obj.setVisible(true);
    }//GEN-LAST:event_CerrarSecionActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        if (JOptionPane.showConfirmDialog(null,
                "¿Desea salir de Sistema?", "Acceso",
                JOptionPane.YES_NO_CANCEL_OPTION)
                == JOptionPane.YES_OPTION) {
            System.out.println("\nsaliste del Login");
            System.exit(0);
        }
    }//GEN-LAST:event_SalirActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        String codigo = ctxCodigoProducto.getText();
        consultarProducto(codigo);
    }//GEN-LAST:event_btnComprarActionPerformed

    private void btnPremiumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPremiumActionPerformed
        if (tipo_usuario.equals("PREMIUM") == false) {
            if (JOptionPane.showConfirmDialog(null,
                    "Ser premium cuesta 20$\n¿Desea continuar?", "Confirmación",
                    JOptionPane.YES_NO_CANCEL_OPTION)
                    == JOptionPane.YES_OPTION) {
                setPremium();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede ejecutar esta acción "
                    + "Ud. ya es usuario Premuim");
        }
    }//GEN-LAST:event_btnPremiumActionPerformed

    private void subMenuInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuInformacionActionPerformed
        JOptionPane.showMessageDialog(null, "Nombre : " + usuario
                + "\nTipo de usuario: " + tipo_usuario
                + "\nCuenta : " + cuenta);

    }//GEN-LAST:event_subMenuInformacionActionPerformed


    private void subMenuInActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuInActualizarActionPerformed
        this.setEnabled(false);
        this.setVisible(false);
        ActualizaDatos.main(null);
    }//GEN-LAST:event_subMenuInActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(SistemaVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CerrarSecion;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnPremium;
    private javax.swing.JTextField ctxCodigoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem subMenuInActualizar;
    private javax.swing.JMenuItem subMenuInformacion;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
