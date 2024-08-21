# EmployeeSpringBoot

Guía de despliegue archivo .war de EmployeeApp

NOTA: La aplicación ya está configurada para que, al compilar, en la carpeta "target"
al interior de "empleados-app" se compile el archivo .war

1. La primera compilación debe garantizar descargar todas las dependencias, en el caso de Netbeans
dar clic derecho al proyecto y ejecutar "Build with Dependencies", esperar a que se descarguen todas las librerías.

2. Luego clic derecho al proyecto y ejecutar "Clean and Build".

3. Ubicar la carpeta target al interior del proyecto

4. Encontrará un archivo .war llamado employee-springboot-app.war

5. A partir de aquí si se desea, se puede desplegar en Wildfly server siguiento estos pasos:

Instalar WildFly:

1. Descarga e instala WildFly desde wildfly.org.

2. Configurar WildFly:

Descomprime el archivo descargado y navega a la carpeta bin.
Ejecuta el script standalone.bat (Windows) o standalone.sh (Linux/Mac) para iniciar el servidor.
Desplegar el .war:

Copiar archivo .war generado a la carpeta standalone/deployments dentro del directorio de instalación de WildFly.
WildFly detectará automáticamente el archivo y lo desplegará.
Verificar el Despliegue:

Accede a http://localhost:8080/employee-springboot-app para verificar que tu aplicación esté desplegada correctamente.
En caso de tener ocupado el puerto 8080, actualizarlo.