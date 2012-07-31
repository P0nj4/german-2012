package dominio;

import java.sql.ResultSet;
import java.util.ArrayList;
import persistencia.IPersistente;
import persistencia.ManejadorBD;

public class Vehiculo extends utilidades.Observable implements persistencia.IPersistente {

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
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (!this.marca.equals(marca)) {
            this.matricula = matricula;
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
        String sql = "Select * from Vehiculos where borrado = 0";
        if (id != 0) {
            sql += " and vehiculoID =" + this.getid();
        } else {
            if (!this.matricula.isEmpty()) {
                sql += " and vehiculoMatricula = '" + this.matricula + "'";
            }
        }
        return sql;
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) throws Exception {
        this.setMarca(rs.getString("vehiculoMarca"));
        this.setMatricula(rs.getString("vehiculoMatricula"));
        this.setModelo(rs.getString("vehiculoModelo"));
        this.setid(rs.getInt("vehiculoID"));

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
