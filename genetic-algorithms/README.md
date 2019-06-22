# Sistemas de Inteligencia Artificial: Trabajo Práctico 3
A nuestro grupo (Grupo 5) le tocó trabajar con el personaje **ARCHER1**

## Instrucciones de compilación y ejecución

Para ejecutar el trabajo práctico, para conveniencia de los profesores se escribió un script que automatiza el proceso de compliación y ejecución del programa. Se deben seguir los siguientes pasos:

1.	Asegurarse de estar parados en el directorio **genetic-algorithms**
2.	Asegurarse de tener los permisos para ejecutar *execute.sh* que es el archivo que vamos a utilizar para compilar y correr el tp. En caso de no tenerlos, escribir lo siguiente en la terminal de linux `chmod +x execute.sh`
3.	Ya esta todo listo! Escribir lo siguiente en la terminal de linux: `./execute.sh`


## Instrucciones para correr los tests
Para ejecutar los UnitTests basta con correr el comando `mvn test` y maven se encargará del resto.

## Cambiar la configuración
Para cambiar la configuración con la que se corre el programa, se debe modificar el archivo **character.json**. 
En caso de querer cambiar Mutator, Replacer, Crossover, Selector o Conditioner, para saber qué nombre corresponde al que se quiere utilizar simplemente escribir el nombre de la clase. Es importante escribir esto bien, pues sino el programa terminará en una falla de lectura.
El argumento en Crossover corresponde a la probabilidad de cruza.
El argumento en Conditioner se corresponde con tipo de corte que uno quiera utilizar. Por ejemplo, si el corte es por Optimo, el argumento corresponde al fitness que uno desea llegar para cortar la ejecucion.
El GraphDiversityGenerations corresponde a cada cuantas generaciones uno quiere ir imprimiendo la diversidad.

## Autores

- Bianca Ritorto
- Micaela Banfi
- Clara Guzzetti
- Ignacio Vidaurreta
