/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.crdhn;

import vn.com.fpt.sqlorc.stream.StreamWriter;
import dev.crdhn.connector.DBConnector;
import dev.crdhn.metadata.MetadataUtils;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.avro.generic.GenericData;
import org.apache.crunch.types.orc.OrcUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcStruct;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.orc.TypeDescription;
//import org.apache.orc.Writer;
import org.apache.hadoop.hive.ql.io.orc.Writer;



import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import vn.com.fpt.sqlorc.orc.OrcWriter;

/**
 *
 * @author cuongnc
 */
public class Main {
	public static void main(String[] args) {
		try {
			// TODO code application logic here
			/*try {
			//for metadata get table name and type.
			//Connection connection = DBConnector.getConnection();
			//Global.getInstance().setDbMetadata(connection);
			String tableName = "DANHBA";
			
			DBConnector.getInstance().setDBInfo(DBConnector.DBType.SQLSERVER, "CucThue", "10.86.222.24", 1444, "sa", "123456abcA");
			
			
			
			Map<String,String> schema = MetadataUtils.getSchemaMap(tableName);
			
			Connection connection = DBConnector.getInstance().getConnection();
			String sql = DBConnector.getDataStatement(tableName);
			PreparedStatement ps = connection.prepareStatement(sql);
			
			StreamWriter st = new StreamWriter(ps, schema, "my-orc6.orc");
			st.run();
			}
			catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}*/
			
			
			//abc();
			//OrcWriter orc = new OrcWriter(), savePath)
			testCruch();
		}
		catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	

	public static void testCruch() throws IOException {
		String input = "text_string\t1\t2\t3\t123.4\t123.45";
		
		String typeStr = "struct<string_value:string,short_value:smallint,integer_value:int,long_value:bigint,double_value:double,float_value:float>";
		TypeInfo typeInfo = TypeInfoUtils.getTypeInfoFromTypeString(typeStr);
		ObjectInspector inspector = OrcStruct.createObjectInspector(typeInfo);

		String[] inputTokens = input.split("\\t");

		OrcStruct orcLine = OrcUtils.createOrcStruct(
				typeInfo,
				new Text(inputTokens[0]),
				new ShortWritable(Short.valueOf(inputTokens[1])),
				new IntWritable(Integer.valueOf(inputTokens[2])),
				new LongWritable(Long.valueOf(inputTokens[3])),
				new DoubleWritable(Double.valueOf(inputTokens[4])),
				new FloatWritable(Float.valueOf(inputTokens[5])));
		Configuration conf = new Configuration();
		Path tempPath = new Path("my-fileText7.orc");

		Writer writer = org.apache.hadoop.hive.ql.io.orc.OrcFile.createWriter(tempPath, org.apache.hadoop.hive.ql.io.orc.OrcFile.writerOptions(conf).inspector(inspector).stripeSize(100000).bufferSize(10000));
		writer.addRow(orcLine);
		writer.close();
	}
	
	public static void getUpdateChange(){
		try {
			String file = "my-changlog6.orc";
			
			if(!file.isEmpty()){
				File f = new File(file);
				if(f.exists() && !f.isDirectory()) { 
					f.delete();
				}
			}
			
			String structStr = "struct<string_value:string,integer_value:int>";
			String typeStr = "struct<timeOfChange:bigint,changeType:int,data:" +structStr + ">";
			
			//TypeDescription type = TypeDescription.fromString(typeStr);
			
			//OrcStruct row = (OrcStruct) OrcStruct.createObjectInspector(info)
			
			
			
			/*final String typeStr = "struct<b1:binary,b2:boolean,b3:tinyint," +
        "c:char(10),d1:date,d2:decimal(20,5),d3:double,fff:float,int:int," +
        "l:array<bigint>,map:map<smallint,string>," +
        "str:struct<u:uniontype<timestamp,varchar(100)>>,ts:timestamp>";*/
			
			//create struct:
			TypeInfo typeInfo = TypeInfoUtils.getTypeInfoFromTypeString(typeStr);
			ObjectInspector inspector = OrcStruct.createObjectInspector(typeInfo);
			
			
			Configuration conf = new Configuration();
			TypeDescription schema = TypeDescription.fromString(typeStr);
			
			
			OrcFile.WriterOptions wopts = OrcFile.writerOptions(conf);
			//wopts.inspector(inspector).stripeSize(100000).bufferSize(10000).memory(memorymanager);
			wopts.inspector(inspector).stripeSize(100000).bufferSize(10000);
			
			Writer writer = OrcFile.createWriter(new Path(file),wopts);
			
			final List<Object> record = new LinkedList<>();
			record.add(new LongWritable(1200L));
			record.add(new IntWritable(2));
			
			TypeInfo structTypeInfo = TypeInfoUtils.getTypeInfoFromTypeString(structStr);
			ObjectInspector structInspector = OrcStruct.createObjectInspector(structTypeInfo);
			//add Data:
			List data = new ArrayList();
			data.add(new Text("Alice"));
			data.add(new IntWritable(222));
			
			
			OrcStruct dataLine = OrcUtils.createOrcStruct(structTypeInfo, data.toArray());
			record.add(dataLine);
			
			//OrcUtils.createOrcStruct(typeInfo, record.toArray());
			
			OrcStruct recordLine = OrcUtils.createOrcStruct(typeInfo, record.toArray());
			
			
			writer.addRow(recordLine);
			writer.close();
			
		}
		catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void uploadOrcFile(){
		//name theo pattern : requestId-dbId-logtime
		//name theo pattern : requestId-dbId-logtime
		
		String _path = "/home/cuongnc/Documents/1-2-109768";
		String tableName = "bangdonvibaocao";
		String extensionName = ".tar.gz";
		
		
	}
}
