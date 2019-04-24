function ret = sigmoid_exp(v)
    aux = 1/(1+exp(-v));
    if (aux > 0.5)
        ret = 1;
    else
        ret = -1;
    endif

endfunction