
% Agarra red neuronal, patrones de entrada y retorna el error 
% Parameters:
%
% W es una matriz W{m}(i,j) que guarda los pesos de las conecciones de i en la capa m-1 a j en la capa m
% g guarda las funciones de activacion y su derivada para cada capa
%
% Retorna V{m}(i) que contiene el output de i en la capa m-1
%

function error = calculate_error(W, patterns, g)
  cant_layers = numel(W);
  outsize = rows(W{cant_layers});
  P = numel(patterns);
  error = 0;
  for p = [1:P]
    res = run_pattern(W, patterns{p}{1}, g){cant_layers+1};
    for o = [1:outsize]
      error += (patterns{p}{2}(o) - res(o))^2;
    endfor
  endfor
  error /= (2);
endfunction