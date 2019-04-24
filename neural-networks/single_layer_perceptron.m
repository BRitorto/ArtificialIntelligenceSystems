function single_layer_perceptron(input_number, get_expected_output, activation_fun)
    maximum_epochs = 2
    current_epoch = 1;
    weights = rand(1, input_number+1); # Generates a 1x( input_number + 1) matrix of random numbers
    learning_factor = 0.1

    # We are going to use random training data which we are 
    # going to call training_input
    printf("STARTING TRAINING with %d epochs\n", maximum_epochs);
    while(current_epoch <= maximum_epochs)
        training_input = random_input(input_number);
        expected_output = get_expected_output(training_input, input_number);
        obtained_output = activation_fun(weights * training_input);
        if (obtained_output != expected_output)
            for index= 1:  input_number+1
                weights(1, index) = weights(1, index) + learning_factor * (1- expected_output*obtained_output) * expected_output * training_input(index, 1);
            endfor
        endif
        current_epoch = current_epoch + 1;
    endwhile

    printf("TRAINING ENDED\n");

    printf("TESTING OBTAINED WEIGHTS:\n");
    test_qty = 10000;
    failed_tests = 0;

    for test_num=1 : test_qty
        test_input = random_input( input_number);
        expected_output = get_expected_output(test_input,  input_number);
        obtained_output = activation_fun(weights* test_input);
        if(obtained_output != expected_output)
            failed_tests = failed_tests + 1;
        endif
    endfor

    printf("FAILED %d / %d\n", failed_tests, test_qty)

endfunction