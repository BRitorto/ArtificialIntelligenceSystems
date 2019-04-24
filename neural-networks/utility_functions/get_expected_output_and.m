
function ret = get_expected_output(training_input, number_of_ands)
    if(sum(training_input) == number_of_ands)
        ret = 1;
    else
        ret = -1;
    endif
endfunction