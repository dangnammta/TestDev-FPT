/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.fpt.sqlorc.stream;

import vn.com.fpt.sqlorc.orc.OrcWriter;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;

/**
 *
 * @author cuongnc
 */
public class StreamWriter {

	/**
	 * @param args the command line arguments
	 */
	private PreparedStatement _ps;
	private final OrcWriter _writer;
	private Map<String, String> _schema;
	private String _path = null;

	public StreamWriter(PreparedStatement pstm, Map<String, String> schema, String savePath) throws Exception {
		_schema = schema;
		_ps = pstm;
		_path = savePath;
		deleteExistedSaveFile();
		_writer = new OrcWriter(getOrcSchemaString(), _path);

	}
	
	private void deleteExistedSaveFile(){
		if(!_path.isEmpty()){
			File f = new File(_path);
			if(f.exists() && !f.isDirectory()) { 
				f.delete();
			}
		}
	}

	public StreamWriter setSavePath(String path) {
		_path = path;
		deleteExistedSaveFile();
		return this;
	}

	public StreamWriter setPreparedStatement(PreparedStatement ps) {
		_ps = ps;
		return this;
	}

	public StreamWriter setSchema(Map<String, String> schema) {
		_schema = schema;
		return this;
	}

	public final String getOrcSchemaString() {
		String schemaStr = "struct<";
		for (Map.Entry<String, String> entry : _schema.entrySet()) {
			if (!"struct<".equals(schemaStr)) {
				schemaStr = schemaStr + ",";
			}
			schemaStr = schemaStr + entry.getKey() + ":" + entry.getValue();
		}
		schemaStr = schemaStr + ">";
		return schemaStr;
	}

	public void run() {
		try (Stream<List> dataStream = new ResultSetStream<List>().getStream(_ps, (ResultSet rs) -> {
			try {
				//Object[] data;
				List data = new ArrayList();
				
				
				
//				
//				_schema.entrySet().stream().forEach((entry) -> {
//					String key = entry.getKey();
//					String valueType = entry.getValue();
//					//more type here
//					if ("string".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new Text(rs.getString(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new Text());
//						}
//					}
//					else if ("boolean".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new BooleanWritable(rs.getBoolean(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new ShortWritable());
//						}
//					}
//					else if ("tinyint".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new ByteWritable(rs.getByte(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new ShortWritable());
//						}
//					}
//					else if ("smallint".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new ShortWritable(rs.getShort(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new ShortWritable());
//						}
//					}else if ("int".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new IntWritable(rs.getInt(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new IntWritable());
//						}
//					}else if ("bigint".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new LongWritable(rs.getLong(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new LongWritable());
//						}
//					}else if ("double".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new DoubleWritable(rs.getDouble(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new DoubleWritable());
//						}
//					}else if ("float".equalsIgnoreCase(valueType)) {
//						try{
//							data.add(new FloatWritable(rs.getFloat(key)));
//						}catch(Exception ex){
//							System.out.println("Data Exception: " + ex.getMessage());
//							data.add(new FloatWritable());
//						}
//					}
//					//timestamp
//					
//					//date
//				});
				//System.err.println(data.get(0).toString());
				return data;
			}
			catch (Exception ex) {
				System.out.println("GetStream Exception: " + ex.getMessage());
				return null;
			}
		})) {

			Iterator<List> datas = dataStream.iterator();
			System.out.println("Start process data DATA ");
			while (datas.hasNext()) {
				List dt = datas.next();
				if (dt != null) {
					_writer.write(dt.toArray());
				}
				else {
					break;
				}
			}
			_writer.close();
			System.out.println("Stream done ");
		}
		catch (Exception ex) {
			System.out.println("Exception " + ex.getMessage());
			Logger.getLogger(StreamWriter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
