1;# Script file?? Maybe this should be a function file

terrain = './terrains/terrain05.data';
max_epochs = 100;
sample_number = 300;
arq = [2 10 5 1];

addpath('./activation_derivatives')
addpath('./activation_functions')
addpath('./utility_functions')
addpath('./terrains')

network_setup(terrain, max_epochs, sample_number, arq);

