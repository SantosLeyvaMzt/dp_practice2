import javax.swing.*;
import java.sql.*;

public class ModelDB {
    private String dataBase;
    private String userDB;
    private String dbUrl;

    public ModelDB() {
        try {
            dataBase    = "pruebas_dp";
            userDB      = "root";
            dbUrl       = "jdbc:mysql://localhost:3306/";
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet InsertData(String idDistributor, String name, String lastName, String lastName2,
                                String street, String number, String neighborhood) throws SQLException {

        try {
            Connection connection   = DriverManager.getConnection(dbUrl+dataBase, userDB, "");
            Statement statement     = connection.createStatement();
            String sqlQuery         = "CALL PROC_AGREGARDISTRIBUIDOR( '" + idDistributor + "', '" + name + "', '" + lastName + "', '" + lastName2
                    + "', '" + street + "', '" + number + "', '" + neighborhood +"' );";

            PreparedStatement preparedStatement = connection.prepareStatement( sqlQuery );
            ResultSet resultSet = preparedStatement.executeQuery();

            statement.close();
            connection.close();

            return resultSet;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error :( \n " + e);

        }

        return null;
    }
}