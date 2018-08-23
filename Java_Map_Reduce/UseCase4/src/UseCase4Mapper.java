import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class UseCase4Mapper extends 
TableMapper<Text, IntWritable>  {
	
	private Text text = new Text();
	
   	public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
    	String val = new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("old")));
      	text.set(val);
    	context.write(text, new IntWritable(1));
	}

}
