package ejemplobd.cl;

import ejemplobd.accesobd.Accesobd;
import java.sql.*;
import java.util.*;
import java.sql.Connection;

public class MultiCliente {

    public ArrayList<Cliente> listarClientes() throws SQLException, Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        java.sql.ResultSet rs;
        String sql;
        Accesobd accesobd = new Accesobd();
        Connection conn = accesobd.getConexion();
        Statement stmt = null;
        sql = "SELECT nombre, identificacion "
                + "FROM Cliente ;";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            cliente = new Cliente(rs.getString("nombre"), rs.getString("identificacion"));
            clientes.add(cliente);
        }
        rs.close();
        return clientes;
    }

    public Cliente crear(String pnombre, String pidentificacion)
            throws Exception {
        Cliente cliente = null;
        String sql;
        sql = "INSERT INTO TCliente "
                + "(nombre, identificacion) "
                + "VALUES ('" + pnombre + "', '" + pidentificacion + "');";
        try {
            //aca debe poner el codigo para conectar a la base de datos y ejecutar la consulta.
            cliente = new Cliente(pnombre, pidentificacion);
        } catch (Exception e) {
            throw new Exception("El n�mero de identificaci�n ya est� en el sistema.");
        }
        return cliente;
    }

    public Cliente buscar(String pidentificacion) throws java.sql.SQLException, Exception {
        Cliente cliente = null;
        java.sql.ResultSet rs;
        String sql;
        Accesobd accesobd = new Accesobd();
        Connection conn = accesobd.getConexion();
        Statement stmt = null;
        sql = "SELECT nombre, identificacion "
                + "FROM TCliente "
                + "WHERE identificacion='" + pidentificacion + "';";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            cliente = new Cliente(rs.getString("nombre"), rs.getString("identificacion"));
        } else {
            throw new Exception("El cliente no est� registrado.");
        }
        rs.close();
        return cliente;
    }

    public void actualizar(Cliente pcliente) throws
            java.sql.SQLException, Exception {
        String sql;
        sql = "UPDATE TCliente "
                + "SET nombre='" + pcliente.getNombre() + "' "
                + "WHERE identificacion='" + pcliente.getId() + "';";
        try {
            //aca debe poner el codigo para conectar a la base de datos y ejecutar la consulta.
        } catch (Exception e) {
            throw new Exception("El cliente no est� registrado.");
        }
    }

    public void borrar(Cliente pcliente) throws
            java.sql.SQLException, Exception {
        String sql;
        sql = "DELETE FROM TCliente "
                + "WHERE identificacion='" + pcliente.getId() + "';";
        try {
            //aca debe poner el codigo para conectar a la base de datos y ejecutar la consulta.
        } catch (Exception e) {
            throw new Exception("El cliente tiene cuentas.");
        }
    }
}
