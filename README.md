# PRUEBA TECNICA: DARWIN VEGAS
##### Esta prueba consiste en dos retos, el primo es la validación de informes de revista para que se cumpla con un determinado criterio una longitud aproximada para su validacion, el segundo reto se basa en varias lista de estudiantes donde se debe enviar un corro a una determinada sede
    
* ### **RETO 1, INFORME CON RECORTE(S) DE REVISTA:**.
  * Detalles de la función realiza
    * ##### Se creó una función de utilidad para separar por espacios y así obtener un array de palabras como tambien omitir palabras con menos de 3 caracteres de longitud que normalmente son Pronombre personal,demostrativo, posesivo,  indefinido. Tanto para informe/revista
    * ##### Se realizó una función de limpiar para qué reemplace caracteres no deseados  
    * ##### Se creó una lista de palabras negativas para limpiar el informe/revista
    * ##### Se creó un variable minPorcentaje (configurable) para calcular el porcentaje del el umbral de aceptación del informe
    * ##### Se creó una función de utilidad para Sanitización y limpiar el informe/revista
    * ##### Se creó una variable para palabras mínimas que tienen que coincidir con las palabras del informe, esto se realiza multiplicando el minPorcentaje por el total de palabras en la revista
    * ##### Se realiza un bucle donde se buscan las palabras del informe contra las palabras de la revista
    * ##### Se realiza una variable donde se calcula el porcentaje de acierto en porcentaje de las palabras encontradas dividiendo las palabras encontradas con el total de palabras por 100
    * ##### Para sabes y obtener si el informe, si es válido, se realiza la validación del porcentaje acertado de palabras encontrada que sea igual o mayor que el porcentaje del umbral de acierto (minPorcentaje)
    * #### Test exitoso
      <img src="src/main/resources/img/informe acertado.png" alt="img">
    * #### Test Fallido
      <img src="src/main/resources/img/informe no acertado.png" alt="img">

* ### **RETO 2, EL INSTITUTO DESEA NOTIFICAR POR VIA EMAIL A LOS ESTUDIANTES DEL LA SEDE MALAGA, SOBRE UNA INCIDENCIA QUE AFECTAN LAS CLASES DEL DIA SIGUEINTE:**.
  * #### Se crearon 3 listas y a cada lista se le asignaron 3 estudiantes
  * #### Se creo una funcion que recorre la lista y filtra por los estudiantes que se encuentran en la sede de MALAGA
  * #### Test de notificación
    <img src="src/main/resources/img/envio de correo por incidencia.png" alt="img">
    
