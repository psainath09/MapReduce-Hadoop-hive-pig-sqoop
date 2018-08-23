import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

public class UseCase2Mapper extends 
TableMapper<Text, Text>  {
	
	private Text text = new Text();
	private Text type= new Text();
   	public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
    	String val = new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("place")));
    	String vals = new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("duration")));
      	
    	text.set(val);
    	type.set(vals);
    	context.write(text, type);
	}

}
