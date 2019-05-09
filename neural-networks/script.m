1;# Script file?? Maybe this should be a function file
addpath('./activation_derivatives')
addpath('./activation_functions')
addpath('./utility_functions')
addpath('./terrains')

terrain_file = './terrains/terrain05.data';
max_epochs = 100;
sample_number = 200;
global arq = [2 10 5 1];
global eta = 0.03;
global W = generate_weights_random(arq);
global g = {{@tanh, @dtanh}, {@tanh, @dtanh}, {@(x) x, @(x) 1}};

network_setup(terrain_file, max_epochs, sample_number);

