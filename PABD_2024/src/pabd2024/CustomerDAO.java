/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pabd2024;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author 20221074010036
 */
public class CustomerDAO {
    private Connection con;

    public CustomerDAO() throws java.sql.SQLException {
        this.con = new ConnectionFactory().getConnection();
        System.out.println("Conexão OK!");
    }

    public void insertCustomer(Customer c) throws java.sql.SQLException {
        String sql = "insert into customer"
                + " (store_id, first_name, last_name, email, address_id, active)"
                + " values"
                + " (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, c.getStore_id());
        pst.setString(2, c.getFirst_name());
        pst.setString(3, c.getLast_name());
        pst.setString(4, c.getEmail());
        pst.setInt(5, c.getAddress_id());
        pst.setInt(6, c.getActive());

        pst.execute();
        pst.close();
        System.out.println("Insert OK!");
    }

    public void deleteCustomer(int id) throws java.sql.SQLException {
        String sql = "delete from customer where customer_id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
        pst.close();
        System.out.println("Delete OK!");
    }

    public void updateCustomer(int id, int store_id, String first_name, String last_name, String email, int address_id, int active) throws java.sql.SQLException {
        String sql = "update customer"
                + " set store_id=?, first_name=?, last_name=?, email=?, address_id=?, active=?"
                + " where customer_id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setInt(1, store_id);
        pst.setString(2, first_name);
        pst.setString(3, last_name);
        pst.setString(4, email);
        pst.setInt(5, address_id);
        pst.setInt(6, active);
        pst.setInt(7, id);

        pst.execute();
        pst.close();
        System.out.println("Update OK!");
    }

    public void showCustomers() throws java.sql.SQLException {
        Statement st = con.createStatement();

        String query = "select * from customer"
                + " order by customer_id desc"
                + " limit 5";

        ResultSet rs = st.executeQuery(query);

        ResultSetMetaData md = rs.getMetaData();
        int col = md.getColumnCount();

        System.out.println("Tabela: " + md.getTableName(1));
        for (int i = 1; i <= col; i++) {
            System.out.print(md.getColumnName(i) + "\t");
        }
        System.out.println("");

        while (rs.next()) {
            for (int i = 1; i <= col; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println("");
        }

        st.close();
    }
    
    public List<Customer> getCustomers () throws SQLException {
        List<Customer> lista = new ArrayList<Customer>();
        Statement st = con.createStatement();

        String query = "select * from customer"
                + " order by customer_id desc";

        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()){
            lista.add(new Customer(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getTimestamp(8),
                    rs.getTimestamp(9)
            ));
        }
        return lista;
    }
}
