%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% This function takes a neural network and an input pattern and returns the 
% output at every layer
%
% Parameters:
%
% W is a cell array of matrices. W{m}(j, i) holds the weight of the
% connection from unit i in the m-1 layer to unit j in the m layer
%
% E is a column vector. E(i) holds the input for unit i
%
% g is a two dimentional cell array of function handles. g{m}{1} is the the 
% activation function for layer m
%
% Return value:
%
% V is a cell array of column vectors. V{m}(i) holds the output of unit i at
% layer m-1
function V = run_pattern(W, E, g)
  cant_layers = numel(W);
  V = cell(cant_layers+1, 1);
  V{1} = E;
  for k = [1:cant_layers]
    V{k} = [-1; V{k}];
    V{k+1} = arrayfun(g{k}{1}, W{k}*V{k});
  endfor
endfunction