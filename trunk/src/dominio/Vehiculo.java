package dominio;

import java.sql.ResultSet;
import java.util.ArrayList;

import persistencia.IPersistente;
import persistencia.ManejadorBD;
import utilidades.Observable;

public class Vehiculo extends Observable implements IPersistente {

    private int id;
    private String marca;
    private String matricula;
    private String modelo;

    public String getMarca() {
        return marca;        
    }

    public void setMarca(String marca) {
        this.marca = marca;
        this.notificarObservadores();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
       this.notificarObservadores();
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
        this.notificarObservadores();
    }

    @Override
    public int getid() {
        return id;
    }

    @Override
    public void setid(int valor) {
        this.id = valor;

    }

    @Override
    public void guardar(int parentID) throws Exception {
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

    }

    @Override
    public String getSelectSQL() {
        String sql = "Select m.* From Vehiculos m where m.borrado = 0";
        if (this.getid() != 0) {
            sql = sql + " and m.vehiculoID = " + this.getid();
        } else {
            if (matricula != null && !matricula.isEmpty()) {
                sql = sql + " and m.vehiculoMatricula = '" + this.matricula + "'";
            }
        }

        return sql;
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) throws Exception {
        try {

            this.setMarca(rs.getString("vehiculoMarca"));
            this.setMatricula(rs.getString("vehiculoMatricula"));
            this.setModelo(rs.getString("vehiculoModelo"));
            this.setid(rs.getInt("vehiculoID"));

        } catch (Exception ex) {
            System.out.println("Error leerDesdeResultSet.\n" + ex.getMessage());
            throw ex;
        }

    }

    @Override
    public IPersistente getNuevo() {
        return new Vehiculo();
    }

    @Override
    public String getTableName(boolean singular) {
        return (singular ? "muelle" : "Muelles");
    }

    public String toString() {
        return this.matricula + " " + this.marca + " " + this.modelo;

    }


}
