package AdminEmpresas;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class datosProductos extends JPanel implements ActionListener {

    //creacion de variables para facturacion
    String Nombre, Codigo;
    int cantidad;
    float precio;

    JTextField txtCodigo, txtNom, txtPrecio, txtCantidad;
    JTextArea txtDescripcion;
    DefaultTableModel modeloProd = new DefaultTableModel();
    JTable tabla;
    JButton btnGuardar, btnAñadir;
    JComboBox proveedor = new JComboBox();

    datosProductos() {
        setLayout(null);
        this.setBounds(30, 50, 345, 265);
        this.setBackground(new Color(190,200,250));
        ImageIcon img = new ImageIcon("Producto.png");
        JLabel imagen = new JLabel();
        imagen.setBounds(383, 10, 150, 75);
        imagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH)));
        add(imagen);
        ImageIcon img2 = new ImageIcon("LogoProduct.PNG");
        JLabel imagen2 = new JLabel();
        imagen2.setBounds(230, 10, 150, 75);
        imagen2.setIcon(new ImageIcon(img2.getImage().getScaledInstance(130, 60, Image.SCALE_SMOOTH)));
        add(imagen2);

        this.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Registro de Productos", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        Componentes();

    }

    public void Componentes() {
        JLabel l1 = new JLabel("Nombre del Producto");
        l1.setBounds(10, 20, 150, 30);
        JLabel l2 = new JLabel("Codigo");
        l2.setBounds(10, 65, 50, 30);
        JLabel l3 = new JLabel("Precio");
        l3.setBounds(10, 112, 50, 30);
        JLabel l4 = new JLabel("Descripcion");
        l4.setBounds(10, 160, 140, 30);
        JLabel l6 = new JLabel("Proveedor");
        l6.setBounds(70, 232, 150, 30);
        JLabel l5 = new JLabel("Cantidad");
        l5.setBounds(10, 232, 50, 30);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);

        txtNom = new JTextField();
        txtNom.setBounds(10, 47, 150, 23);
        add(txtNom);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(10, 90, 120, 23);
        add(txtCodigo);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(10, 138, 100, 23);
        add(txtPrecio);

        txtDescripcion = new JTextArea();
        txtDescripcion.setBounds(10, 185, 140, 50);
        txtDescripcion.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        add(txtDescripcion);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(10, 257, 55, 23);
        add(txtCantidad);

        //combo de provedores
        proveedor.addItem("Sinsa");
        proveedor.addItem("E.Chamorro");
        proveedor.addItem("Dicegsa");
        proveedor.addItem("Monisa");
        proveedor.addItem("Marfil");
        proveedor.setBounds(70, 255, 95, 30);

        add(proveedor);

        //Tabla de productos
        modeloProd.addColumn("Producto");
        modeloProd.addColumn("Codigo");
        modeloProd.addColumn("Precio");
        modeloProd.addColumn("Cantidad");
        modeloProd.addColumn("Proveedor");
        tabla = new JTable(modeloProd);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(178, 85, 358, 320);
        this.add(scroll);

        //Botones
        btnAñadir = new JButton("Añadir");
        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(10, 300, 100, 30);
        add(btnAñadir);
        Image icono3 = new ImageIcon("ProductoCaja.png").getImage();
        ImageIcon añadir = new ImageIcon(icono3.getScaledInstance(23, 24, Image.SCALE_SMOOTH));
        btnAñadir.setIcon(añadir);
        btnAñadir.addActionListener(this);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(10, 335, 103, 30);
        add(btnGuardar);
        Image icono4 = new ImageIcon("IconoGuardar.png").getImage();
        ImageIcon sav = new ImageIcon(icono4.getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        btnGuardar.setIcon(sav);
        btnGuardar.addActionListener(this);

    }

    public void guardarDatos() {
        String[] reg = new String[5];
        reg[0] = txtNom.getText();
        reg[1] = txtCodigo.getText();
        reg[2] = txtPrecio.getText();
        reg[3] = txtCantidad.getText();
        reg[4] = (String) proveedor.getSelectedItem();
        modeloProd.addRow(reg);

    }

    public void limpiarDatos() {
        txtNom.setText("");
        txtCodigo.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtDescripcion.setText(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAñadir) {
            JOptionPane.showMessageDialog(null, "Producto Añadido");
            guardarDatos();
            limpiarDatos();
        }
        if (e.getSource() == btnGuardar) {
            JOptionPane.showMessageDialog(null, "Lista de Productos Guardadas");
            almacenDatosFichero();
            
        }

    }

    public void almacenDatosFichero() {
        try {

            File prod = new File("Productos.txt");
            FileWriter fw = new FileWriter(prod, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < modeloProd.getRowCount(); i++) {
                for (int j = 0; j < modeloProd.getColumnCount(); j++) {
                    bw.write(modeloProd.getValueAt(i, j).toString() + "/");

                }
                bw.newLine();

            }
            bw.close();
            fw.close();

        } catch (IOException ex) {

            Logger.getLogger(datosProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //prueba de muestra de productos
//    public void mostrarProductos() {
//
//       
//
//    }

}
