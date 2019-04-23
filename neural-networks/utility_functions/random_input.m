function ret = random_input
    ret = randi([0, 1]);
    if (ret == 0)
        ret = -1;
    endif
endfunction
