%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Agarra una red, una patron entrenado y un aprendijaze rate y calcula los pesos teniendo en cuenta as reglas de back-propagation
% W es una matriz W{m}(i,j) que guarda los pesos de las conecciones de i en la capa m-1 a j en la capa m
% g guarda las funciones de activacion y su derivada para cada capa
% E(i) guarda el inpuut de la unidad i
% S es el output esperado
% eta es el learning rate
%
% Retorna delta_W{m} que es el update para anadir a la matriz de pesos en la capa m
%

function delta_W = run_and_correct(W, E, g, S, eta)
  cant_layers = numel(W);
  V = run_pattern(W, E, g);
  
  derivatives = V;
  derivatives = apply_derivative(derivatives, V, g, cant_layers);
  
  delta = cell(cant_layers, 1);
  delta_W = backpropagation(delta, W, S, V, derivatives, cant_layers, eta);
endfunction

function delta_W = backpropagation(delta, W, S, V, derivatives, cant_layers, eta)
  delta{cant_layers} = S - V{cant_layers+1};
  for k = [cant_layers-1:-1:1]
    delta{k} = (((W{k+1}')*delta{k+1})(2:end).*derivatives{k+1});
  endfor

  delta_W = cell(cant_layers, 1);
  for k = [1:cant_layers]
    delta_W{k} = eta*(delta{k}*V{k}');
  endfor
endfunction

function derivatives = apply_derivative(derivatives, V, g, cant_layers)
  for k = [2:cant_layers]
    derivatives{k} = arrayfun(g{k-1}{2}, V{k}(2:end));
  endfor
  derivatives{cant_layers+1} = arrayfun(g{cant_layers}{2}, V{cant_layers+1});
  
endfunction