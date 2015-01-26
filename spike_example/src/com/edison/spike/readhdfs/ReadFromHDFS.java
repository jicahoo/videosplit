package com.edison.spike.readhdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.io.IOException;

public class ReadFromHDFS {
    public static void main(final String args[]) throws IOException{
        //It worked well on 10.62.84.131  
        //hadoop com.edison.spike.readhdfs.ReadFromHDFS /tmp/example_input_data/wordcount.txt
        String uri=args[0];
        Configuration conf = new Configuration();
        String defaultFsPropName = "fs.defaultFS";
        System.out.println(conf.get(defaultFsPropName));
        FileSystem fs = FileSystem.get(conf);
        InputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally{
            IOUtils.closeStream(in);
        }
    }
}
