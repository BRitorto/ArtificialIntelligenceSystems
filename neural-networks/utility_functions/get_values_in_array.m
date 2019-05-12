function out = get_values_in_array(full_patterns)
    row_num = size(full_patterns, 2);
    arr = zeros(row_num*3, 1);
    arr_index = 1;
    current_max=0;
    for i=[1: row_num]
        arr(arr_index) = full_patterns{i}{1}(1);
        arr_index+=1;
        arr(arr_index) = full_patterns{i}{1}(2);
        arr_index+=1;
        arr(arr_index) = full_patterns{i}{2};
        arr_index+=1;
    endfor 
    out = arr;
endfunction