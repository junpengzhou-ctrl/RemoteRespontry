package com.njbdqn.normal;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TextMapper extends Mapper<LongWritable, Text,Text, LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        System.out.println(key+":"+value);
        context.write(new Text("hehe"),new LongWritable(1));
    }
}
