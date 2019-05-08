
function ret = tensor_product(v, w)
  ret = [];
  for k = [1:numel(v)]
    ret = [ret v(k)*w(k)];
  endfor
endfunction


