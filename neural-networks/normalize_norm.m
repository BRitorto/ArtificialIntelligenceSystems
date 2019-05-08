function out=normalize_norm(patterns)
    row_num = size(patterns, 2);
    for i=[1:row_num]
      norma=norm(patterns{i}{1});
      if norma == 0
          norma = 1 
      endif
      patterns{i}{1}(1)=patterns{i}{1}(1)/norma;
      patterns{i}{1}(2)=patterns{i}{1}(2)/norma;
    endfor
    out=patterns;
endfunction
       















