/**IFPB - Curso SI - Disciplina de POO
 * @author Fabrício Liberato
 */

package modelo;

import java.util.ArrayList;

public class Garcom {

	private String apelido;
	private ArrayList<Mesa> mesas;
	
	public Garcom(String apelido, ArrayList<Mesa> mesas) {
		this.apelido = apelido;
		this.mesas = mesas;
	}
	
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public ArrayList<Mesa> getMesas() {
		return mesas;
	}
	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}

	@Override
	public String toString() {
		String mesas = new String();
		for (Mesa m : this.mesas)
			mesas += (m.getId() + " ");
		return "Garcom [apelido=" + apelido + ", mesas= [ " + mesas + "] ]";
	}
	
	
	
}
