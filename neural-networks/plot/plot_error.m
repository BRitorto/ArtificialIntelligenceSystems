function plot_error(error_array)
    cant=numel(error_array);
    x=[1:1:cant];
    graphics_toolkit('gnuplot');
    figure(2)
    refresh(2)
    plot(x,error_array)
    title("Grafico del error")
    ylabel("Error")

endfunction
