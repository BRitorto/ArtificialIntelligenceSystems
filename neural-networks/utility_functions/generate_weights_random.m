
% network es un vector donde cada elemento representa el numero de nodos en cada capa
# https://towardsdatascience.com/random-initialization-for-neural-networks-a-thing-of-the-past-bfcdd806bf9e
function W = generate_weights_random(network)
  cant_layers = numel(network) - 1;
  W = cell(cant_layers, 1);
  for m = [1:cant_layers] 
    W(m) = rand(network(m+1), (network(m)+1));
  endfor
endfunction