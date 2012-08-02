package dominio;

import java.sql.ResultSet;
import java.util.ArrayList;
import persistencia.IPersistente;
import persistencia.ManejadorBD;

public class Vehiculo2 extends utilidades.Observable implements persistencia.IPersistente {

    private int id;
    private String marca;
    private String modelo;
    private String matricula;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (!this.marca.equals(marca)) {
            notificarObservadores();
        }
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (!this.marca.equals(marca)) {
            this.modelo = modelo;
            notificarObservadores();
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (!this.marca.equals(marca)) {
            this.matricula = matricula;
            notificarObservadores();
        }
    }

    @Override
    public void guardar() throws Exception {
        if (this.getid() == 0) {
            ManejadorBD.getInstancia().agregar(this);
        } else {
            ManejadorBD.getInstancia().modificar(this);
        }
    }

    @Override
    public void leer() throws Exception {
        ManejadorBD.getInstancia().leer(this);

    }

    @Override
    public void eliminar() throws Exception {
        ManejadorBD.getInstancia().eliminar(this);
    }

    @Override
    public ArrayList obtenerTodos() throws Exception {
        return ManejadorBD.getInstancia().obtenerTodos(this);
    }

    @Override
    public int getid() {
        // TODO Auto-generated method stub
        return this.id;
    }

    @Override
    public void setid(int valor) {
        // TODO Auto-generated method stub
        this.id = valor;
    }

    @Override
    public String getInsertSQL() {
        return "Insert into Vehiculos (vehiculoMarca,vehiculoMatricula,vehiculoModelo)Values('"
                + this.marca
                + "','"
                + this.matricula
                + "','"
                + this.modelo
                + "')";
    }

    @Override
    public String getUpdateSQL() {
        return "Update Vehiculos set vehiculoMarca ='" + marca + "', vehiculoMatricula='" + matricula + "', vehiculoModelo='" + modelo + "' where vehiculoID =" + this.getid();
    }

    @Override
    public String getDeleteSQL() {
        return "Update Vehiculos set borrado = 1 where vehiculoID =" + this.getid();
        //return "Delete from Vehiculos ";
    }

    @Override
    public String getSelectSQL() {
        //String sql = "Select vehiculoMarca, vehiculoMatricula, vehiculoModelo, vehiculoID  from Vehiculos where borrado = 0";
        String sql = "Select * from Vehiculos ";
        if (id != 0) {
            sql += " and vehiculoID =" + this.getid();
        } 
        return sql;
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) throws Exception {
        /*
        this.setMarca(rs.getString("vehiculoMarca"));
        this.setMatricula(rs.getString("vehiculoMatricula"));
        this.setModelo(rs.getString("vehiculoModelo"));
        this.setid(rs.getInt("vehiculoID"));
         * 
         */
        String tmp =rs.getString(0);
        this.setMarca(rs.getString(0));
        this.setMatricula(rs.getString(1));
        this.setModelo(rs.getString(2));
        this.setid(rs.getInt(3));

    }

    @Override
    public IPersistente getNuevo() {
        // TODO Auto-generated method stub
        return new Vehiculo();
    }

    @Override
    public String getTableName(boolean singular) {
        if (singular) {
            return "vehiculo";
        } else {
            return "Vehiculos";
        }
    }

    public String toString() {
        return this.matricula + " -> " + this.marca + " " + this.modelo;
    }

    @Override
    public int getParentId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setParentId(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
