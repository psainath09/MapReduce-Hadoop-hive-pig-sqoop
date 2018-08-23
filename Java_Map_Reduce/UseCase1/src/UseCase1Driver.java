import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;


public class UseCase1Driver {
	public static void main(String[] args) throws Exception {
		Configuration conf = HBaseConfiguration.create();
	    DBConfiguration.configureDB(conf, "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/satya", "root","sainath09");
		Job job = Job.getInstance(conf,"Generating count of variables");
		job.setJarByClass(UseCase1Driver.class);
		Scan scan = new Scan();
		scan.setCaching(500);
		scan.setCacheBlocks(false);
		TableMapReduceUtil.initTableMapperJob(
				"housing_dataset1",      
				scan,	          
				UseCase1Mapper.class,   
				Text.class,	         
				FloatWritable.class,
				job);
		 job.setReducerClass(UseCase1Reducer.class);
	     job.setOutputKeyClass(DBOutputWritable.class);
	     job.setOutputValueClass(NullWritable.class);
	     job.setOutputFormatClass(DBOutputFormat.class);

		DBOutputFormat.setOutput(
				 job,
			     "usecase1",    // output table name
			     new String[] { "data", "value" }   //table columns
			     );

		job.setNumReduceTasks(1);
		boolean b = job.waitForCompletion(true);
		if (!b) {
		    throw new IOException("error with job!");
		}
		}	
}
