package com.njbdqn.ebtity;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class CusDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(CusDriver.class);

        job.setMapperClass(CusMapoper.class);
        job.setReducerClass(CusReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CustomersOrders.class);

        job.setOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(CustomersOrders.class);

        FileOutputFormat.setOutputPath(job,new Path("d:/ss"));
        FileInputFormat.setInputPaths(job,new Path("d:/input/orders.csv"),new Path("d:/input/customers.csv"));
//        FileInputFormat.setInputPaths(job,new Path(""));
       job.waitForCompletion(true);
    }
}
