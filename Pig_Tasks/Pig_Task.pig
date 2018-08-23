records = LOAD 'housing_dataset'
USING PigStorage(',') AS (price: INT,date: chararray,old: chararray, duration: chararray, place:chararray,district:chararray,county:chararray,ppd:chararray,recordstatus:chararray,id:int);
Grouped_records = GROUP records BY county;

Sum_Num = FOREACH Grouped_records GENERATE TOP(1, 0, records.county), COUNT(records.county);

STORE Sum_Num INTO '/home/hduser/shared/project/satya/Pig_Tasks/Task1_Output' USING PigStorage(',');
