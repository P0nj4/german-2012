package dominio;

import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import persistencia.IPersistente;
import persistencia.ManejadorBD;

public class Asignacion implements IPersistente {

    private int id;
    private Date fechaDeCreacion;
    private Date fechaInicioDescarga;
    private Date fechaFinDescarga;
    private int prioridad;
    private long pesoMercancia;
    private Vehiculo vehiculo;
    private int parentId;
    private Usuario usuario;
    private int estado;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaDeCreacion() {
        if (fechaDeCreacion == null) {
            fechaDeCreacion = new Date();
        }
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Date getFechaInicioDescarga() {
        return fechaInicioDescarga;
    }

    public void setFechaInicioDescarga(Date fechaInicioDescarga) {
        this.fechaInicioDescarga = fechaInicioDescarga;
    }

    public Date getFechaFinDescarga() {
        return fechaFinDescarga;
    }

    public void setFechaFinDescarga(Date fechaFinDescarga) {
        this.fechaFinDescarga = fechaFinDescarga;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public long getPesoMercancia() {
        return pesoMercancia;
    }

    public void setPesoMercancia(long pesoMercancia) {
        this.pesoMercancia = pesoMercancia;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int getid() {
        return this.id;
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
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaCreacion = utilidades.Fechas.fechaToLongString(this.getFechaDeCreacion());
        String fechaini = "";
        String fechafin = "";
        if (getFechaInicioDescarga() != null) {
            fechaini = utilidades.Fechas.fechaToLongString(this.getFechaInicioDescarga());
        }
        if (getFechaFinDescarga() != null) {
            fechafin = utilidades.Fechas.fechaToLongString(this.getFechaFinDescarga());
        }
        return "Insert into Asignaciones (asignacionFechaCreacion, asignacionPrioridad, asignacionPesoMercancia, asignacionFechaInicioDescarga, asignacionFechaFinDescarga, vehiculoID, muelleID, usuarioID, estadoDeAsignacionID) Values ("
                + "'"
                + fechaCreacion
                + "',"
                + this.prioridad
                + ","
                + this.pesoMercancia
                + ", "
                + (this.getFechaInicioDescarga() == null ? "null" : "'"
                + fechaini + "'")
                + ", "
                + (this.getFechaFinDescarga() == null ? "null" : "'" + fechafin
                + "'")
                + ", "
                + this.getVehiculo().getid()
                + ", "
                + this.getParentId()
                + ","
                + this.getUsuario().getid()
                + ", " + this.estado + ")";
    }

    @Override
    public String getUpdateSQL() {

        String fechaCreacion = utilidades.Fechas.fechaToLongString(this.getFechaDeCreacion());
        String fechaini = "";
        String fechafin = "";
        if (getFechaInicioDescarga() != null) {
            fechaini = utilidades.Fechas.fechaToLongString(this.getFechaInicioDescarga());
        }
        if (getFechaFinDescarga() != null) {
            fechafin = utilidades.Fechas.fechaToLongString(this.getFechaFinDescarga());
        }

        String sql = "UPDATE Asignaciones "
                + "   SET asignacionPrioridad = "
                + this.getPrioridad()
                + " ,asignacionFechaCreacion = '"
                + fechaCreacion + "' ,asignacionPesoMercancia = "
                + this.getPesoMercancia();
        if (fechaini.length() > 0) {
            sql += "      ,asignacionFechaInicioDescarga =  '" + fechaini + "' ";
        }
        if (fechafin.length() > 0) {
            sql += "      ,asignacionFechaFinDescarga = '" + fechafin + "' ";
        }
        sql += "      ,vehiculoID =  " + this.vehiculo.getid()
                + "      ,muelleID = " + this.getParentId()
                + "      ,usuarioID = " + this.usuario.getid()
                + "      ,estadoDeAsignacionID = " + estado
                + " Where Asignaciones.asignacionID = " + this.getid();

        return sql;
    }

    @Override
    public String getDeleteSQL() {
        return "UPDATE Asignaciones SET borrado = 1  Where Asignaciones.asignacionID = "
                + this.getid();
    }

    @Override
    public String getSelectSQL() {
        /*String sql = "SELECT * "
        + "FROM Asignaciones a "
        + "inner join Usuarios u on u.usuarioID = a.usuarioID "
        + "inner join TipoUsuarios tp on tp.tipoUsuarioID = u.tipoUsuarioID "
        + "inner join Vehiculos v on v.vehiculoID = a.vehiculoID "
        + "inner join EstadoDeAsignaciones ea on ea.estadoDeAsignacionID = a.estadoDeAsignacionID where a.borrado = 0";*/

        String sql = "Select * from Asignaciones a, Usuarios u Vehiculos v"
                + "where "
                + "a.usuarioID = u.usuarioID and "
                + "a.vehiculoID = v.vehiculoID and "                
                + "a.borrado = 0";
        if (parentId != 0) {
            sql += " and a.muelleID = " + this.parentId;
        }
        return sql;
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) throws Exception {        
        this.setEstado(rs.getInt("estadoDeAsignacionID"));
        
        this.setFechaDeCreacion(rs.getDate("asignacionFechaCreacion"));
        this.setFechaInicioDescarga(rs.getDate("asignacionFechaInicioDescarga"));
        this.setFechaFinDescarga(rs.getDate("asignacionFechaFinDescarga"));
        this.setPesoMercancia(rs.getLong("asignacionPesoMercancia"));
        this.setPrioridad(rs.getInt("asignacionPrioridad"));

        Usuario u = new Usuario();
        u.leerDesdeResultSet(rs);
        this.setUsuario(u);

        Vehiculo v = new Vehiculo();
        v.leerDesdeResultSet(rs);
        this.setVehiculo(v);

        this.setid(rs.getInt("asignacionID"));


    }

    @Override
    public IPersistente getNuevo() {
        
        return new Asignacion();
    }

    @Override
    public String getTableName(boolean singular) {
        return (singular ? "asignacion" : "Asignaciones");
    }

    @Override
    public int getParentId() {
        return this.parentId;
    }

    @Override
    public void setParentId(int id) {
        this.parentId = id;
    }
}
