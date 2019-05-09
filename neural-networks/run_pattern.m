
% Agarra una red neuronal, una input de patterns y retorna output en cada capa
% Parameters:
%
% W es una matriz W{m}(i,j) que guarda los pesos de las conecciones de i en la capa m-1 a j en la capa m
% g guarda las funciones de activacion y su derivada para cada capa
% E(i) guarda el inpuut de la unidad i
%
% Retorna V{m{(i) que contiene el output de i en la capa m-1
%

function V = run_pattern(W, E, g)
  cant_layers = numel(W);
  V = cell(cant_layers+1, 1);
  V{1} = E;
  for k = [1:cant_layers]
    V{k} = [-1; V{k}];
    V{k+1} = arrayfun(g{k}{1}, W{k}*V{k});
  endfor
endfunction