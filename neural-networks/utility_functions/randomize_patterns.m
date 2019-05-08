%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% This function takes the whole sample of patterns and a number 'n' and returns
% a randomly trimmed version of lenght n of the sample.
%
% Parameters:
%
% patterns is a two dimentional cell array. patterns{i}{1} contains an input 
% pattern; patterns{i}{2} holds the expected output.
%
% n is an integer representing the lenght of the output.
%
% Output:
%
% out is a two dimentional cell array of lenght n. out{i}{1} contains an input 
% pattern; out{i}{2} holds the expected output.

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