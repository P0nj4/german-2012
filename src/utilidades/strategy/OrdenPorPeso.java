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
public class OrdenPorPeso extends OrdenPorCriterio {

	@Override
	public int compare(Object arg0, Object arg1) {
		Asignacion a1 = (Asignacion) arg0;
		Asignacion a2 = (Asignacion) arg1;
                Long Obj1 = new Long(a1.getPesoMercancia());
                Long Obj2 = new Long(a2.getPesoMercancia());
		return Obj1.compareTo(Obj2);
	}
    
}
