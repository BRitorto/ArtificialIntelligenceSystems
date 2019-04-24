#Make sure octave realizes this is a script file;
clear all

addpath('./utility_functions')
addpath('./activation_functions')

function ret = get_expected_output(training_input, number_of_ors)
    if(sum(training_input) + 1 == -number_of_ors)
        ret = -1;
    else
        ret = 1;
    endif
endfunction

input_number = 5
get_output = @get_expected_output;
activation_fun = @step;

single_layer_perceptron(input_number, get_output, activation_fun)
