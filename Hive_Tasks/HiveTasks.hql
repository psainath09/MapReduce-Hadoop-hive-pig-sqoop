drop table housing1;
#Create table and load from hbase
CREATE EXTERNAL TABLE housing1(price int,date_ string,old string,duration string,place string,district string,county string,ppd string,recordstatus string,id int) ROW FORMAT SERDE 'org.apache.hadoop.hive.hbase.HBaseSerDe' STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping"=":key,details:price,details:date,details:old,details:duration,details:place,details:district,details:county,details:ppd,details:recordstatus") TBLPROPERTIES ("hbase.table.name"="housing_dataset1");

##Hive Task 1

INSERT OVERWRITE DIRECTORY  '/project/HiveTask_Output1' ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE SELECT duration,avg(price) FROM housing1 group by duration;

## Hive Task 2
INSERT OVERWRITE DIRECTORY  '/project/HiveTask_Output2' ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE SELECT county,max(price) FROM housing1 group by county;