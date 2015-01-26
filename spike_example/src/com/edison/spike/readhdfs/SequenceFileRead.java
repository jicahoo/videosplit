package com.edison.spike.readhdfs;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Writable;

import org.apache.hadoop.util.ReflectionUtils;

public class SequenceFileRead {
    /**
     * CMD:hadoop com.edison.spike.readhdfs.SequenceFileRead /tmp/SequenceFileWrite.seq
     * OUTPUT: [130] SequenceFileWrite.class  ca fe ba be 00 0....
     *
     */

    public static void main(final String[] args) throws IOException {
        String inURI=args[0];
        Path inputPath = new Path(inURI);
        Configuration conf = new Configuration();
        //String defaultFsPropName = "fs.defaultFS";
        //System.out.println(conf.get(defaultFsPropName));
        FileSystem fs = FileSystem.get(conf);

        SequenceFile.Reader reader = null;
        try{
            reader = new SequenceFile.Reader(fs, inputPath, conf);
            Writable key = (Writable)ReflectionUtils.newInstance(reader.getKeyClass(), conf);
            Writable value = (Writable)ReflectionUtils.newInstance(reader.getValueClass(), conf);
            long position = reader.getPosition();
            while( reader.next(key,value) ){
                String syncSeen = reader.syncSeen() ? "*" : "";

                System.out.printf("[%s%s]\t%s\t%s\n", position, syncSeen, key, value);
                position = reader.getPosition();
            }
        }finally{
            IOUtils.closeStream(reader);
        }


    }
}
