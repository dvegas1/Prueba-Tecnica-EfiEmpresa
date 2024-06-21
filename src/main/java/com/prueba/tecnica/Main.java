package com.prueba.tecnica;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.runInforme();
        main.emviarNotificacionUsuarios();
    }
    public  boolean runInforme(){
        String informe = "java un lenguaje de programacion robusto y versatil que sigue vigente java un lenguaje de programacion orientado a objetos creado por sun microsystems en 1995 ha mantenido su posicion como uno de los lenguajes mas populares y utilizados en la actualidad  su versatilidad robustez y amplia comunidad de desarrolladores lo convierten en una opcion atractiva para una gran variedad de proyectos desde aplicaciones web y moviles hasta sistemas empresariales de gran escala una de las principales fortalezas proyecto de java es su filosofia escribir automatica gestion";
        InformeRevista informeRevista = new InformeRevista(informe);
        return informeRevista.runInforme();
    }

    public void emviarNotificacionUsuarios(){
        NotifiacionStatudiantes notifiacionStatudiantes = new NotifiacionStatudiantes();
        notifiacionStatudiantes.enviarNotificacion();
    }
}