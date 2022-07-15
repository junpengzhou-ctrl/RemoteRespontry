package com.njbdqn.ebtity;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

import java.util.ArrayList;
//Reduce端的主要工作： 我们只需要在每一个分组当中将那些来源于不同文件的记录(在map阶段已经打标志)分开，
// 最后进行合并就ok了。
public class CusReducer extends Reducer<Text,CustomersOrders,CustomersOrders, NullWritable> {
    CustomersOrders cusBean = new CustomersOrders();
    @Override
    protected void reduce(Text key, Iterable<CustomersOrders> values, Context context) throws IOException, InterruptedException {
        //准备存储的集合
        ArrayList<CustomersOrders> orderBeans = new ArrayList<>();
        for (CustomersOrders bean : values) {
            if (1==( bean.getFlag())) {// 订单表
                // 拷贝传递过来的每条  订单数据到集合中
                CustomersOrders orderBean = new CustomersOrders();
                try {
                    //拷贝传递过来的产品表到内存中
                    BeanUtils.copyProperties(orderBean, bean);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                orderBeans.add(orderBean);
            } else {// 客户信息表
                try {
                    // 拷贝传递过来的产品表到内存中
                    BeanUtils.copyProperties(cusBean, bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (CustomersOrders bean : orderBeans){
            bean.setCustomerName(cusBean.getCustomerName());
            context.write(bean,NullWritable.get());
        }
    }
}
