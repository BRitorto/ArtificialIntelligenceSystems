## Sistemas de Inteligencia Artificial: Trabajo Práctico 2

### Instrucciones de compilación y ejecución



### Como utilizar el debugger de octave para cambiar las variables en runtime

En el archivo `script.m` podemos observar en la línea 8 el comando `debug_on_interrupt(1)`. Lo que esto logra, es que mientras estamos ejecutando nuestra red
neuronal, si interrumpimos forzadamente la ejecución (en el caso de Linux apretando **ctrl + C**) en vez de finalizar la ejecución, la frena y se pone en modo
debug. Esto permite escribir comandos de octave, lo cual nos permite cambiar los valores de las variables. 
Para el caso de variables globales que estén fuera del scope de la función actual, escribir `global var_name` para meterla dentro del scope y poder cambiarla.
Para continuar la ejecución, escribir `dbcont` y para finalizar la ejecución `dbquit`.
### Autores

- Bianca Ritorto
- Micaela Banfi
- Clara Guzzetti
- Ignacio Vidaurreta
