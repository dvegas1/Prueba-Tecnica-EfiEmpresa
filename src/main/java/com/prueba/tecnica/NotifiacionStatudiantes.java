package com.prueba.tecnica;

import com.prueba.tecnica.model.Studiantes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.prueba.tecnica.templates.Templates.templateMatematica;
import static java.lang.String.format;

public class NotifiacionStatudiantes {
    private static final Logger logger = LogManager.getLogger(Main.class);
    List<Studiantes> listaA = new ArrayList<>();
    List<Studiantes> listaB = new ArrayList<>();
    List<Studiantes> listaC = new ArrayList<>();
    private String[] asignaturas = {"matematica", "frances", "ingles", "economia"};

    public boolean enviarNotificacion() {

        ArrayList<List<Studiantes>> listaCompletaStudiantes = new ArrayList<>();

        // Estudiantes de matemática
        Studiantes studiante1 = Studiantes.builder().nombre("Lucia").sede("Malaga").correo("Lucia@school.edu").asignaturas(new String[]{asignaturas[0]}).build();

        Studiantes studiante2 = Studiantes.builder().nombre("Maria").sede("Madrid").correo("Maria@school.edu").asignaturas(new String[]{asignaturas[0]}).build();

        Studiantes studiante3 = Studiantes.builder().nombre("Paula").sede("Barcelona").correo("Paula@school.edu").asignaturas(new String[]{asignaturas[0]}).build();

        // Estudiantes de francés
        Studiantes studiante4 = Studiantes.builder().nombre("Carla").sede("Malaga").correo("Carla@school.edu").asignaturas(new String[]{asignaturas[1]}).build();

        Studiantes studiante5 = Studiantes.builder().nombre("Sara").sede("Madrid").correo("Sara@school.edu").asignaturas(new String[]{asignaturas[1]}).build();

        Studiantes studiante6 = Studiantes.builder().nombre("Mia").sede("Barcelona").correo("Mia@school.edu").asignaturas(new String[]{asignaturas[1]}).build();

        // Estudiantes de matemática y francés
        Studiantes studiante7 = Studiantes.builder().nombre("Noa").sede("Malaga").correo("Noa@school.edu").asignaturas(new String[]{asignaturas[0], asignaturas[1]}).build();

        Studiantes studiante8 = Studiantes.builder().nombre("Chloe").sede("Madrid").correo("Chloe@school.edu").asignaturas(new String[]{asignaturas[0], asignaturas[1]}).build();

        Studiantes studiante9 = Studiantes.builder().nombre("Aitana").sede("Barcelona").correo("Aitana@school.edu").asignaturas(new String[]{asignaturas[0], asignaturas[1]}).build();

        listaA.add(studiante1);
        listaA.add(studiante2);
        listaA.add(studiante3);

        listaB.add(studiante4);
        listaB.add(studiante5);
        listaB.add(studiante6);

        listaC.add(studiante7);
        listaC.add(studiante8);
        listaC.add(studiante9);

        listaCompletaStudiantes.add(listaA);
        listaCompletaStudiantes.add(listaB);
        listaCompletaStudiantes.add(listaC);

        // Notificar a los estudiantes
        enviarNotificacionEstudiante(listaCompletaStudiantes);

        return true;
    }

    public boolean enviarCorreo(Studiantes estudiante) {
        logger.info(format(templateMatematica, estudiante.getCorreo().toLowerCase(), estudiante.getNombre()));
        return true;
    }

    public void enviarNotificacionEstudiante(ArrayList<List<Studiantes>> lista) {
        for (List<Studiantes> estudiantes : lista) {
            for (Studiantes estudiante : estudiantes) {
                if (estudiante.getSede().toLowerCase().equals("malaga")) {
                        if (!estudiante.isEnviado()) {
                            enviarCorreo(estudiante);
                            estudiante.setEnviado(true);
                        }
                }
            }
        }
    }
}
