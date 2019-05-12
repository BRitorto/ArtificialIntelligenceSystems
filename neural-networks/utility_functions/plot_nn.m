% This function takes a series of patterns and plots what the neural network 
%   thinks the answers are.
%
% Parameters:
%
% W is a cell array of matrices. W{m}(j, i) holds the weight of the
%   connection from unit i in the m-1 layer to unit j in the m layer
%
% g is a two dimentional cell array of function handles. g{m}{1} is the the 
%   activation function for layer m; g{m}{2} is the derivative of g{m}{1} in 
%   terms of g (for example, if g{m}{1}(x) = tanh(x), then g{m}{2}(x) = 1-x^2
%
% patterns is a two dimentional cell array. patterns{i}{1} contains and input 
%   pattern.

function plot_nn(trained_weights, test_patterns, g, terrain_file)
  x = [-2:0.2:2];
  y = x;
  for i = [1: length(x)]
    for j = [1: length(y)]
      V = run_pattern(trained_weights, [x(i);y(j)], g);
      z(i, j) = V{numel(V)};
    endfor
  endfor
 
  data =dlmread(terrain_file,'',1,0);
  length = length(data / 3);
    xo = data(1:length);
    yo = data(length+1: length*2);
    zo = data(length*2 + 1: length*3);

  figure(1)
  surf(x, y, z)
  hold on;
  plot3(xo,yo,zo,'o')
  title("Terreno realizado por la red")
  xlabel("X")
  ylabel("Y")
  zlabel("Z")

end