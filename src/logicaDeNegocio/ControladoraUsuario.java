package logicaDeNegocio;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
            ArrayList todosLosUsuarios = u.obtenerTodos();
            for (int i = 0; i < todosLosUsuarios.size(); i++) {
                Usuario usu = (Usuario) todosLosUsuarios.get(i);
                if (usu.getPassword().equals(MD5_pass) && usu.getNombre().equals(usuario)) {
                    u = usu;
                    i = todosLosUsuarios.size();
                }
            }
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
