package com.njbdqn.normal;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class TextJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
       // Job job = new Job(new Configuration());
//        Configuration conf = new Configuration();
//        conf.set("dfs.replica","5");
        //创建Job来读取默认配置文件
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(TextJob.class);

        FileInputFormat.setInputPaths(job,new Path("d:/file.txt"));
        FileOutputFormat.setOutputPath(job,new Path("d:/res")); //文件夹

        job.setMapperClass(TextMapper.class);
        job.setReducerClass(TextReduce.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.waitForCompletion(true);
    }
}
