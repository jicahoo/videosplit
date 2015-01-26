package com.edison.spike.mapper;


import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.BytesWritable;

/**
 * KEYIN : Text : Video Segment Name
 * VALUEIN : BytesWritable : bytes of content of video segement
 * KEYOUT : Text : Video Segment Name + number of image
 * VALUEOUT : BytesWritable : bytes of content of image
 */
public class VideoSplitMapper  extends Mapper<Text, BytesWritable, Text, BytesWritable> {
    public static class Image {
        public byte[] content;
    }

    public List<Image> convertVideoToImages( final BytesWritable videoBytes) {
        List<Image> imageList = new LinkedList<Image>();
        return  imageList;
    }
    
     @Override
     public void map(Text videoSegmentName, BytesWritable videoSegmentContent, Context context) throws IOException,InterruptedException{

         List<Image> imageList = convertVideoToImages( videoSegmentContent );
         Long i = 0L;
         for(Image image : imageList) {
             i++;
             String keyOutStr = videoSegmentName.toString() + "_" + i.toString();
             context.write( new Text(keyOutStr), new BytesWritable(image.content) );
         }

     }
}

