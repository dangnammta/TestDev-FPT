/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.fpt.sqlorc.orc;

import java.io.IOException;

import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.Writer;
import org.apache.crunch.types.orc.OrcUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcStruct; 
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

//import org.apache.orc.MemoryManager;

//import org.apache.hadoop.hive.ql.io.orc.MemoryManager;

/**
 *
 * @author cuongnc
 */
public class OrcWriter {

	private Writer writer;
	
	private TypeInfo typeInfo;
	private Configuration configuration;
	private boolean isInit = false;
	private Path filePath;
	
	//private long maxDataSize = 10000;

	public OrcWriter(String schemStr, String savePath) throws Exception {
		init(schemStr, savePath);
	}

	private void init(String schemStr, String savePath) throws Exception {
		//read from configuration file
		//String schemStr = "struct<string_value:string,short_value:smallint,integer_value:int,long_value:bigint,double_value:double,float_value:float>";
		
		//String schemStr = "struct<tin:string,normname:string>";
		
		
		typeInfo = TypeInfoUtils.getTypeInfoFromTypeString(schemStr);
		ObjectInspector inspector = OrcStruct.createObjectInspector(typeInfo);
		
		configuration = new Configuration();
		filePath = new Path(savePath);
		MemoryManager memorymanager = new MemoryManager(configuration);
		OrcFile.WriterOptions wopts = OrcFile.writerOptions(configuration);
		//wopts.inspector(inspector).stripeSize(100000).bufferSize(10000).memory(memorymanager);
		wopts.inspector(inspector).stripeSize(100000).bufferSize(10000);
		
		writer = OrcFile.createWriter(filePath, wopts);
		isInit = true;
	}

	public boolean write(Object[] data) throws Exception {
		//System.out.println("Orc write: " + data.tin + " is init " + String.valueOf(isInit));
		if (isInit) {
			/*OrcStruct orcLine = OrcUtils.createOrcStruct(
				typeInfo,new Text(data.tin),
					new Text(data.normName));*/
			OrcStruct orcLine = OrcUtils.createOrcStruct(typeInfo, data);
			writer.addRow(orcLine);
			return true;
		}
		else {
			return false;
		}
	}

	
	public void close() throws Exception {
		writer.close();
		configuration = null;
		typeInfo = null;
		isInit = false;
	}
}
