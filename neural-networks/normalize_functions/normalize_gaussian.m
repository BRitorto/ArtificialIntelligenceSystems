function out = normalize_gaussian(patterns)
    values_in_array = get_values_in_array(patterns);
    st_dev = std(values_in_array);
    mn = mean(values_in_array);
    row_num = size(patterns, 2);
    
    for i=[1: row_num]
        
        aux_value=patterns{i}{1}(1);
        patterns{i}{1}(1) = (aux_value - mn) / (st_dev);

        aux_value = patterns{i}{1}(2);
        patterns{i}{1}(2) = (aux_value - mn) / (st_dev);
        
        aux_value = patterns{i}{2};
        patterns{i}{2} = (aux_value - mn) / (st_dev);
    endfor
    out = patterns;
endfunction