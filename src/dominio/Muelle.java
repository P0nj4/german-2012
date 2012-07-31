package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistencia.IPersistente;
import persistencia.ManejadorBD;

public class Muelle  extends utilidades.Observable implements IPersistente {

    private int id;
    private String nombre;
    private String descripcion;
    private int criterio;
    private ArrayList asignaciones;

    public Muelle() {
        asignaciones = new ArrayList();
    }

    public void agregarNuevaAsignacion(Asignacion a){
        this.asignaciones.add(a);
        notificarObservadores();
    }
    public void eliminarAsignacion(Asignacion a){
        this.asignaciones.remove(a);
        notificarObservadores();
    }
    public ArrayList getAsignaciones() {
        //aca debería ordenar
        if (asignaciones == null) {
            asignaciones = new ArrayList();
        }
        return this.asignaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCriterio() {
        return criterio;
    }

    public void setCriterio(int criterio) {
        this.criterio = criterio;
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
    public String getInsertSQL() {
        // TODO Auto-generated method stub
        return "INSERT INTO Muelles(muelleNombre,muelleDescripcion,criterioID)" + "VALUES('"
                + this.getNombre() + "','" + this.getDescripcion() + "',"
                + this.getCriterio() + ")";
    }

    @Override
    public String getUpdateSQL() {
        // TODO Auto-generated method stub
        return "UPDATE Muelles SET muelleNombre='" + this.getNombre()
                + "', muelleDescripcion='" + this.getDescripcion() + "', criterioID="
                + this.getCriterio() + " WHERE muelleID=" + this.getid();
    }

    @Override
    public String getDeleteSQL() {
        return "UPDATE Muelles SET borrado = 1 where muelleID =" + this.getid();
    }

    @Override
    public String getSelectSQL() {
        String sql = "Select m.* From Muelles m where m.borrado = 0";
        if (this.getid() != 0) {
            sql = sql + " and m.muelleID = " + this.getid();
        }
        return sql;
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) throws Exception {
        try {
            if (nombre == null && descripcion == null) {
                this.setDescripcion(rs.getString("muelleDescripcion"));
                this.setNombre(rs.getString("muelleNombre"));
                this.setid(rs.getInt("muelleID"));                
                this.setCriterio(rs.getInt("criterioID"));
                
            }
        } catch (Exception ex) {
            System.out.println("Error leerDesdeResultSet.\n" + ex.getMessage());
            throw ex;
        }

    }

    @Override
    public IPersistente getNuevo() {
        return new Muelle();
    }

    @Override
    public String getTableName(boolean singular) {
        return (singular ? "muelle" : "Muelles");
    }

    public String toString() {
        return this.nombre + " " + this.id;

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
