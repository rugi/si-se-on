# Si-se-on
Single Search for PDF doc's.

SiSeOn, es un sencillo buscador para archivos PDF.

Su funcionamiento es el siguiente:
- Recibe de entrada una serie de archivos PDF.
- Crea un indice con el contenido ( en texto ) de los archivos.
- Permite realizar búsquedas por palabra en el contenido indexado.
- Muestra los resultados y da la opción de abrir un archivo en particular.

En este video lo puedes ver funcionar.
[![SiSeOn. Ejemplo de uso](http://img.youtube.com/vi/NP-qzMXNntc/0.jpg)](http://www.youtube.com/watch?v=NP-qzMXNntc)

Está construido con java 7, el código del Indexador está basado en código de:
http://fazlansabar.blogspot.mx/


## Para ejecutar.

### Usando maven:
Baja todo el proyecto, en la carpeta con el archivo POM, ejecuta:

```python
%>mvn clean install
%>mvn exec:java -Dexec.mainClass="mx.org.rugi.tools.siseon.Main"
```

### StandAlone:
Entra a la carpeta _releases_ y elije un _release_

