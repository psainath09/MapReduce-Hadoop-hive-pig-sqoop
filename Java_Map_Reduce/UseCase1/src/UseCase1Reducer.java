import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;;

public class UseCase1Reducer extends
       Reducer<Text, FloatWritable, DBOutputWritable, NullWritable>{
	
	 protected void reduce(Text key, Iterable<FloatWritable> values, Context ctx)
	   {
	     float avg = 0;
         int count=0;
         float sum=0;
	     for(FloatWritable value : values)
	     {
	       sum += value.get();
	       count = count + 1;
	     }

avg=sum/count;
	     try
	     {
	    	 ctx.write(new DBOutputWritable(key.toString(), avg), NullWritable.get());
	     } catch(IOException e)
	     {
	       e.printStackTrace();
	     } catch(InterruptedException e)
	     {
	       e.printStackTrace();
	     }
	   }

}
