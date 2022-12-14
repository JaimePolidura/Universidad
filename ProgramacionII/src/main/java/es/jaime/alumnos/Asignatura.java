package es.jaime.alumnos;


public class Asignatura{
	private String nombre;
	private Alumno[] alumnos;
	private boolean ordenado = false;
	private int posicion;
	private int nAlumnos;

	public Asignatura(String nomb, int nAl){
	   nombre = nomb;
	   posicion=0;
	   nAlumnos = nAl;
	   alumnos = new Alumno[nAlumnos];
	}

	public void addAlumno(Alumno al){
	   if (posicion<nAlumnos){
		   alumnos[posicion] = al;
		   posicion++;
	   }
	}

	private void ordena(){
	   if (!ordenado){
	      ordenado = true;
	      Alumno aux;
	      int N = alumnos.length;

	      for (int i=0;i<(N-2);i++)
	         for (int j=(N-1);j>i;j--)
	            if (alumnos[j].getNota()<alumnos[j-1].getNota()){
	               aux = alumnos[j-1];
	               alumnos[j-1] = alumnos[j];
	               alumnos[j] = aux;
	            }
	   }
	}

	public float mediana(){
	   ordena();
	   return alumnos[Math.round(alumnos.length/2)].getNota();
	}

	public float notaMedia(){
		float acumuladorNota = 0;

		for(int i = 0; i < posicion; i++){
			acumuladorNota = acumuladorNota + alumnos[i].getNota();
		}

		return acumuladorNota / posicion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Alumno getAlumMax(){
		ordena();

		return alumnos[posicion - 1];
	}

	public Alumno getAlumMin(){
		ordena();

		return alumnos[0];
	}
}
