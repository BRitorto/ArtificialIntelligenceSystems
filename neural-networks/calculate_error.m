%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% This function takes a neural network and a series of patterns and returns the 
% overall error
%
% Parameters:
%
% W is a cell array of matrices. W{m}(j, i) holds the weight of the
% connection from unit i in the m-1 layer to unit j in the m layer
%
% patterns is a two dimentional cell array. patterns{i}{1} contains and input 
% pattern; patterns{i}{2} holds the expected output
%
% g is a two dimentional cell array of function handles. g{m}{1} is the the 
% activation function for layer m
%
% Return value:
%
% V is a cell array of column vectors. V{m}(i) holds the output of unit i at
% layer m-1
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