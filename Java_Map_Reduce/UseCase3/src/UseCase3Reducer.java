import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;;

public class UseCase3Reducer extends
       Reducer<Text, IntWritable, DBOutputWritable, NullWritable>{
	
	 protected void reduce(Text key, Iterable<IntWritable> values, Context ctx)
	   {
		 double min = Integer.MAX_VALUE, max = 0;

		 Iterator<IntWritable> iterator = values.iterator(); //Iterating

		 while (iterator.hasNext()) {

		 double value = iterator.next().get();

		 if (value < min) { //Finding min value

		 min = value;

		 }

		 if (value > max) { //Finding max value

		 max = value;

		 } }

		 
	     try
	     {
	    	 ctx.write(new DBOutputWritable(key.toString(), max,min), NullWritable.get());
	     } catch(IOException e)
	     {
	       e.printStackTrace();
	     } catch(InterruptedException e)
	     {
	       e.printStackTrace();
	     }
	   }

}
