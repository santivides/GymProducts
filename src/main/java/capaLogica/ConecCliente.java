/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

import capaDatos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santiago Vides
 */

//coneccion de los datos del cliente con la DB
public class ConecCliente {
    private conexion SQL = new conexion();
    private Connection con = SQL.conectar();
    String sql = "";
    
    //funcion para mostrar un cliente
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        
        String[] titulos = {"ID", "Nombre", "Apellidos","DNI", "RUC", "Edad", "Sexo", "Telefono", "direccion"};
        
        modelo = new DefaultTableModel(null, titulos);
        String[] registro = new String[9];
        
        sql = ("BuscarCliente' "+ buscar + "'");
        
        try{
            //El statement recoge los resultados de la consulta
            Statement st = con.createStatement(); 
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registro[0] = rs.getString(1);//guarda el primer resultado del procedimiento
                registro[1] = rs.getString(2);
                registro[2] = rs.getString(3);
                registro[3] = rs.getString(4);
                registro[4] = rs.getString(5);
                registro[5] = rs.getString(6);
                registro[6] = rs.getString(7);
                registro[7] = rs.getString(8);
                registro[8] = rs.getString(9);
                modelo.addRow(registro); //agregar la fila a la columna de clientes  
            }
            
            return modelo;
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
    //insertar nuevo cliente
    public boolean insertar(Cliente dts){
        sql = ("{call GuardarCliente(?,?,?,?,?,?,?,?)}");
        
        try{
            PreparedStatement pst = con.prepareCall(sql);
            pst.setString(1, dts.getId());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getNombre());
            pst.setString(4, dts.getApellidos());
            pst.setString(5, dts.getDNI());
            pst.setString(6, dts.getRUC());
            pst.setInt(7, dts.getEdad());
            pst.setString(8, dts.getSexo());
            pst.setString(9, dts.getTelefono());
            pst.setString(9, dts.getDireccion());
            
            int n = pst.executeUpdate();
            
            if(n != 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    //eliminar cliente
    public boolean eliminar (Cliente dts){
        sql = ("{call EliminarCliente(?)}");
        
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dts.getId());
            
            int n = pst.executeUpdate();
            
            if(n != 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    //generar el id del cliente
    public int generarIdCliente(){
        String SQL = ("select max(Id) as id from Clientes");
        int cod = 0;
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if(rs.next()){
                cod = rs.getInt("Id")+1;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return cod;
    }
}
