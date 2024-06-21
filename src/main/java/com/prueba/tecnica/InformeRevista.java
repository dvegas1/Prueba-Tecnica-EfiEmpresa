package com.prueba.tecnica;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

public class InformeRevista {

    private static final Logger logger = LogManager.getLogger(InformeRevista.class);

    private String revista = "";
    private String[] palabrasRevista;
    private static Gson gson = new Gson();
    private static final String[] palabrasProhibidas = {"a", "e", "i", "o", "u", "á", "é", "í", "ó", "ú", "Yo", "Tú", "Él", "Nosotros", "Vosotros", "Usted", "Ellos", "El", "la", "Ustedes", "Ellas", "Vos", "Nosotras", "Tú", "te", "contigo", "usted", "Ellos", "ellas", "los", "las", "les", "se", "sí", "consigo", " ", "lo", "de", "un", "que", "por", "para", "a", "e", "i", "o", "u", "y", "las", "como", "desde", "que", "uno", "del"};

    private static double minPercentage = 0.2;

    private String informe = "";

    public InformeRevista(String informe){
        this.informe = informe;
    }

    public boolean runInforme() {
        this.readFileRevista();
        informe = this.limpiar(this.informe);
        this.revista = this.limpiar(this.revista);
        return this.validateInforme(informe, this.revista);
    }


    //Función que busca el archivo revista.txt ubicado en resources
    public Boolean readFileRevista() {
        Boolean valid = true;
        try {
            revista = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(Main.class.getClassLoader().getResource("revista.txt")).toURI())));

            palabrasRevista = revista.split(" ");

        } catch (URISyntaxException uri) {
            valid = false;
            System.out.println("No existe la revista.");

        } catch (IOException ex) {
            valid = false;
            System.out.println("No fue posible ejecutar el proceso de busqueda de revista.");
        } finally {
            System.out.println("-- readFileRevista Fin busqueda de revista -- .");
        }
        return valid;
    }

    public boolean validateInforme(String informe, String revista) {
        // Cuente las apariciones de cada palabra en la nota y la revista.
        Map<String, Integer> totalEncontradasInforme = contadorPalabras(informe);
        Map<String, Integer> totalEncontradasRevista = contadorPalabras(revista);

        // Calcular el número total de palabras del diario.
        int totalPlabrasRevista = totalEncontradasRevista.size();

        // Indicación del número absoluto de palabras coincidentes necesarias para que la nota alcance el umbral porcentual deseado

        int palabrasMinimasCoincidentes = (int) Math.round(minPercentage * totalPlabrasRevista);
        int minPalabrasConcidendencias = (int) Math.round(minPercentage * 100);

        int encontradas = 0;
        for (Map.Entry<String, Integer> entry : totalEncontradasInforme.entrySet()) {
            String key = entry.getKey();
            int informeValue = entry.getValue();

            if (totalEncontradasRevista.containsKey(key) && totalEncontradasRevista.get(key) >= informeValue) {
                encontradas += informeValue;
            }
        }

        double percentageMatching = Math.round((encontradas / (double) totalPlabrasRevista) * 100);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String porcentajeAcertado = decimalFormat.format(percentageMatching);

        logger.info("\r-------------------------------------------------------------------");

        logger.info(format("Acertacion para el informe:%s%s", minPalabrasConcidendencias, "%"));
        logger.info(format("Porcentaje de aceptacion:%s%s\n", porcentajeAcertado, "%"));

        logger.info(format("palabras totales en revista:%s", totalEncontradasRevista.size()));
        logger.info(format("palabras totales en informe:%s\n", totalEncontradasInforme.size()));

        logger.info(format("palabras mínimas de acertacion:%s", palabrasMinimasCoincidentes));
        logger.info(format("palabras acertada entre informe/revista:%s\n", encontradas));

        // Compruebe si el porcentaje alcanza el umbral mínimo
        boolean result = Integer.parseInt(porcentajeAcertado) >= minPalabrasConcidendencias;

        if (validateSucess(result)) {
            logger.info(format("\r-- EXITO, EL INFORME ACERTADO PARA LA REVISTA CON UN PORCENTAJE DE %s%s SOBRE UN %s%s %s --\n", porcentajeAcertado, "%", minPalabrasConcidendencias, "%", "REQUERIDO"));
        } else {
            logger.info(format("\r-- El INFORME NO ES ACEPTADO YA QUE TIENE UN PORCENTAJE DE %s%s SOBRE UN %s%s %s --\n", porcentajeAcertado, "%", minPalabrasConcidendencias, "%", "REQUERIDO"));
        }
        logger.info("\r-------------------------------------------------------------------");


        return result;
    }

    private boolean validateSucess(Boolean result) {
        return result;
    }

    //Función que cuentas las palabras separándolas por espacios, la misma hace una validacion en count.getOrDefault(palabra, 0) + 1); para validar si ya existe y actualiza el existente
    private Map<String, Integer> contadorPalabras(String text) {
        Map<String, Integer> hashMapPalabras = new HashMap<>();
        ArrayList<String> keys = new ArrayList<>();

        for (String palabra : text.split("\\s+")) {
            if (palabra.length() > 2) {
                hashMapPalabras.put(palabra.toLowerCase(), hashMapPalabras.getOrDefault(palabra, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : hashMapPalabras.entrySet()) {
            for (String prohibida : palabrasProhibidas) {
                if (entry.getKey().equalsIgnoreCase(prohibida)) {
                    keys.add(entry.getKey().toLowerCase());
                }
            }
        }

        for (String key : keys) {
            hashMapPalabras.remove(key);
        }
        return hashMapPalabras;
    }

    public String limpiar(String value) {
        return value.replaceAll("\\r", " ").replaceAll("\\n", "").replaceAll("  ", " ").replaceAll("\\\\", "").replaceAll(",", "").replaceAll("\"", "").replaceAll("\\.", " ").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(":", "").replaceAll("-", "").replaceAll("_", "").toLowerCase().trim();
    }
}
