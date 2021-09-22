package ProgramacionII.alumnos;

public class Ejemplo{
   public static void main(String[] args) {
      Asignatura as = new Asignatura("Programación II", 5);

      Alumno a = new Alumno("Manolo", "Pérez", "56789890z", 5f);
      as.addAlumno(a);

      a = new Alumno("María", "García", "87415268t", 6f);
      as.addAlumno(a);

      a = new Alumno("Juan José", "Torres", "96354712d", 6f);
      as.addAlumno(a);

      a = new Alumno("Laura", "Meseguer", "87415268t", 6f);
      as.addAlumno(a);

      a = new Alumno("Lucía", "Serra", "77441166h", 5f);
      as.addAlumno(a);

      a = new Alumno("Antonio", "Peris", "96358425A", 5.5f);
      as.addAlumno(a);


      System.out.println("Nombre de la asignatura: " + as.getNombre());
      System.out.println("Nota Media:              " + as.notaMedia());
      a = as.getAlumMax();
      System.out.println("El alumno/a " + a.getNombre() + " " + a.getApellidos() + " ha obtenido la máxima nota: " + a.getNota());
      a = as.getAlumMin();
      System.out.println("El alumno/a " + a.getNombre() + " " + a.getApellidos() + " ha obtenido la mínima nota: " + a.getNota());
   }
}
