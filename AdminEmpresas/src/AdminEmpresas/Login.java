package AdminEmpresas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JDialog implements ActionListener {

    Interfaz I1 = new Interfaz();

    JTextField txtNombre;
    JPasswordField txtClave;
    JButton btnAceptar, btnSalir;
    JLabel labelNom, labelPass;
    String user, pass, Nombre, Clave;

    Login() {
        this.setResizable(false);
        JLabel titulo = new JLabel("INICIAR SESION");
        titulo.setFont(new Font("Bell MT", Font.BOLD, 20));
        titulo.setBounds(85, 0, 180, 20);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        super.setBounds(10, 15, 350, 350);
        this.add(titulo);
        setLocationRelativeTo(this);
        this.getContentPane().setBackground(Color.WHITE);
        user = "Admin";
        pass = "123";
        setLayout(null);
        componentes();
    }

    public void componentes() {
        labelNom = new JLabel("Usuario");
        labelNom.setBounds(28, 160, 80, 30);
        labelNom.setHorizontalAlignment(SwingConstants.RIGHT);
        add(labelNom);

        txtNombre = new JTextField();
        txtNombre.setBounds(110, 160, 125, 30);
        add(txtNombre);

        labelPass = new JLabel("Password");
        labelPass.setBounds(28, 195, 80, 30);
        labelPass.setHorizontalAlignment(SwingConstants.RIGHT);
        add(labelPass);

        txtClave = new JPasswordField();
        txtClave.setBounds(110, 195, 125, 30);
        add(txtClave);

        //botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(58, 240, 105, 25);
        add(btnAceptar);
        Image icono = new ImageIcon("Acetar.png").getImage();
        ImageIcon acep = new ImageIcon(icono.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnAceptar.setIcon(acep);
        btnAceptar.addActionListener(this);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(178, 240, 105, 25);
        add(btnSalir);
        Image icono2 = new ImageIcon("x.jpg").getImage();
        ImageIcon sal = new ImageIcon(icono2.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        btnSalir.setIcon(sal);
        btnSalir.addActionListener(this);

        btnSalir.addActionListener(this);

        ImageIcon img = new ImageIcon("Login.jpg");
        JLabel imagen = new JLabel();
        imagen.setBounds(95, 20, 150, 150);
        imagen.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        add(imagen);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Nombre = txtNombre.getText();
        Clave = txtClave.getText();

        if (Nombre.isEmpty() || Clave.isEmpty()) {
            if (e.getSource() == btnAceptar) {
                JOptionPane.showMessageDialog(null, "Ingrese un Usuario y Una Contraseña");
                limpiarDatos();

            }

        } else {
            if (Nombre.equals(user) && Clave.equals(pass)) {
                if (e.getSource() == btnAceptar) {
                    I1.setVisible(true);

                    limpiarDatos();
                    this.dispose();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta");
                limpiarDatos();
            }
        }

        if (e.getSource() == btnSalir) {
            System.exit(0);
        }

    }

    public void limpiarDatos() {
        txtNombre.setText("");
        txtClave.setText("");

    }

    public static void main(String[] args) {
       Login l1 = new Login();
      l1.setVisible(true);

    }

}
