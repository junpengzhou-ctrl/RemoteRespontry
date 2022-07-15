package com.njbdqn.normal;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TextReduce extends Reducer <Text, LongWritable,Text,LongWritable>{
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("reduce"+key+":"+values);
        context.write(key,new LongWritable(500));
    }
}
