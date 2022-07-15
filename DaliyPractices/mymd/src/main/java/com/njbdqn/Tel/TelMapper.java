package com.njbdqn.Tel;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TelMapper extends Mapper<LongWritable, Text,Text,TelBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
     String [] infos = value.toString().split("\t");
     int len = infos.length;
     context.write(new Text(infos[0]),
             new TelBean(Long.parseLong(infos[len-3]),Long.parseLong(infos[len-2]),Long.parseLong(infos[len-3])+Long.parseLong(infos[len-2])));
    }
}
