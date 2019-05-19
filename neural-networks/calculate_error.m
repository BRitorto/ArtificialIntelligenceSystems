
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
  for p = [1:P]
    res(p) = run_pattern(W, patterns{p}{1}, g){cant_layers+1};
    wanted(p) = patterns{p}{2}(1);
  endfor

  error = mean((wanted.-res).^2);

endfunction