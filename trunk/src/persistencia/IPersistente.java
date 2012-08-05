package persistencia;

import java.util.ArrayList;
import java.sql.*;

public interface IPersistente {

	public int getid();        
	public void setid(int valor);
        
	public void guardar(int parentID) throws Exception;
	public void leer() throws Exception;
	public void eliminar() throws Exception;
	public ArrayList obtenerTodos() throws Exception;
	
	public String getInsertSQL();
	public String getUpdateSQL();
	public String getDeleteSQL();
	public String getSelectSQL();
	public void leerDesdeResultSet(ResultSet rs) throws Exception;
	public IPersistente getNuevo();
	public String getTableName(boolean singular);
}
