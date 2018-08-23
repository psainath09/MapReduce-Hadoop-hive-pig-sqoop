import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;;

public class UseCase2Reducer extends
       Reducer<Text, Text, DBOutputWritable, NullWritable>{
	
	 protected void reduce(Text key, Iterable<Text> values, Context ctx)
	   {
	     int news = 0;
         int old=0;
         //val t = new Text("Y");
        Text n=new Text("Y");
         Text o=new Text("N");
	     for(Text value : values)
	     {
	    	 
	    	 if(value.equals(n))
	    	 {
	    		 news +=1;
	    	 }
	    	 else if(value.equals(o)) {
	    		 old +=1;
	    	 }
	     }

	     try
	     {
	    	 ctx.write(new DBOutputWritable(key.toString(), news,old), NullWritable.get());
	     } catch(IOException e)
	     {
	       e.printStackTrace();
	     } catch(InterruptedException e)
	     {
	       e.printStackTrace();
	     }
	   }

}
