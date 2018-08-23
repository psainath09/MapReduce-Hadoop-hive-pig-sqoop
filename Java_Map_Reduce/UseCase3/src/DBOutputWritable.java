import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class DBOutputWritable implements Writable, DBWritable
{
   private String name;
   private double max;
   private double min;

   public DBOutputWritable(String name, double max,double min)
   {
     this.name = name;
     this.max = max;
     this.min=min;
   }

   public void readFields(DataInput in) throws IOException {   }

   public void readFields(ResultSet rs) throws SQLException
   {
     name = rs.getString(1);
     max = rs.getInt(2);
     min=rs.getInt(3);
   }

   public void write(DataOutput out) throws IOException {    }

   public void write(PreparedStatement ps) throws SQLException
   {
     ps.setString(1, name);
     ps.setInt(2, (int) max);
     ps.setInt(3,(int) min);
   }
}