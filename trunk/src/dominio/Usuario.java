package dominio;

import java.sql.ResultSet;
import java.util.ArrayList;

import persistencia.IPersistente;
import persistencia.ManejadorBD;

public class Usuario implements IPersistente {
	private int id;
	private String nombre;
	private String password;
	private int tipoDeUsuario;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}

	public int getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(int tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	@Override
	public int getid() {
		// TODO Auto-generated method stub
		return this.id;
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
		return null;
	}

	@Override
	public String getUpdateSQL() {
		return null;
	}

	@Override
	public String getDeleteSQL() {
		return null;
	}

	@Override
	public String getSelectSQL() {
		String sql ="Select * from Usuarios where borrado = 0";
		if(password != null && nombre != null)
			sql+= " and usuarioNombre = '" + this.nombre +"' and usuarioPassword = '" + this.getPassword() + "'";
		return sql;
	}

	@Override
	public void leerDesdeResultSet(ResultSet rs) throws Exception {
		this.setPassword(rs.getString("usuarioPassword"));
		this.setNombre(rs.getString("usuarioNombre"));
		this.setid(rs.getInt("usuarioID"));
		this.setTipoDeUsuario(rs.getInt("tipoUsuarioID"));
		
	}

	@Override
	public IPersistente getNuevo() {
		return new Usuario();
	}

	@Override
	public String getTableName(boolean singular) {
		if (singular)
			return "usuario";
		else
			return "Usuarios";
	}

  

}
