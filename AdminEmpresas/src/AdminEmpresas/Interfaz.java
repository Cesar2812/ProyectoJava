package AdminEmpresas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

public class Interfaz extends JFrame implements ActionListener {

    JDesktopPane dsk = new JDesktopPane();//panel de escritorio
    Facturacion jiFact = new Facturacion();//JInternalFrame de factura
    Planilla jiPla=new Planilla();//Jinternal de planilla

    JToolBar barra, barradown;//barra de herramientas
    JButton btnguardar, btneditar, btnSalir, btnMini;
    JMenuBar barraMenu = new JMenuBar();//agregar una barra de menu
    JMenu mnuFile, mnuTool, mnuProcess;//agregar opciones al menu
    JMenuItem jmnSave, jmnExit, jmnFacturacion, jmnPlanilla;
    datosEmpleados pestEmp = new datosEmpleados();
    datosProductos pestProd = new datosProductos();
    JTabbedPane pestaña = new JTabbedPane();
    JTabbedPane pestaña2 = new JTabbedPane();
    Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();

    public Interfaz() {
        setLayout(null);
        setTitle("Administracion de Empresas");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setContentPane(dsk);
        this.setUndecorated(true);
        dsk.setBackground(Color.WHITE);
        Image admon = new ImageIcon("admon.png").getImage();
        ImageIcon admonScale = new ImageIcon(admon.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        this.setIconImage(admon);

        ///////////////////BarraDEHERRAMIENTAS
        barra = new JToolBar();
        barra.addSeparator();
        barra.setFloatable(false);
        barra.setBorder(new EtchedBorder());
        barra.setBackground(new Color(120, 180, 195));
        barra.setOrientation(JToolBar.HORIZONTAL);
        barra.setBounds(0, 0, 2048, 55);
        dsk.add(barra);
        componentesBarra();
        Menu();
        //barra de tareas
        componentesBarraTarea();

        ///pestañas paracontrol de negocio seccion de empleados
        Image icono = new ImageIcon("Empleados2.png").getImage();
        ImageIcon emp = new ImageIcon(icono.getScaledInstance(35, 30, Image.SCALE_SMOOTH));
        
        pestaña.addTab(" ", emp, pestEmp);
       
        
        //pestaña de productos
        Image icono2 = new ImageIcon("Producto2.png").getImage();
        ImageIcon prod = new ImageIcon(icono2.getScaledInstance(35, 30, Image.SCALE_SMOOTH));
        pestaña.addTab("", prod, pestProd);
        pestaña.setBounds(5, 55, 555, 450);
  
        dsk.add(pestaña);

        //facturacion
        jiFact.setBounds(5, 55, 560, 550);
        dsk.add(jiFact);
        
        //PLANILLA
        jiPla.setBounds(5,55,560,550);
        dsk.add(jiPla);

    }

    public void componentesBarra() {
        btnguardar = new JButton("");
        barra.add(btnguardar);
        btnguardar.setBackground(Color.WHITE);
        Image icono = new ImageIcon("Save2.png").getImage();
        ImageIcon guardar = new ImageIcon(icono.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnguardar.setIcon(guardar);

        btneditar = new JButton("");
        btneditar.setBackground(Color.WHITE);
        barra.add(btneditar);
        Image icono2 = new ImageIcon("Editar.png").getImage();
        ImageIcon editar = new ImageIcon(icono2.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btneditar.setIcon(editar);

        btnSalir = new JButton("");
        barra.add(btnSalir);
        btnSalir.setBackground(Color.WHITE);
        Image icono3 = new ImageIcon("salir.png").getImage();
        ImageIcon salir = new ImageIcon(icono3.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnSalir.setIcon(salir);
        btnSalir.addActionListener(this);
        
        btnMini = new JButton();
        barra.add(btnMini);
        btnMini.setBackground(Color.WHITE);
        Image mini = new ImageIcon("Minimizar.png").getImage();
        ImageIcon miniScale = new ImageIcon(mini.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnMini.setIcon(miniScale);
        btnMini.addActionListener(this);

    }

    public void Menu() {
        this.setJMenuBar(barraMenu);
        barraMenu.setBackground(Color.WHITE);
        mnuFile = new JMenu("File");
        mnuFile.setForeground(Color.BLACK);
        //mnuFile.addSeparator();
        barraMenu.add(mnuFile);

        mnuTool = new JMenu("Tool");
        barraMenu.add(mnuTool);
        mnuTool.setForeground(Color.BLACK);

        mnuProcess = new JMenu("Process");
        barraMenu.add(mnuProcess);
        mnuProcess.setForeground(Color.BLACK);

        /////opciones de Menu
        jmnSave = new JMenuItem("Guardar");
        Image icono = new ImageIcon("save.png").getImage();
        ImageIcon save = new ImageIcon(icono.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        jmnSave.setIcon(save);
        mnuFile.add(jmnSave);

        jmnExit = new JMenuItem("Salir");
        Image icono2 = new ImageIcon("exit.png").getImage();
        ImageIcon exit = new ImageIcon(icono2.getScaledInstance(25, 20, Image.SCALE_SMOOTH));
        jmnExit.setIcon(exit);
        mnuFile.add(jmnExit);
        jmnExit.addActionListener(this);

        //boton para la facturacion
        jmnFacturacion = new JMenuItem("Facturar");
        mnuProcess.add(jmnFacturacion);
        Image icono3 = new ImageIcon("Facturar.png").getImage();
        ImageIcon fac = new ImageIcon(icono3.getScaledInstance(30, 25, Image.SCALE_SMOOTH));
        jmnFacturacion.setIcon(fac);
        jmnFacturacion.addActionListener(this);

        //boton para la planilla
        jmnPlanilla = new JMenuItem("Planilla");
        mnuProcess.add(jmnPlanilla);
        Image icono4 = new ImageIcon("Planilla.png").getImage();
        ImageIcon pla = new ImageIcon(icono4.getScaledInstance(25, 20, Image.SCALE_SMOOTH));
        jmnPlanilla.setIcon(pla);
        jmnPlanilla.addActionListener(this);

    }

    public void componentesBarraTarea() {
        barradown = new JToolBar();
        barradown.setLayout(new BorderLayout());
        barradown.setFloatable(false);
        barradown.setBackground(new Color (200, 200, 250));
        barradown.setOrientation(JToolBar.HORIZONTAL);
        barradown.setBounds(0, dime.height - 83, dime.width, 55);
        /////fecha y hora
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy/HH:MM:ss");
        String date = fecha.format(new Date());
        JLabel formatDate = new JLabel(date);
        barradown.add("East", formatDate);
        this.add(barradown);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSalir) {
            System.exit(0);

        }
        if (e.getSource() == jmnExit) {
            System.exit(0);

        }
        if (e.getSource() == jmnFacturacion) {
            jiFact.setVisible(true);
            jiFact.actulizarDatos();

        }
        
        if(e.getSource()==jmnPlanilla){
            jiPla.setVisible(true);
            jiPla.actulizarDatos();
        }
        if(e.getSource() == btnMini){
           this.setState(1);
       }
    }

}
