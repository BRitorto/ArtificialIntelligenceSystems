function out = network_setup()

    global terrain_file;
    global sample_number;

    full_patterns = load_file(terrain_file);
    normalized_patterns = normalize(full_patterns);
    test_patterns = randomize_patterns(normalized_patterns, sample_number);
    out = learn(test_patterns, false, random_pass = false, aep = [], with_error = true);
   
    result = out{1};
    plot_nn(result, full_patterns, g); 
endfunction