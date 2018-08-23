import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;



public class UseCase3Mapper extends 
TableMapper<Text, IntWritable>  {
	
	private Text text = new Text();
	private IntWritable price=new IntWritable();

   	public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
    	String val = new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("district")));
    	String pri= new String(value.getValue(Bytes.toBytes("details"), Bytes.toBytes("price")));
      	text.set(val);
      	price.set(Integer.parseInt(pri));
    	context.write(text, price);
	}
	}


