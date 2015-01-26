package com.edison.spike.writehdfs;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;

import java.io.InputStream;
import java.io.IOException;

/*
 *
 * Reference:
 * http://mail-archives.apache.org/mod_mbox/hadoop-hdfs-user/201205.mbox/%3CCAOcnVr3p-+hx7BtspsJRkCzvSULdWcnXhMqc=Gf_ryA0gPO20g@mail.gmail.com%3E
 * To learn how to implement custom readers/formats you can refer to an
 * > example provided via sub-title "Processing a whole file as a record",
 * > Page 206 | Chapter 7: MapReduce Types and Formats in Tom White's
 * > Hadoop: The Definitive Guide, or you can read up the details on
 * > http://developer.yahoo.com/hadoop/tutorial/module5.html#inputformat.
 * */
public class SequenceFileWrite{
    public static String[] DATA = {
        "one two three four",
        "five six eight.",
        "you know fk",
        "fs what"
    };
    public static void main(final String args[]) throws IOException{
        //It worked well on 10.62.84.131:
        //hadoop  com.edison.spike.writehdfs.SequenceFileWrite /tmp/example_input_data/seqfile_example.seq
        //TODO: Key:Value = Text(name of video file):Bytes(video content)
        //Note: follow the example: http://stackoverflow.com/questions/16546040/store-images-videos-into-hadoop-hdfs
        //1. Read the binary file: hadoop fs put to hdfs
        //2. Put the binary file to 
        Path inputPathSrcHdfs = new Path(args[0]);
        Path outputPathDstHdfs = new Path(args[1]);

        Configuration conf = new Configuration();
        //String defaultFsPropName = "fs.defaultFS";
        //System.out.println(conf.get(defaultFsPropName));
        FileSystem fs = FileSystem.get(conf);

        SequenceFile.Writer  writer = null;

        FSDataInputStream in = null;
        try {
            in = fs.open(inputPathSrcHdfs);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            writer = SequenceFile.createWriter(fs, conf, outputPathDstHdfs, Text.class, BytesWritable.class);
            writer.append(new Text(inputPathSrcHdfs.getName() ), new BytesWritable(buffer));
        }finally{
            IOUtils.closeStream(writer);
        }
    }
}
