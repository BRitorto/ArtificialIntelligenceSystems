

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