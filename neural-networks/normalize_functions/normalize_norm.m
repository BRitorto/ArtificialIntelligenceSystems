function out = normalize_norm(full_patterns)
  global norma;
    row_num = size(full_patterns, 2);
    for i=[1:row_num]
      aux_pattern = [full_patterns{i}{1}(1), full_patterns{i}{1}(2), full_patterns{i}{2}];
      norma{i} = norm(aux_pattern);
      if norma{i} == 0
          norma{i} = 1;
      endif
      full_patterns{i}{1}(1) = full_patterns{i}{1}(1)/norma{i};
      full_patterns{i}{1}(2) = full_patterns{i}{1}(2)/norma{i};
      full_patterns{i}{2} = full_patterns{i}{2}/norma{i};
    endfor
    out = full_patterns;
endfunction
       















