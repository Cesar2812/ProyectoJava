package AdminEmpresas;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Facturacion extends JInternalFrame implements ActionListener, ItemListener {

    ArrayList<String> listaProd = new ArrayList();//lista de productos

    //variables para calculos monetarios
    int precio1, preciototal, obtenercant;
    float suma = 0, igv = 0, tot = 0;
    float descuento;
    float Predes = 0;

    String[] reg = new String[4];//arreglo de tabla

    ////////////////////////////////////////////////////////////////////////////////////////////
    JComboBox productos;
    JTextField txtNombre, txtCantidad, txtDireccion, txttelefono, txtdocumento, txtprecio, txtdesc;
    JPanel j1, j2;
    DefaultTableModel modeloFact = new DefaultTableModel();
    JTable tabla;
    JCheckBox chxdescuento;
    JButton btnAgregar, btnMostrar, btnGuardar;
    JLabel t, iv, t2;

    public Facturacion() {

        setLayout(null);
        this.setResizable(false);
        this.setClosable(true);
        this.getContentPane().setBackground(Color.WHITE);
        this.setBackground(new Color(120, 180, 195));
        this.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Facturacion", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        ImageIcon img = new ImageIcon("LogoFact.png");
        JLabel imagen = new JLabel();
        imagen.setBounds(400, 10, 150, 75);
        imagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 85, Image.SCALE_SMOOTH)));
        add(imagen);

        //panel de datos del cliente
        j1 = new JPanel();
        j1.setLayout(null);
        j1.setBounds(5, 10, 400, 120);
        j1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Datos del Cliente", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        this.add(j1);

        //panel de productos
        j2 = new JPanel();
        j2.setLayout(null);
        j2.setBounds(5, 150, 400, 250);
        j2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Datos de Productos", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        this.add(j2);

        Componentes();

    }

    public void Componentes() {
        //clientes
        JLabel nom = new JLabel("Cliente");
        nom.setBounds(25, 5, 50, 50);
        j1.add(nom);

        txtNombre = new JTextField();
        txtNombre.setBounds(68, 20, 150, 25);
        j1.add(txtNombre);

        JLabel dir = new JLabel("Direccion");
        dir.setBounds(10, 35, 55, 50);
        j1.add(dir);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(68, 50, 150, 25);
        j1.add(txtDireccion);

        JLabel tel = new JLabel("Telefono");
        tel.setBounds(15, 65, 55, 50);
        j1.add(tel);

        txttelefono = new JTextField();
        txttelefono.setBounds(68, 80, 100, 25);
        j1.add(txttelefono);

        JLabel doc = new JLabel("Documento");
        doc.setBounds(223, 5, 65, 55);
        j1.add(doc);

        txtdocumento = new JTextField();
        txtdocumento.setBounds(289, 20, 100, 25);
        j1.add(txtdocumento);

        //productos facturacion
        JLabel prod = new JLabel("Producto");
        prod.setBounds(10, 10, 55, 55);
        j2.add(prod);

        //combo de los productos
        productos = new JComboBox();
        productos.setBounds(65, 25, 100, 30);
        productos.addItemListener(this);
        j2.add(productos);

        JLabel cant = new JLabel("Cantidad");
        cant.setBounds(10, 50, 55, 55);
        j2.add(cant);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(65, 65, 100, 25);
        j2.add(txtCantidad);

        JLabel preci = new JLabel("Precio");
        preci.setBounds(25, 80, 50, 50);
        j2.add(preci);

        txtprecio = new JTextField();
        txtprecio.setBounds(65, 95, 100, 25);
        j2.add(txtprecio);

        JLabel des = new JLabel("Descuento");
        des.setBounds(220, 5, 65, 55);
        j2.add(des);

        txtdesc = new JTextField();
        txtdesc.setBounds(220, 43, 50, 25);
        j2.add(txtdesc);

        chxdescuento = new JCheckBox("Descuento");
        chxdescuento.setBounds(215, 73, 100, 15);
        j2.add(chxdescuento);
        chxdescuento.addActionListener(this);

        //tabla de facturacion
        modeloFact.addColumn("Producto");
        modeloFact.addColumn("Precio Unitario");
        modeloFact.addColumn("Cantidad");
        modeloFact.addColumn("Importe");
        tabla = new JTable(modeloFact);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 125, 350, 100);
        j2.add(scroll);

        //Botones
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 400, 120, 30);
        add(btnAgregar);
        Image icono2 = new ImageIcon("add.png").getImage();
        ImageIcon sal = new ImageIcon(icono2.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnAgregar.setIcon(sal);
        btnAgregar.addActionListener(this);

        btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(10, 470, 120, 30);
        add(btnMostrar);
        Image icono4 = new ImageIcon("Mostrar.png").getImage();
        ImageIcon most = new ImageIcon(icono4.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnMostrar.setIcon(most);
        btnMostrar.addActionListener(this);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(10, 435, 120, 30);
        add(btnGuardar);
        Image icono3 = new ImageIcon("guardarFact.png").getImage();
        ImageIcon fact = new ImageIcon(icono3.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        btnGuardar.setIcon(fact);
        btnGuardar.addActionListener(this);

        JLabel total = new JLabel("Sub-Total:");
        total.setBounds(270, 400, 100, 30);
        add(total);

        JLabel iva = new JLabel("Iva 15%:");
        iva.setBounds(281, 420, 100, 30);
        add(iva);

        JLabel tot = new JLabel("Total:");
        tot.setBounds(295, 440, 100, 30);
        add(tot);

        t = new JLabel("00.00");
        t.setBounds(335, 400, 100, 30);
        add(t);

        iv = new JLabel("00.00");
        iv.setBounds(335, 420, 100, 30);
        add(iv);

        t2 = new JLabel("00.00");
        t2.setBounds(335, 440, 100, 30);
        add(t2);

    }

    //cargar datos de precio en el textfield
    public void cargarDatos(String elementSelect) {
        for (String cad : listaProd) {
            String reg[] = cad.split("/");
            if (elementSelect.equals(reg[0])) {
                txtprecio.setText(reg[2]);
            }
        }
    }

    public void guardarDatosFactura() {
        reg[0] = (String) productos.getSelectedItem();
        reg[1] = txtprecio.getText();
        reg[2] = txtCantidad.getText();
        reg[3] = Integer.toString(preciototal);
        modeloFact.addRow(reg);

    }

    public void guardarfacturadesc() {
        reg[0] = (String) productos.getSelectedItem();
        reg[1] = txtprecio.getText();
        reg[2] = txtCantidad.getText();
        reg[3] = Float.toString(Predes);
        modeloFact.addRow(reg);
    }

    public void limpiarDatos() {
        txtCantidad.setText("");

    }

    public void limpiarDatos2() {
        txtCantidad.setText("");
        txtdesc.setText("");

    }

    public double redondear(float num) {
        return Math.rint(num * 100) / 100;
    }

    public void calTot() {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            suma = suma + Float.parseFloat(modeloFact.getValueAt(i, 3).toString());
        }
        suma = (float) redondear(suma);
        igv = (float) (0.15 * suma);
        igv = (float) redondear(igv);
        tot = suma + igv;
        tot = (float) redondear(tot);

        //cambiar la etiqueta de los labels
        iv.setText(String.valueOf(igv));
        t2.setText(String.valueOf(tot));
        t.setText(String.valueOf(suma));
    }
//acciones de botonoes

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chxdescuento) {
            descuento = Float.parseFloat(txtdesc.getText());//obtener el valor del descuento
            obtenercant = Integer.parseInt(txtCantidad.getText());//obtener el valor de la cantidad
            precio1 = Integer.parseInt(txtprecio.getText());//obtener el valor del precio
            preciototal = precio1 * obtenercant;//precio total
            Predes = preciototal - (preciototal * (descuento / 100));//precio con descuento
            guardarfacturadesc();
            limpiarDatos2();
            calTot();
            JOptionPane.showMessageDialog(null, "Descuento aplicado");

        } else if (e.getSource() == btnAgregar) {
            obtenercant = Integer.parseInt(txtCantidad.getText());
            precio1 = Integer.parseInt(txtprecio.getText());
            preciototal = precio1 * obtenercant;
            guardarDatosFactura();
            limpiarDatos();

            calTot();
        }
        if (e.getSource() == btnGuardar) {
            JOptionPane.showMessageDialog(null, "Factura Guardada exitosamente");
            Factura();
        }
        if (e.getSource() == btnMostrar) {
            mostrarFactura();
        }

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED) {
            String elemento = (String) productos.getSelectedItem();
            cargarDatos(elemento);
        }

    }

    //metodo para actulizar datos en el combo
    public void actulizarDatos() {
        MostrarDtosCombo h1 = new MostrarDtosCombo();
        h1.start();
    }

    // guardar la factura y crearla en txt
    public void Factura() {

        try {
            File Fact = new File("Factura.txt");
            FileWriter fw = new FileWriter(Fact, true);
            FileWriter fw2 = new FileWriter(Fact, true);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write("----------------------Factura de Compra de Productos------------"
                    + "\nNombre: " + txtNombre.getText()
                    + "\nDireccion: " + txtDireccion.getText() + "         Documento: " + txtdocumento.getText()
                    + "\nTelefono: " + txttelefono.getText()
                    + "\n----------------------Detalle de Compra-------------------------\n"
                    + "\n-------Producto" + "--PUnitario--" + "Cantidad--" + "Importe----\n");
            for (int i = 0; i < modeloFact.getRowCount(); i++) {
                for (int j = 0; j < modeloFact.getColumnCount(); j++) {
                    bw.write("--------" + modeloFact.getValueAt(i, j).toString() + " ");

                }
                bw.newLine();

            }

            fw.write("Sub-Total C$: " + suma
                    + "\nIva C$: " + iv.getText()
                    + "\nTotal a pagar C$: " + t2.getText());

            bw.close();
            fw.close();
        } catch (IOException e) {

        }
    }

    //mostrar factura en pantalla
    public void mostrarFactura() {
        try {
            File archivo = new File("Factura.txt");
            if (!Desktop.isDesktopSupported()) {
                JOptionPane.showMessageDialog(null, "No lo Soporta");
                return;
            }
            Desktop dsk = Desktop.getDesktop();
            if (archivo.exists()) {
                dsk.open(archivo);

            } else {
                JOptionPane.showMessageDialog(null, "Archivo no encontrado");
            }

        } catch (IOException e) {

        }

    }

//metodo para adquirir los datos de la lista de prodcutos en el combo
    class MostrarDtosCombo extends Thread {

        @Override
        public void run() {

            try {

                File prod = new File("Productos.txt");
                FileReader fr = new FileReader(prod);
                BufferedReader br = new BufferedReader(fr);
                String cadena = br.readLine();
                while (cadena != null) {
                    String reg[] = cadena.split("/");
                    productos.addItem(reg[0]);
                    listaProd.add(cadena);
                    cadena = br.readLine();

                }
                br.close();
                fr.close();

            } catch (IOException ex) {

            }

        }

    }

}
