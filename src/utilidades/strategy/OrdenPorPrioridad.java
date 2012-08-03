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
public class OrdenPorPrioridad extends OrdenPorCriterio {

	@Override
	public int compare(Object arg0, Object arg1) {
		Asignacion a1 = (Asignacion) arg0;
		Asignacion a2 = (Asignacion) arg1;

		return a2.getPrioridad() - a1.getPrioridad();
	}
}
