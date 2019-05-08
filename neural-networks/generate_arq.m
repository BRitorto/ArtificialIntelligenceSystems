function out=generate_arq()
  terrain = './terrains/terrain05.data';
  max_epochs = 30;
  sample_number = 400;
  hidden_layers = 1;
  
  for( i = [1:3])
    arq = zeros(1, i+2);
    arq(1) = 2; # Always 2 input nodes
    arq(i+2) = 1; #Always 1 output node
    
    for( j = [1:100])
      for( k = [2:i+1])
        arq(k) = randi(30);
      endfor
      arq
       # network_setup(terrain,max_epochs, sample_number, arq);
    endfor
  endfor
  
endfunction