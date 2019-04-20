clear all;
1;
function step= unit_step(v)
    step=0;
    if(v >= 0)
        step=1;
    else
        step=-1;
    endif
endfunction


function p_return = perceptron(x, w)
    v = x*w
    for i = 1:rows(v(:))
        y = unit_step(v(i))
    endfor
    p_return = y;
endfunction

x = [-1 0 0; -1 0 1; -1 1 0; -1 1 1];
w = [1.5; 1;1];
val = perceptron(x, w)


