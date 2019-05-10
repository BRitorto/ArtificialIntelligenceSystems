1;# Script file?? Maybe this should be a function file
clear all;
addpath('./activation_derivatives')
addpath('./activation_functions')
addpath('./utility_functions')
addpath('./terrains')

debug_on_interrupt(1);
source('network_parameters.bin');

network_setup();
plot_original(terrain_file)

