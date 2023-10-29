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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Planilla extends JInternalFrame implements ActionListener, ItemListener {

    ArrayList<String> listaEmp = new ArrayList();//lista de Empleados

    //creacion de variables para calculos
    float salarioBruto, salarioNeto;
    int horasTrabajadas;
    float tasaDeduccion = (float) 0.10;
    float deduccion;

    JPanel j1;
    JTable tabla;
    JComboBox empleados;
    DefaultTableModel modeloPlanilla = new DefaultTableModel();
    JTextField txtSalario, txtPuesto;
    JButton btnGuardar, btnMostrar, btnAgregar;

    Planilla() {
        setLayout(null);
        this.setBackground(new Color(120, 180, 195));
        this.setResizable(false);
        this.setClosable(true);
        this.getContentPane().setBackground(Color.WHITE);
        this.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Planilla de Empleados", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        ImageIcon img = new ImageIcon("planilla2.png");
        JLabel imagen = new JLabel();
        imagen.setBounds(440, -20, 180, 180);
        imagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        add(imagen);

        j1 = new JPanel();
        j1.setLayout(null);
        j1.setBounds(5, 20, 400, 120);
        j1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Datos del Empleado", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
        this.add(j1);

        componentes();
    }

    public void componentes() {

        //Textfield y labels
        JLabel salario = new JLabel("Salario: ");
        salario.setBounds(30, 10, 100, 50);
        j1.add(salario);

        txtSalario = new JTextField();
        txtSalario.setBounds(80, 25, 100, 20);
        j1.add(txtSalario);

        JLabel puesto = new JLabel("Puesto: ");
        puesto.setBounds(30, 50, 100, 50);
        j1.add(puesto);

        txtPuesto = new JTextField();
        txtPuesto.setBounds(80, 65, 100, 20);
        j1.add(txtPuesto);

        JLabel nombresEmpleado = new JLabel("Nombres: ");
        nombresEmpleado.setBounds(200, 45, 100, 20);
        j1.add(nombresEmpleado);

        //Botones
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(170, 465, 120, 30);
        btnGuardar.addActionListener(this);
        Image icono2 = new ImageIcon("IconoGuardar.png").getImage();
        ImageIcon most2 = new ImageIcon(icono2.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnGuardar.setIcon(most2);

        add(btnGuardar);

        btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(295, 465, 120, 30);
        btnMostrar.addActionListener(this);
        Image icono = new ImageIcon("mostPla.png").getImage();
        ImageIcon most = new ImageIcon(icono.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnMostrar.setIcon(most);
        add(btnMostrar);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(420, 465, 120, 30);
        btnAgregar.addActionListener(this);
        Image icono1 = new ImageIcon("addemp.png").getImage();
        ImageIcon most1 = new ImageIcon(icono1.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btnAgregar.setIcon(most1);
        add(btnAgregar);

        //ComboBox
        empleados = new JComboBox();
        empleados.setBounds(260, 43, 100, 30);
        empleados.addItemListener(this);
        j1.add(empleados);

        //Tabla
        modeloPlanilla.addColumn("Nombre");
        modeloPlanilla.addColumn("Puesto");
        modeloPlanilla.addColumn("Salario Bruto");
        modeloPlanilla.addColumn("Deducciones");
        modeloPlanilla.addColumn("Salario Neto");
        tabla = new JTable(modeloPlanilla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 150, 530, 312);
        add(scroll);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            salarioBruto = Float.parseFloat(txtSalario.getText());
            deduccion = salarioBruto * tasaDeduccion;
            salarioNeto = salarioBruto - deduccion;

            guardarDatosEmpleados();

        }
        if (e.getSource() == btnGuardar) {
            JOptionPane.showMessageDialog(null, "Planilla Guardada exitosamente");
            planilla();

        }
        if (e.getSource() == btnMostrar) {
            mostrarPlanilla();

        }

    }

    //metodo del combo
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String elemento = (String) empleados.getSelectedItem();
            cargarDatos(elemento);
        }

    }

    //cargar datos al seleccionar el nombre
    public void cargarDatos(String elementSelect) {
        for (String cad : listaEmp) {
            String reg[] = cad.split("/");
            if (elementSelect.equals(reg[0])) {
                txtPuesto.setText(reg[4]);
                txtSalario.setText(reg[3]);
            }
        }
    }

    public void guardarDatosEmpleados() {
        String[] reg = new String[6];
        reg[0] = (String) empleados.getSelectedItem();
        reg[1] = txtPuesto.getText();
        reg[2] = txtSalario.getText();
        reg[3] = Float.toString(deduccion);
        reg[4] = Float.toString(salarioNeto);
        modeloPlanilla.addRow(reg);

    }

    //actualizar datos en el combo
    public void actulizarDatos() {
        MostrarDtosCombo h1 = new MostrarDtosCombo();
        h1.start();
    }

    // guardar la Planilla y crear txt
    public void planilla() {

        try {
            File Fact = new File("Planilla.txt");
            FileWriter fw = new FileWriter(Fact, true);
            BufferedWriter bw = new BufferedWriter(fw);
            fw.write("\n------------------Planilla de Empleados----------"
                    + "\n-Nombre-Puesto-SalarioBruto-Deducciones-Salario Neto--\n");
            for (int i = 0; i < modeloPlanilla.getRowCount(); i++) {
                for (int j = 0; j < modeloPlanilla.getColumnCount(); j++) {
                    bw.write("-" + modeloPlanilla.getValueAt(i, j).toString() + "");

                }
                bw.newLine();

            }

            bw.close();
            fw.close();
        } catch (IOException e) {

        }
    }

    public void mostrarPlanilla() {
        try {
            File archivo = new File("Planilla.txt");
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

    //mostrar datos en el combo hilo
    class MostrarDtosCombo extends Thread {

        @Override
        public void run() {

            try {

                File prod = new File("Empleados.txt");
                FileReader fr = new FileReader(prod);
                BufferedReader br = new BufferedReader(fr);
                String cadena = br.readLine();
                while (cadena != null) {
                    String reg[] = cadena.split("/");
                    empleados.addItem(reg[0]);
                    listaEmp.add(cadena);
                    cadena = br.readLine();

                }
                br.close();
                fr.close();

            } catch (IOException ex) {

            }

        }

    }

}
