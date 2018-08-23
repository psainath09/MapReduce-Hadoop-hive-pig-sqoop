import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBOutputWritable implements Writable, DBWritable
{
   private String county;
   private float average;

   public DBOutputWritable(String county, float average)
   {
     this.county = county;
     this.average = average;
   }

   public void readFields(DataInput in) throws IOException {   }

   public void readFields(ResultSet rs) throws SQLException
   {
	   county = rs.getString(1);
     average = rs.getFloat(2);
   }

   public void write(DataOutput out) throws IOException {    }

   public void write(PreparedStatement ps) throws SQLException
   {
     ps.setString(1, county);
     ps.setFloat(2, average);
   }
}