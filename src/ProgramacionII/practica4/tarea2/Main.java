package ProgramacionII.practica4.tarea2;

import ProgramacionII.practica4.Alumno;
import ProgramacionII.practica4.tarea1.AlumnoGII;
import ProgramacionII.practica4.tarea1.AlumnoGIIAA;
import ProgramacionII.practica4.tarea1.AlumnoGIOI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class Main {
    public static void main(String[] args) throws IOException {
        Map<String, Alumno> alumnos = cargarContenidoEnMapa("c:/alumnos.txt");

        Collection<Alumno> alumnosValues = alumnos.values();

        for (Alumno alumno : alumnosValues) {
            printAlumno(alumno);
            System.out.println("----------");
        }

        Alumno alumno = alumnos.get("55667788P");

        if(alumno == null){
            System.out.println("No se ha encontrado al alumno");
        }else{
            printAlumno(alumno);
        }
    }

    private static Map<String, Alumno> cargarContenidoEnMapa(String ruta) throws IOException {
        Map<String, Alumno> toReturn = new HashMap<>();

        FileReader fileReader = new FileReader(ruta);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> alumnosArray = cargarArray(bufferedReader);
        Iterator<String> iterator = alumnosArray.iterator();

        String actualLine;
        String actualDni;
        Alumno alumno = null;

        while (iterator.hasNext()) {
            actualLine = iterator.next();

            validarSiHaySiguiente(iterator);
            actualDni = iterator.next();

            validarSiHaySiguiente(iterator);
            String nombre = iterator.next();

            validarSiHaySiguiente(iterator);
            String apellidos = iterator.next();

            validarSiHaySiguiente(iterator);
            float notaMeda = Float.parseFloat(iterator.next());

            validarSiHaySiguiente(iterator);
            boolean isBecario = Boolean.parseBoolean(iterator.next());

            switch (actualLine) {
                case "GII":
                    alumno = new AlumnoGII(nombre, apellidos, notaMeda, isBecario);
                    break;
                case "GIIAA":
                    alumno = new AlumnoGIIAA(nombre, apellidos, notaMeda, isBecario);
                    break;
                case "GIOI":
                    alumno = new AlumnoGIOI(nombre, apellidos, notaMeda, isBecario);
                    break;
            }

            toReturn.put(actualDni, alumno);
        }

        bufferedReader.close();
        fileReader.close();

        return toReturn;
    }

    private static void validarSiHaySiguiente (Iterator<?> iterator) {
        if(!iterator.hasNext()){
            throw new IllegalArgumentException("Archivo en formato incorrecto");
        }
    }

    private static List<String> cargarArray (BufferedReader reader) throws IOException {
        List<String> listToReturn = new ArrayList<>();
        String actualWordReading = null;

        while ((actualWordReading = reader.readLine()) != null) {
            listToReturn.add(actualWordReading);
        }

        return listToReturn;
    }

    private static void printAlumno (Alumno alumno) {
        System.out.println(alumno.getNombre());
        System.out.println(alumno.getApellidos());
        System.out.println(alumno.getNotaMedia());
        tipoAlumno(alumno);
    }

    private static void tipoAlumno (Alumno alumno) {
        if(alumno instanceof AlumnoGII){
            System.out.println("Pertenece a GII");
        }else if (alumno instanceof AlumnoGIOI) {
            System.out.println("Pertenece a GIOI");
        }else if (alumno instanceof AlumnoGIIAA) {
            System.out.println("Pertenece al GIIAA");
        }
    }
}
