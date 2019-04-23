
function ret = get_expected_output(training_input)
    if( training_input(2,1) == 1 && training_input(3,1) == 1)
        ret = 1;
    else
        ret = -1;
    endif
endfunction