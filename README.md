# CVDS Grupo No.3 | Laboratorio #3 - TDD SONARQUBE & JACOCO

### Enunciado Oficial: [LAB3 CVDS Company](https://github.com/CVDS-ESCUELAING/Laboratory2025/blob/main/LAB03.md)


### Integrantes
- [Sergio Andrey Silva Rodríguez](https://github.com/OneCode182) (Owner)
- [Juan Esteban Lozano Cárdenas](https://github.com/juanLozano-2004) (Collaborator)

## Construccion Proyecto
### Crear Proyecto Maven
Crear un proyecto maven con los siguientes parámetros:
```yml
Grupo: edu.eci.cvds 
Artefacto: Library 
Paquete: edu.eci.cvds.tdd 
archetypeArtifactId: maven-archetype-quickstart 
```

Para ello, se usa el siguiente comando:

```sh
mvn archetype:generate \
  -DgroupId=edu.eci.cvds \
  -DartifactId=Library \
  -Dpackage=edu.eci.cvds.tdd \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

### Agregando Dependencias
- Buscar en maven central la dependencia de JUnit5 en su versión más reciente.
[JUnit5-Maven-Central](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.11.4)

La version mas reciente y estable de JUnit5 encontrada en Maven Central es la `5.11.4`. 

- Edite el archivo `pom.xml` del proyecto para agregar la dependencia.

Agregar la dependencia en la etiqueta `dependencies`:
```xml
<!-- JUnit Jupiter API for JUnit 5 -->
<!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.11.4</version>
    <scope>test</scope>
</dependency>
```

- Compile el proyecto para validar que todo este bien.
```sh
mvn clean package
```



