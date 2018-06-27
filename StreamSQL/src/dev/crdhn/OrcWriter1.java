/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.crdhn;

import java.io.IOException;
import org.apache.hadoop.fs.FileSystem;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;

import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;




//import org.apache.hadoop.hive.ql.io.orc.OrcFile;
//import org.apache.hadoop.hive.ql.io.orc.Writer;
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

/**
 *
 * @author cuongnc
 */
public class OrcWriter1 {

	private Writer writer;
	private TypeDescription schema;
	private Configuration configuration;
	private boolean isInit = false;

	public OrcWriter1() throws Exception {
		init();
	}

	private void init() throws Exception {
		//read from configuration file
		/*configuration = new Configuration();
		schema = TypeDescription.fromString("struct<tin:string,normName:string>");
		writer = OrcFile.createWriter(new Path("my-file.orc"),
				OrcFile.writerOptions(configuration).setSchema(schema));

		isInit = true;*/
	}

	public boolean write(String data) throws Exception {
		System.out.println("Orc write: " + data + " is init " + String.valueOf(isInit));
		if (isInit) {
			VectorizedRowBatch batch = schema.createRowBatch();
			BytesColumnVector x = (BytesColumnVector) batch.cols[0];
			BytesColumnVector y = (BytesColumnVector) batch.cols[1];

			int row = batch.size++;
			x.vector[row] = data.getBytes();
			y.vector[row] = data.getBytes();
			// If the batch is full, write it out and start over.
			if (batch.size == batch.getMaxSize()) {
				writer.addRowBatch(batch);
				batch.reset();
			}
			System.out.println("Return Orc write: " + data);
			return true;
		}
		else {
			return false;
		}
	}

	public void writeTest() throws Exception {
		/*Writer writer = OrcFile.createWriter(
			testFilePath,
			OrcFile.writerOptions(conf).setSchema(schema)
									   .compress(CompressionKind.NONE)
									   .bufferSize(10000));*/

		VectorizedRowBatch batch = schema.createRowBatch();
		BytesColumnVector col1 = (BytesColumnVector) batch.cols[0];
		BytesColumnVector col2 = (BytesColumnVector) batch.cols[1];
		for (int i = 0; i < 2; i++) {
			int row = batch.size++;
			col1.vector[row] = String.valueOf("Haha").getBytes();
			col2.vector[row] = String.valueOf("Hehe").getBytes();
			if (batch.size == batch.getMaxSize()) {
				writer.addRowBatch(batch);
				batch.reset();
			}
		}
		writer.addRowBatch(batch);
		writer.close();
	}

	public void writeLongTest() throws Exception {
		Configuration conf = new Configuration();
		TypeDescription schemaI = TypeDescription.fromString("struct<x:int,y:int>");
		Writer writerI = OrcFile.createWriter(new Path("my-fileLong.orc"),
				OrcFile.writerOptions(conf)
				.setSchema(schemaI));

		VectorizedRowBatch batch = schemaI.createRowBatch();
		LongColumnVector x = (LongColumnVector) batch.cols[0];
		LongColumnVector y = (LongColumnVector) batch.cols[1];
		for (int r = 0; r < 10000; ++r) {
			int row = batch.size++;
			x.vector[row] = r;
			y.vector[row] = r * 3;
			// If the batch is full, write it out and start over.
			if (batch.size == batch.getMaxSize()) {
				writerI.addRowBatch(batch);
				batch.reset();
			}
		}
		writerI.close();
	}

	public void writeTextTest2() throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.getLocal(conf);
		Path testFilePath = new Path("my-fileText2.orc");

		TypeDescription schemaI = TypeDescription.fromString("struct<x:string,y:string>");
		Writer writerI = OrcFile.createWriter(testFilePath,
				OrcFile.writerOptions(conf)
				.setSchema(schemaI));

		VectorizedRowBatch batch = schemaI.createRowBatch();
		BytesColumnVector x = (BytesColumnVector) batch.cols[0];
		BytesColumnVector y = (BytesColumnVector) batch.cols[1];
		for (int r = 0; r < 10000; ++r) {
			int row = batch.size++;
			String valx = "Haha";
			String valy = "Hehe";
			x.vector[row] = valx.getBytes();
			y.vector[row] = valy.getBytes();
			// If the batch is full, write it out and start over.
			if (batch.size == batch.getMaxSize()) {
				writerI.addRowBatch(batch);
				batch.reset();
			}
		}
		writerI.close();
	}

	public void readTextTest2() throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.getLocal(conf);
		Path testFilePath = new Path("my-fileText2.orc");

		Reader reader = OrcFile.createReader(testFilePath, OrcFile.readerOptions(conf).filesystem(fs));
		RecordReader rows = reader.rows();
		TypeDescription schemaI = reader.getSchema();
		VectorizedRowBatch batch1 = reader.getSchema().createRowBatch();
		BytesColumnVector col1 = (BytesColumnVector) batch1.cols[0];
		BytesColumnVector col2 = (BytesColumnVector) batch1.cols[1];
		int idx = 0;
		while (rows.nextBatch(batch1)) {
			for (int r = 0; r < batch1.size; ++r) {
				System.out.println(col1.toString(r) + " " + String.valueOf(idx++));
				System.out.println(col2.toString(r) + " " + String.valueOf(idx++));
				//assertEquals(String.valueOf(idx++), col.toString(r));
			}
		}
	}

	public void writeTextTest() throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.getLocal(conf);
		Path testFilePath = new Path("my-fileText1.orc");
		//fs.delete(testFilePath, false);

		TypeDescription schema = TypeDescription.createString();

		/*Writer writer = OrcFile.createWriter(
				testFilePath,
				OrcFile.writerOptions(conf).setSchema(schema)
				//.compress(CompressionKind.NONE)
				.bufferSize(10000));
		VectorizedRowBatch batch = schema.createRowBatch();
		BytesColumnVector col = (BytesColumnVector) batch.cols[0];
		for (int i = 0; i < 20000; i++) {
			if (batch.size == batch.getMaxSize()) {
				writer.addRowBatch(batch);
				batch.reset();
			}
			col.setVal(batch.size++, String.valueOf(i).getBytes());
		}
		writer.addRowBatch(batch);
		writer.close();*/
		Reader reader = OrcFile.createReader(testFilePath, OrcFile.readerOptions(conf).filesystem(fs));
		RecordReader rows = reader.rows();
		VectorizedRowBatch batch1 = reader.getSchema().createRowBatch();
		BytesColumnVector col1 = (BytesColumnVector) batch1.cols[0];
		int idx = 0;
		while (rows.nextBatch(batch1)) {
			for (int r = 0; r < batch1.size; ++r) {
				System.out.println(col1.toString(r) + " " + String.valueOf(idx++));
				//assertEquals(String.valueOf(idx++), col.toString(r));
			}
		}
	}

	public void testCruch(String input) throws IOException {
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
		Path tempPath = new Path("my-fileText3.orc");

//		Writer writer = org.apache.hadoop.hive.ql.io.orc.OrcFile.createWriter(tempPath, org.apache.hadoop.hive.ql.io.orc.OrcFile.writerOptions(conf).inspector(inspector).stripeSize(100000).bufferSize(10000));
//		writer.addRow(orcLine);
		writer.close();
	}

	public void close() throws Exception {
		writer.close();
		configuration = null;
		schema = null;
		isInit = false;

	}
}
