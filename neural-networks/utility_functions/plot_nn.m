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

function plot_nn(result, full_patterns, g)
  x = [-2:0.01:2];
  y = x;
  for i = [1: length(x)]
    for j = [1: length(y)]
      V = run_pattern(result, [x(i);y(j)], g);
      z(i, j) = V{numel(V)};
    endfor
  endfor
 
  %title ("Neural network's interpretation");
   %subplot (2, 1, 1)
   %subplot (2, 1, 2)
   %plot3(x, y, z_n, ".");
   %rotate3d
   %surf(x, y, z_n)
   for i = [1: 441]
      xo(i) = full_patterns{i}{1}(1);
      yo(i) = full_patterns{i}{1}(2);
      zo(i) = full_patterns{i}{2};
    endfor
   surf(x, y, z);
   %hold on;
   %plot3(xo, yo, zo, ".");

end