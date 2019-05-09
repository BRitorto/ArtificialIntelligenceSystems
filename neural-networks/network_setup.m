function out = network_setup(terrain_file, max_epochs, sample_number)
    global arq;
    global W;
    global g;
    global eta;
    full_patterns = load_file(terrain_file);
    normalized_patterns = normalize(full_patterns);
    test_patterns = randomize_patterns(normalized_patterns, sample_number);
    out = learn(W,test_patterns, g,eta,max_epochs, false, random_pass = false, momentum = 0.9, aep = [], with_error = true);
    
    result = out{1};
    plot_nn(result, full_patterns, g); 
endfunction