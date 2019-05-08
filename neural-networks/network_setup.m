function out = network_setup(terrain_file, epochs, n_samples, arq)
    W = generate_weights_random(arq);
    full_patterns = load_file(terrain_file);
    normalized_patterns = normalize(full_patterns);
    test_patterns = randomize_patterns(normalized_patterns, n_samples);
    g = {{@tanh, @dtanh}, {@tanh, @dtanh}, {@(x) x, @(x) 1}};
    out = learn(W, test_patterns, g, eta=0.03, epochs, false, random_pass = false, momentum = 0.9, aep = [], with_error = true);
    %plot_nn(out{1}, full_patterns, g); 
endfunction