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
  global general_error;
  global z_calculated;
  
  x = [-3:0.2:3];
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
  
  for i = [1:numel(test_patterns)]
    xt(i) = test_patterns{i}{1}(1);
    yt(i) = test_patterns{i}{1}(2);
    zt(i) = test_patterns{i}{2}(1);
  endfor
  
  for k = [1: numel(test_patterns)]
    test_results = run_pattern(trained_weights, [test_patterns{k}{1}(1);test_patterns{k}{1}(2)], g);
    z_calculated(k) = test_results{numel(test_results)};
  endfor
  
  general_error = mean((zt - z_calculated).^2);
  figure(1)
  surf(x, y, z)
  %hold on;
  %plot3(yo, xo, zo,'ob')
  hold on;
  plot3(yt, xt, zt, 'or')
  title("Terreno realizado por la red")
  xlabel("X")
  ylabel("Y")
  zlabel("Z")

end