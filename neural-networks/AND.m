#Make sure octave realizes this is a script file;
clear all

addpath('./utility_functions')
addpath('./activation_functions')

input_number = 4;
get_output = @get_expected_output_and;
activation_fun = @step;

single_layer_perceptron(input_number, get_output, activation_fun)
