package logicaDeNegocio;


import java.util.ArrayList;

import dominio.Usuario;


import excepciones.ExcepcionControlada;
import utilidades.ConvertidorMD5;

public class ControladoraUsuario {

    private static ControladoraUsuario instancia;

    private ControladoraUsuario() {
    }

    public static ControladoraUsuario getInstance() {
        if (instancia == null) {
            instancia = new ControladoraUsuario();
        }
        return instancia;
    }

    /***
     * Metodo encargado de validar si el usuario ingresado existe.
     * En caso de que exista retorna el mismo
     * @param usuario nombre de usuario
     * @param password contraseña de usuario
     * @return usuario
     * @throws Exception 
     */
    public Usuario loguearUsuario(String usuario, String password) throws Exception {
        try {
            if (usuario == null || usuario.trim().length() == 0) {
                throw new ExcepcionControlada("El nombre de usuario no puede ser nulo, por favor vuelva a intentar");
            }
            if (password == null || password.trim().length() == 0) {
                throw new ExcepcionControlada("La contraseña de usuario no puede ser nula, por favor vuelva a intentar");
            }
            String MD5_pass = ConvertidorMD5.MD5(password);
            Usuario u = new Usuario();
            u.setNombre(usuario);
            u.setPassword(MD5_pass);
            u.leer();
            if (u.getid() == 0) {
                throw new ExcepcionControlada("Usuario y/o contraseña no son correctos");
            } else {
                return u;
            }
        } catch (ExcepcionControlada e) {
            throw e;
        } catch (Exception e) {
            throw new ExcepcionControlada(e);
        }

    }
}
