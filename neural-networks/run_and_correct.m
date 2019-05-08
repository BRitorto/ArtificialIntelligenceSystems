%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% This function takes a neural network, a training pattern and a learning rate
% and calculates the weight updates according to the back-propagation rule
%
% Parameters:
%
% W is a cell array of matrices. W{m}(j, i) holds the weight of the
% connection from unit i in the m-1 layer to unit j in the m layer
%
% E is a column vector. E(i) holds the input for unit i
%
% g is a two dimentional cell array of function handles. g{m}{1} is the the 
% activation function for layer m; g{m}{2} is the derivative of g{m}{1} in 
% terms of g (for example, if g{m}{1}(x) = tanh(x), then g{m}{2}(x) = 1-x^2
%
% S is the expected output as a column vector
%
% eta is the learning rate
%
% Return value:
%
% delta_W is a cell array of matrices. delta_W{m} contains the updates to be added to the 
% weight matrix at layer m
function delta_W = run_and_correct(W, E, g, S, eta)
  % M is the number of layers
  cant_layers = numel(W);
  V = run_pattern(W, E, g);
  
  % ders is a cell array. ders{m}(i) contains the derivative of the mth layer 
  % activation function evaluated at the ith unit's h
  derivatives = V;
  derivatives = apply_derivative(derivatives, V, g, cant_layers);
  
  % delta is a cell array.
  delta = cell(cant_layers, 1);
  delta_W = backpropagation(delta, W, S, V, derivatives, cant_layers, eta);
endfunction

function delta_W = backpropagation(delta, W, S, V, derivatives, cant_layers, eta)
  delta{cant_layers} = S - V{cant_layers+1};
  for k = [cant_layers-1:-1:1]
    delta{k} = (((W{k+1}')*delta{k+1})(2:end).*derivatives{k+1});
  endfor
  % delta_W is a cell array of matrices; delta_W{m} contains the weight update matrix for 
  % layer m
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