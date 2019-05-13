function plot_original(terrain_file)
  data =dlmread(terrain_file,'',1,0);
  length = length(data / 3);
    x = data(1:length);
    y = data(length+1: length*2);
    z = data(length*2 + 1: length*3);
  
   figure(3)
   plot3(y,x,z,'o')
   title("Terreno original")
   xlabel("X")
   ylabel("Y")
   zlabel("Z")
   
endfunction
