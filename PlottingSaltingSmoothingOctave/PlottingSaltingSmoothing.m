function main()
    generateFunctionValues();
    saltData();
    smoothData();
end

function generateFunctionValues()
    n = input('Enter the amount of times you''d like x to run: ');
    fileName = input('Name for your csv file (include .csv & don''t reuse current file names): ', 's');
    xValues = (1:n)';
    yValues = xValues.^2 + 0.5 * xValues;
    fprintf('X,Y\n');
    for i = 1:length(xValues)
        fprintf('%.0f,%.2f\n', xValues(i), yValues(i));
    end
    figure;
    plot(xValues, yValues, 'b-o');
    title('Normal Data');
    xlabel('x');
    ylabel('y');
    exportCSVFile(xValues, yValues, fileName);
end

function exportCSVFile(xValues, yValues, filename)
    data = [xValues yValues];
    fid = fopen(filename, 'w');
    fprintf(fid, 'x,y\n');
    fclose(fid);
    dlmwrite(filename, data, '-append');
    fprintf('Exported to: %s\n', filename);
end

function [xValues, yValues] = loadCSVFile(filename)
    data = csvread(filename, 1, 0);
    xValues = data(:,1);
    yValues = data(:,2);
end

function saltData()
    fileName = input('Enter a file you''d like to salt: ', 's');
    [xValues, yValues] = loadCSVFile(fileName);

    meanY = mean(yValues);
    defaultSaltRange = meanY * 0.5;

    saltRangeInput = input(['Enter the preferred salt value (hit Enter for default ' num2str(defaultSaltRange) '): '],'s');
    if isempty(saltRangeInput)
        saltRange = defaultSaltRange;
    else
        saltRange = str2double(saltRangeInput);
    end

    % Use normal (Gaussian) noise, like Java's nextGaussian()
    salt = randn(size(yValues)) * saltRange;
    ySalted = yValues + salt;

    exportCSVFile(xValues, ySalted, 'saltedValues.csv');
    figure;
    plot(xValues, ySalted, 'b-o');
    title('Salted Data');
    xlabel('x');
    ylabel('y');
    disp('Salted data exported. Viewable in the files panel to the left.');
end

function smoothData()
    fileName = input('Enter the file name you''d like to smooth: ', 's');
    windowSize = input('Enter the window size (Ex. 5 means +/-5): ');
    [xValues, yValues] = loadCSVFile(fileName);

    n = length(yValues);
    smoothedY = zeros(n,1);

    for i = 1:n
        weightedSum = 0;
        totalWeight = 0;
        for j = -windowSize:windowSize
            idx = i + j;
            if idx >= 1 && idx <= n
                weight = windowSize + 1 - abs(j);
                weightedSum = weightedSum + yValues(idx) * weight;
                totalWeight = totalWeight + weight;
            end
        end
        smoothedY(i) = weightedSum / totalWeight;
    end

    exportCSVFile(xValues, smoothedY, 'smoothedValues.csv');
    figure;
    plot(xValues, smoothedY, 'b-o');
    title('Smoothed Data');
    xlabel('x');
    ylabel('y');
    disp('Smoothed data exported. Viewable in the files panel to the left.');
end