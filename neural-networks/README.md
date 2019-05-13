## Sistemas de Inteligencia Artificial: Trabajo Práctico 2

### Instrucciones de ejecución
Descargar o clonar el repositorio. 
Desde la terminal, pararse en el repositorio y correr el comando `octave run.m`. Otra forma de correr este TP es entrando a Octave (esto se puede hacer desde la terminal escribiendo `octave &` (se agrega el ampersant 
para que el proceso este en el background y la terminal no quede inutilizada), luego, desde Octave, pararse dentro del repositorio y desde la terminal de octave escribir `run`.

### Modificando los parámetros de la red
Como veran, dentro del repositorio hay un archivo llamado `network_parameters.bin`. Dicho archivo contiene todos parámetros de la red neuronal ya asignados, pero tranquilamente se pueden cambiar esos valores
para que la red neuronal sea distinta. Es importante recordar que este archivo sólo se lee al principio de la ejeución, entonces tratar de modificarlo antes de correr la red neuronal. De todos modos, se pueden modificar
los parámetros de la red durante la ejecución, como veremos a continuación.

### Como utilizar el debugger de octave para cambiar las variables en runtime

En el archivo `run.m` podemos observar que llama al comando `debug_on_interrupt(1)`. Lo que esto logra, es que mientras estamos ejecutando nuestra red
neuronal, si interrumpimos forzadamente la ejecución (en el caso de Linux apretando **ctrl + C**) en vez de finalizar la ejecución, la frena y se pone en modo
debug. Esto permite escribir comandos de octave, lo cual nos permite cambiar los valores de las variables. 
Para el caso de variables globales que estén fuera del scope de la función actual, escribir `global var_name` para meterla dentro del scope y poder cambiarla.
Para continuar la ejecución, escribir `dbcont` y para finalizar la ejecución `dbquit`.
### Autores

- Bianca Ritorto
- Micaela Banfi
- Clara Guzzetti
- Ignacio Vidaurreta
