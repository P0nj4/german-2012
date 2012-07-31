/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.strategy;

import dominio.Asignacion;

/**
 *
 * @author German
 */
public class OrdenPorFifo extends OrdenPorCriterio {

	@Override
	public int compare(Object arg0, Object arg1) {
		Asignacion a1 = (Asignacion) arg0;
		Asignacion a2 = (Asignacion) arg1;

		return a1.getFechaDeCreacion().compareTo(a2.getFechaDeCreacion());
	}

}