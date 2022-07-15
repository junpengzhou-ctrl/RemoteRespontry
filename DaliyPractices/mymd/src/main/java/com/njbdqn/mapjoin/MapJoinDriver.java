package com.njbdqn.mapjoin;

import com.njbdqn.ebtity.CustomersOrders;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MapJoinDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(MapJoinDriver.class);
        job.setMapperClass(MapJoinMapper.class);
        //map端join的逻辑不需要reduce阶段，设置reducetask数量为0
        job.setNumReduceTasks(0);
        //map阶段的输出的key和value的类型
        job.setMapOutputKeyClass(CustomersOrders.class);
        job.setMapOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //加载缓存数据
//        job.addCacheFile(new Path("file://d:/inpput/customers.csv").toUri());
        job.addCacheFile(new URI("/input/customers.csv"));
        job.waitForCompletion(true);
    }

}
