package com.njbdqn.ebtity;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
//Map端的主要工作：为来自(文件)的key/value对打标签以区别不同来源的记录。
// 然后用连接字段作为key，其余部分和新加的标志作为value，最后进行输出。
public class CusMapoper extends Mapper<LongWritable, Text,Text,CustomersOrders> {
   CustomersOrders co = new CustomersOrders();
    String name;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //根据分片信息获取文件名称
        FileSplit fs = (FileSplit) context.getInputSplit();
        name = fs.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

     String [] split = value.toString().split(",");
     if (name.startsWith("orders")){
         co.setCustomerId(split[2]);
         co.setOrderId(split[0]);
         co.setOrderStatus(split[3]);
         co.setFlag(1);
         co.setCustomerName("");
     }else{
         co.setCustomerId(split[0]);
         co.setCustomerName(split[1]);
         co.setOrderStatus("");
         co.setFlag(0);
         co.setOrderId("");
     }
     context.write(new Text(co.getCustomerId()),co);

    }
}
