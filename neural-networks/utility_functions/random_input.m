function ret = random_input(number_of_ands)
    ret = randi([0, 1], number_of_ands+1, 1);
    
    ret(ret == 0) = -1; #Transform every 0 into a 1
    ret(1,1) = -1; #First element is always -1 for the treshold value
endfunction
