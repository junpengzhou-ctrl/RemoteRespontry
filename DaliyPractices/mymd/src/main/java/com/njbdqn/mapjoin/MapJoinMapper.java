package com.njbdqn.mapjoin;


import com.njbdqn.ebtity.CustomersOrders;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MapJoinMapper extends Mapper<LongWritable, Text, CustomersOrders, NullWritable>{
    //思路： 将顾客表加载到内存，数据在map阶段前，完成替换
    Map<String,String> cusMap = new HashMap<>();
    CustomersOrders orders = new CustomersOrders();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        if (null!=cacheFiles&&cacheFiles.length>0){
            Path path = new Path(cacheFiles[0]);
            //加载缓存文件
//            String path = cacheFiles[0].getPath();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path.getName())));
            String line;
            while (StringUtils.isNotEmpty(line = bufferedReader.readLine())){
                String [] fileds = line.split(",");
                cusMap.put(fileds[0],fileds[1]);
            }
            bufferedReader.close();
        }
    }


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
     String []split = value.toString().split(",");
     //拼接数据再输出
     orders.setOrderId(split[0]);
     orders.setOrderStatus(split[3]);
     orders.setCustomerId(split[1]);
     orders.setCustomerName(cusMap.get(split[2]));
     context.write(orders,NullWritable.get());
    }
}
