# si-se-on
Single Search for PDF doc's.

SiSeOn, es un sencillo buscador para archivos PDF.

Su funcionamiento es el siguiente:
- Recibe de entrada una serie de archivos PDF.
- Crea un indice con el contenido ( en texto ) de los archivos.
- Permite realizar búsquedas por palabra en el contenido indexado.
- Muestra los resultados y da la opción de abrir un archivo en particular.

Está construido con java 7, el código del Indexador está basado en :

http://fazlansabar.blogspot.mx/


Para ejecutar.

Usando maven:
Baja todo el proyecto, en la carpeta con el archivo POM, ejecuta:
%>mvn clean install
%>mvn exec:java -Dexec.mainClass="mx.org.rugi.tools.siseon.Main"

StandAlone:
Entra a la carpeta _releases_ y elije un _release_

