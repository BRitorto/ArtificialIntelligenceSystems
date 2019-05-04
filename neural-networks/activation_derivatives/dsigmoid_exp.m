% Derivative of the sigmoid function in terms of itself
function out = dsigm(in)
  out = in*(1-in);
endfunction