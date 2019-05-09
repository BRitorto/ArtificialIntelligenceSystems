function info = load_file(terrain)
    data = dlmread(terrain,'',1,0);
    
    length = length(data / 3);
    x = data(1:length);
    y = data(length+1: length*2);
    z = data(length*2 + 1: length*3);

    
    info = {};
    for k = [1:length]
        info{k}{1} = [x(k) ; y(k)];
        info{k}{2} = z(k);
    end
    info 
endfunction
