function out = network_setup()

    global terrain_file;
    global sample_number;
    global test_patterns;
    global train_patterns;
    global result;
    global trained_weights;
    global normalize_fun;
    global is_batch;
    global is_random_approach;
    global adaptative_eta;
    global record_error;
    global g;

    full_patterns = load_file(terrain_file);
    %normalized_patterns = normalize_fun(full_patterns);
    patterns = randomize_patterns(full_patterns, sample_number);
    %train_patterns = patterns{1};
    %test_patterns = patterns{2};
    out = learn(patterns, is_batch, is_random_approach, adaptative_eta, record_error);    
    trained_weights = out{1};
    plot_nn(trained_weights, full_patterns, g);
endfunction