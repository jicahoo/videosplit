package com.edison.spike.mapperonly;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by zhangj52 on 1/19/15.
 */
    public class SpikeMapper extends Mapper<LongWritable, Text, Text, Text> {
        public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
            // Do what ever you want here
            Text lineSize = new Text(""+value.getLength());
            context.write(value,lineSize);
        }
    }
