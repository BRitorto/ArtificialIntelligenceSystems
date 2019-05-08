function answer = learn(W, patterns, g, eta, cant_epochs, is_batch, is_random_approach, momentum, adaptative_eta, record_error)
  cant_layers = numel(W);
  cant_patterns = numel(patterns);
  
  batch_delta_W = cell(cant_layers, 1);
  % last_delta_W will hold the weight updates for the previous update (to implement momentum)
  last_delta_W = cell(cant_layers,1);
  % last_error will hold the error of the last epoch (to implement adaptative eta)
  last_error = Inf;
  % consecutive_success will hold the the consecutive number of cant_epochs during which the
  % learning has been successful
  consecutive_success = 0;
  % error_array(i) will hold the global error at the end of epoch i
  error_array = zeros(cant_epochs, 1);
  
  for i = [1:cant_layers]
    last_delta_W{i} = zeros(rows(W{i}), columns(W{i}));
  endfor
  
  for k = [1:cant_epochs] 
    % Permute the patterns array uniformly if requested
    if (is_random_approach)
      for i = [cant_patterns:-1:2]
        j = floor((unifrnd(1, cant_patterns+1)-1)*0.99999+1);
        temp = patterns{i};
        patterns{i} = patterns{j};
        patterns{j} = temp;
      endfor
    endif

    % Initialize batch_delta_W, which will accumulate the weight changes for one whole epoch
    if (is_batch)
      for i = [1:cant_layers]
        batch_delta_W{i} = zeros(rows(W{i}), columns(W{i}));
      endfor
    endif

    % Run each pattern once
    for p = [1:cant_patterns]
      delta_W = run_and_correct(W, patterns{p}{1}, g, patterns{p}{2}, eta);
      if (is_batch)
        for i = [1:cant_layers]
          batch_delta_W{i} += delta_W{i};
        endfor
      else % is_incremental
        for i = [1:cant_layers]
          W{i} += delta_W{i} + momentum*last_delta_W{i};
        endfor
        last_delta_W = delta_W;
      endif
    endfor

    % batch update
    if (is_batch)
      for j = [1:cant_layers]
        W{j} += batch_delta_W{j} + momentum*last_delta_W{j};
      endfor
      last_delta_W = batch_delta_W;
    endif

    % calculate error
    error = 0;
    if (record_error)
      error = calculate_error(W, patterns, g);
      error_array(k) = error;
    endif

    % adaptative eta
    if (adaptative_eta)
      if (record_error)
        error = calculate_error(W, patterns, g);
      endif
      if (error < last_error)
        consecutive_success++;
        if (consecutive_success == adaptative_eta(3))
          eta = eta*adaptative_eta(1);
          consecutive_success = 0;
          momentum = 0.9;
        endif
      else
        consecutive_success = 0;
        if (error > last_error)
          eta = eta*adaptative_eta(2);
          momentum = 0;
        endif
      endif
      last_error = error;
    endif
      error
  endfor
  
  answer = cell(2,1);
  answer{1} = W;
  if (record_error)
    answer{2} = error_array;
  else
    answer{2} = [];
  endif
endfunction