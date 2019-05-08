% Derivative of the sigmoid function in terms of itself
function out = dsigmoid_exp(in)
  out = in*(1-in);
endfunction