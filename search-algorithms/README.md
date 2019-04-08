## Sistemas de Inteligencia Artificial: Trabajo Práctico 1

### Instrucciones de compilación y ejecución

Para crear los .jar, primero parados dentro de search algorithms correr el comando
```bash
    mvn install
```

y luego, para correr el programa, ejecutar el siguiente script:

```
        execute.sh
```
El cual se encarga buildear y correr el programa.

### Formas de Jugar
Dependiendo con qué argumentos corra el usuario el script *execute.sh* se abrirá un modo de juego. Si se elije el modo Fill Rule, el tablero se resuelve con DFS, ya que es la estrategia mas rapida. Con Swap Rule, se resuelve utilizando estrategia Greedy. Corriendo el script sin argumentos:
```bash
    ./execute.sh
```
Se abrirá el juego de forma normal. Se le preguntaran todos los datos al usuario vía input (nivel de dificultad, dimension del tablero, modo de juego).

Corriendolo con 1 argumento numérico

```bash
    ./execute [num]
```
Se correra el juego con distintas condiciones iniciales dependiendo del número:

- Si num == 1 --> Se resolverá una board 3x3 en modo fácil con Fill Rule con la estrategia DFS
- Si num == 2 --> Se resolverá una board 4x4 en modo difícil con Swap Rule con la estrategia GREEDY
- Si num == 3 --> Se resolverá una board 5x5 en modo medio con Swap Rule con la estrategia ASTAR
- Si num es cualquier otro número --> Se resolverá una board 4x4 en modo difícil con Fill Rule con la estrategia IDDFS

En caso de que se corra execute con otra cantidad de argumentos, este devolverá un error y ni siquiera buildeará el proyecto (fail fast).




### Autores

- Bianca Ritorto
- Micaela Banfi
- Clara Guzzetti
- Ignacio Vidaurreta
