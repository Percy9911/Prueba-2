import javax.swing.JOptionPane;

public class LoginDialog {
    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        // Validar la información del usuario aquí

        JOptionPane.showMessageDialog(null, "Bienvenido, " + username + "!");
    }
}