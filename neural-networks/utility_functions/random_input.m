function ret = random_input(number_of_ands)
    ret = randi([0, 1], number_of_ands+1, 1);
    
    ret(ret == 0) = -1; 
    ret(1,1) = -1; 
endfunction
