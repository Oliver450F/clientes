/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author olive
 */
public class clientes extends Persona {
    private String nit;
    private int id;
    Conexion cn;
public clientes(){}
    public clientes(String nit, int id) {
        this.nit = nit;
        this.id = id;
    }

    public clientes(String nit, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            
            cn=new Conexion();
            String query="SELECT id_Clientes as id,nombres,apellidos,Direccion,NIT,Telefono,Fecha_Nacimiento FROM clientes;";
            cn.abrir_conexion();
            ResultSet consulta=cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"id","nombres","apellidos","direccion","nit","telefono","nacimiento"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] =new String[7];
            while (consulta.next()){
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("nombres");
            datos[2] = consulta.getString("apellidos");
            datos[3] = consulta.getString("direccion");
            datos[4] = consulta.getString("nit");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);
            
            }
            
            cn.cerrar_conexion();
        }catch(Exception ex){
            
            System.out.print("Error....."+ ex.getMessage());
            
        }
        return tabla;
    }
     public int agregar(){
        int retorno=0;
        
         try{
            PreparedStatement parametro;
            String query="INSERT INTO clientes (nombres,apellidos,Direccion,NIT,Telefono,Fecha_Nacimiento)VALUES(?,?,?,?,?,?);"; 
            cn = new Conexion();
            cn.abrir_conexion();
            parametro =(PreparedStatement) cn.conexionBD.prepareCall(query);
            parametro.setString(1, getNombres());
              parametro.setString(2, getApellidos());
                parametro.setString(3, getDireccion());
                            parametro.setString(4, getNit());
                  parametro.setString(5, getTelefono());
                    parametro.setString(6, getFecha_nacimiento());
                  
                    retorno =parametro.executeUpdate();
                  cn.cerrar_conexion();
             
                  
            //JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            
            System.out.print("Error....."+ ex.getMessage());
        }
       
    
        
        return retorno;
    }

 public int modidficar(){
        int retorno=0;
        
         try{
            PreparedStatement parametro;
            String query="UPDATE clientes SET nombres=?,apellidos=?,Direccion=?,NIT=?,Telefono=?,Fecha_Nacimiento=? WHERE id_Clientes=?"; 
            cn = new Conexion();
            cn.abrir_conexion();
            parametro =(PreparedStatement) cn.conexionBD.prepareCall(query);
            parametro.setString(1, getNombres());
              parametro.setString(2, getApellidos());
                parametro.setString(3, getDireccion());
                            parametro.setString(1, getNit());
                  parametro.setString(4, getTelefono());
                    parametro.setString(5, getFecha_nacimiento());
                    parametro.setInt(6, this.getId());
                  
                    retorno =parametro.executeUpdate();
                  cn.cerrar_conexion();
             
                  
            //JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            
            System.out.print("Error....."+ ex.getMessage());
        }
       
    
        
        return retorno;
    }
    
    public int eliminar(){
        int retorno=0;
        
         try{
            PreparedStatement parametro;
            String query="delete from clientes  WHERE id_Clientes=?"; 
            cn = new Conexion();
            cn.abrir_conexion();
            parametro =(PreparedStatement) cn.conexionBD.prepareCall(query);
           
                    parametro.setInt(1, this.getId());
                  
                    retorno =parametro.executeUpdate();
                  cn.cerrar_conexion();
             
                  
            //JOptionPane.showMessageDialog(null, Integer.toHexString(executar)+ "Reegistro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            
            System.out.print("Error....."+ ex.getMessage());
        }
       
    
        
        return retorno;
    }
    
    
}
