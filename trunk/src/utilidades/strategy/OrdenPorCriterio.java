/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.strategy;

/**
 *
 * @author German
 */
import java.util.Comparator;

public abstract class OrdenPorCriterio implements Comparator {
	
	public static OrdenPorCriterio getStrategy(int orden) {
		
		switch (orden) {
		case 1: return new OrdenPorFifo();

		case 2: return new OrdenPorPeso();
	
		case 3: return new OrdenPorPrioridad();

		default:
			break;
		}
		
		return null;
		
	}
	

}
