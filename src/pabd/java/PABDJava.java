/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pabd.java;

import java.sql.*;

/*
 *
 * @author 20221074010036
 */

/**
 *
 * @author 20211074010035
 */
public class PABDJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        Connection con = null;
        

        con = new ConnectionFactory().getConnection();
        System.out.println("Connnection OK!");
        //*****CRUD*****
        // CREATE - Inserir dados
        String sql = "insert into customer"
                + " (store_id,first_name,last_name,email,address_id,active)"
                + " values"
                + " (?,?,?,?,?,?)";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, 1);
        pst.setString(2, "Victor");
        pst.setString(3, "Alexandre");
        pst.setString(4, "Victor.alexandre@escolar.ifrn.edu.br");
        pst.setInt(5, 1);
        pst.setInt(6, 1);

        pst.execute();
        pst.close();

    }

}
