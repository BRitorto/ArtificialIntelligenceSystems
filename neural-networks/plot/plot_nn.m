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