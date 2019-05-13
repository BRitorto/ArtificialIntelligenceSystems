1;# Script file
clear all;
more off; % Ir imprimiendo le error en real time

addpath('./activation_derivatives')
addpath('./activation_functions')
addpath('./utility_functions')
addpath('./normalize_functions')
addpath('./terrains')
addpath('./plot')

debug_on_interrupt(1);
source('network_parameters.bin');

network_setup();
plot_original(terrain_file)

