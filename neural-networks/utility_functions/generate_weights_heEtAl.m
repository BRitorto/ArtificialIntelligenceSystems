
# network is a vector where each element represents a
# the number of nodes in the layer.
# network(m) is the number of neurons in the layer m -1
# https://towardsdatascience.com/random-initialization-for-neural-networks-a-thing-of-the-past-bfcdd806bf9e
function W = generate_weights_heEtAl(network)
  cant_layers = numel(network) - 1;
  W = cell(cant_layers, 1);

  for m = [1:cant_layers]
    high = 2/sqrt(network(m));
    low = high/4;   
    W(m) = rand(network(m+1), (network(m)+ 1))*(high-low) + low;
  endfor
endfunction