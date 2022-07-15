package com.njbdqn.Tel;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TelUob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(TelUob.class);

        FileInputFormat.setInputPaths(job,new Path("d:/phone_data.txt"));
        FileOutputFormat.setOutputPath(job,new Path("d:/kkk"));
        job.setMapperClass(TelMapper.class);
        job.setReducerClass(TelReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(TelBean.class);
        job.waitForCompletion(true);
    }
}
