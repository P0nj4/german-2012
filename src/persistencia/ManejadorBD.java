package persistencia;

import java.sql.*;
import java.util.ArrayList;

public class ManejadorBD {

    private Connection conexion;
    private String url;
    private static ManejadorBD instancia;

    public static ManejadorBD getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorBD();
        }
        return instancia;
    }

    private void conectar() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conexion = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("Error de driver.");
        } catch (SQLException e1) {
            System.out.println("Error de conexi�n.");
        }
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexi�n.");
        }
    }

    private void ejecutar(String sql) throws SQLException {
        try {
            conectar();
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);
            st.close();
            desconectar();
        } catch (SQLException e) {
            throw e;
        }
    }

    private ResultSet obtenerResultSet(String sql) {

        ResultSet rs = null;
        try {
            Statement st = conexion.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar sql.\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al ejecutar sql.\n" + e.getMessage());
        }

        return rs;
    }

    public int ultimoid(IPersistente p) {
        int id = -1;
        try {
            conectar();
            if (p.getTableName(true).isEmpty()) {
                return -1;
            }
            String sql = "SELECT max(" + p.getTableName(true).toLowerCase() + "ID) as valor FROM " + p.getTableName(false) + "; ";
            ResultSet rs = this.obtenerResultSet(sql);
            while (rs.next()) {
                id = rs.getInt("valor");
            }
            rs.close();
            desconectar();
        } catch (SQLException e) {
            System.out.println("Error al obtener el proximo oid." + e.getMessage());
        }
        return id;
    }

    public void agregar(IPersistente p) throws SQLException {
        this.ejecutar(p.getInsertSQL());
        int oid = this.ultimoid(p);
        p.setid(oid);
    }

    public void modificar(IPersistente p) throws SQLException {
        this.ejecutar(p.getUpdateSQL());
    }

    public void eliminar(IPersistente p) throws SQLException {
        this.ejecutar(p.getDeleteSQL());
        p.setid(0);
    }

    public void leer(IPersistente p) throws Exception {
        try {
            conectar();
            ResultSet rs = this.obtenerResultSet(p.getSelectSQL());

            while (rs.next()) {
                p.leerDesdeResultSet(rs);
            }
            desconectar();
        } catch (SQLException e) {
            System.out.println("Error al leer objeto.\n" + e.getMessage());
        }
    }

    public ArrayList obtenerTodos(IPersistente p) throws Exception {
        ArrayList ret = new ArrayList();
        try {
            conectar();
            ResultSet rs = this.obtenerResultSet(p.getSelectSQL());

            while (rs.next()) {
                IPersistente aux = p.getNuevo();

                aux.leerDesdeResultSet(rs);
                ret.add(aux);
            }
            desconectar();
        } catch (SQLException e) {
            System.out.println("Error al obtener objetos.\n" + e.getMessage());
        }
        return ret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
