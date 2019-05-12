function out = randomize_patterns(patterns,n)
  m = numel(patterns);
  for i = [m:-1:2]
    j = floor((unifrnd(1, m+1)-1)*0.99999+1);
    temp = patterns{i};
    patterns{i} = patterns{j};
    patterns{j} = temp;
  endfor  
  out = ceil(n);
  out = {patterns{1:n}};
endfunction

function out = randomize_patterns1(patterns,n)
  m = numel(patterns);
  for i = [m:-1:2]
    j = floor((unifrnd(1, m+1)-1)*0.99999+1);
    temp = patterns{i};
    patterns{i} = patterns{j};
    patterns{j} = temp;
  endfor  
  out{1} = ceil(n);
  out{1} = {patterns{1:n}};
  out{2} = {patterns{n+1:m}};
endfunction