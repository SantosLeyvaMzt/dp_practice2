import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataForm extends JFrame {
    private JPanel pnlDistribuitors;
    private JLabel lblName;
    private JTextField txtName;
    private JTextField txtLastName2;
    private JTextField txtLastName;
    private JLabel lblLastName;
    private JLabel lblLastName2;
    private JLabel lblPanelDistribuidor;
    private JLabel lblCalle;
    private JTextField txtStreet;
    private JTextField txtNumber;
    private JLabel lblNumero;
    private JLabel lblColonia;
    private JTextField txtNeighborhood;
    private JButton btnGuardar;
    private JButton btnConsultar;
    private JTextField txtId;
    private JLabel lblId;
    private JMenuBar menuBar;
    private JMenu archivo;
    private JMenuItem salir;

    public DataForm(){
        super("DISTRIBUIDORES");
        setContentPane(pnlDistribuitors);

        menuBar = new JMenuBar();
        archivo = new JMenu("Archivo");
        salir   = new JMenuItem("Salir");

        menuBar.add(archivo);
        archivo.add(salir);

        setJMenuBar(menuBar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registerDistributor();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDistributor();
            }
        });
    }

    private void getDistributor() {
        JOptionPane.showMessageDialog(null, "Botón CONSULTAR!!");
    }

    private void registerDistributor() throws SQLException {
        //Distributor
        String idDistributor = txtId.getText();
        //Person
        String name         = txtName.getText();
        String lastName     = txtLastName.getText();
        String lastName2    = txtLastName2.getText();
        //Address
        String street       = txtStreet.getText();
        //int number        = Integer.parseInt(txtNumber.getText());
        String number       = txtNumber.getText();
        String neighborhood = txtNeighborhood.getText();

        //Instancia a la clase 'ModelDB'.
        ModelDB modelDB = new ModelDB();

        if( idDistributor.isEmpty() || name.isEmpty() || lastName.isEmpty() || lastName2.isEmpty()
                || street.isEmpty() || number.isEmpty() || neighborhood.isEmpty() ){
            JOptionPane.showMessageDialog( this,
                    "Por favor capture todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }else {
            ResultSet resultSet = modelDB.InsertData( idDistributor, name, lastName, lastName2, street, number, neighborhood );

            if (resultSet != null){
                System.out.println("Registro guardado con éxito!!");
            }else {
                System.out.println("Error al guardar");
            }
        }
    }
}
