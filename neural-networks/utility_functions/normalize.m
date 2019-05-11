function out= normalize(patterns)
    max_value = getMax(patterns)
    min_value = getMin(patterns)
    row_num = size(patterns, 2);
    for i=[1: row_num]
        
        aux_value=patterns{i}{1}(1);
        patterns{i}{1}(1) = (aux_value - min_value) / (max_value - min_value);

        aux_value = patterns{i}{1}(2);
        patterns{i}{1}(2) = (aux_value - min_value) / (max_value - min_value);
        
        aux_value = patterns{i}{2};
        patterns{i}{2} = (aux_value - min_value) / (max_value - min_value);
   
    endfor 
    out = patterns;
endfunction