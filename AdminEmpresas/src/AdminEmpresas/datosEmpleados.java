package AdminEmpresas;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class datosEmpleados extends JPanel implements ActionListener {

    //Componentes graficos
    JTextField txtnom, txtcodigo, txtsal, txtedad;
    DefaultTableModel modeloEmp = new DefaultTableModel();
    JButton btnGuardar, btnAñadir;
    JComboBox cargos = new JComboBox();
    JTable tabla;

    datosEmpleados() {
        setLayout(null);
        this.setBounds(30, 50, 345, 265);
        this.setBackground(new Color(190,200,250));
        ImageIcon img = new ImageIcon("Empleados.png");
        JLabel imagen = new JLabel();
        imagen.setBounds(390,0,180,180);
        imagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
        add(imagen);
        this.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Registro de Empleados", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        Componentes();
    }

    public void Componentes() {
        JLabel l1 = new JLabel("Nombre:");
        l1.setBounds(10, 30, 50, 30);
        JLabel l2 = new JLabel("Edad:");
        l2.setBounds(27, 70, 50, 30);
        JLabel l3 = new JLabel("Codigo:");
        l3.setBounds(17, 110, 90, 30);
        JLabel l4 = new JLabel("Salario:");
        l4.setBounds(17, 150, 50, 30);

        JLabel l5 = new JLabel("Cargo:");
        l5.setBounds(175, 105, 50, 30);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);

        txtnom = new JTextField();
        txtnom.setBounds(60, 35, 145, 23);
        add(txtnom);

        txtedad = new JTextField();
        txtedad.setBounds(60, 75, 100, 23);
        add(txtedad);

        txtcodigo = new JTextField();
        txtcodigo.setBounds(60, 115, 100, 23);
        add(txtcodigo);

        txtsal = new JTextField();

        txtsal.setBounds(60, 155, 100, 23);
        add(txtsal);

        //botones
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(215, 70, 103, 30);
        add(btnGuardar);
        Image icono = new ImageIcon("IconoGuardar.png").getImage();
        ImageIcon calcular = new ImageIcon(icono.getScaledInstance(18, 18, Image.SCALE_SMOOTH));
        btnGuardar.setIcon(calcular);
        btnGuardar.addActionListener(this);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(215, 32, 100, 30);
        add(btnAñadir);
        Image icono3 = new ImageIcon("añadir.png").getImage();
        ImageIcon añadir = new ImageIcon(icono3.getScaledInstance(23, 24, Image.SCALE_SMOOTH));
        btnAñadir.setIcon(añadir);
        btnAñadir.addActionListener(this);

        //combo de cargos
        cargos.addItem("Analista");
        cargos.addItem("Contador");
        cargos.addItem("Programador");
        cargos.addItem("Secretaria");
        cargos.addItem("Conserje");
        cargos.setBounds(215, 105, 120, 30);

        add(cargos);

        //tabla de empleados
        modeloEmp.addColumn("Nombre");
        modeloEmp.addColumn("Edad");
        modeloEmp.addColumn("Codigo");
        modeloEmp.addColumn("Salario");
        modeloEmp.addColumn("Cargo");
        tabla = new JTable(modeloEmp);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 195, 525, 210);
        this.add(scroll);

    }

    //logica de almacen de datos
    public void guardarDatos() {
        String[] reg = new String[5];
        reg[0] = txtnom.getText();
        reg[1] = txtedad.getText();
        reg[2] = txtcodigo.getText();
        reg[3] = txtsal.getText();
        reg[4] = (String) cargos.getSelectedItem();
        modeloEmp.addRow(reg);
    }

    public void limpiarDatos() {
        txtnom.setText("");
        txtedad.setText("");
        txtcodigo.setText("");
        txtsal.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAñadir) {
            guardarDatos();
            limpiarDatos();
            JOptionPane.showMessageDialog(null, "Empleado añadido");

        }
        if (e.getSource() == btnGuardar) {
            JOptionPane.showMessageDialog(null, "Lista de Empleados Guardada");
            almacenDatosFichero();
        }

    }

    public void almacenDatosFichero() {
        try {
            FileWriter fw = new FileWriter("Empleados.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < modeloEmp.getRowCount(); i++) {
                for (int j = 0; j < modeloEmp.getColumnCount(); j++) {
                    bw.write(modeloEmp.getValueAt(i, j).toString() + "/");

                }
                bw.newLine();

            }
            bw.close();
            fw.close();

        } catch (IOException ex) {

            Logger.getLogger(datosEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
