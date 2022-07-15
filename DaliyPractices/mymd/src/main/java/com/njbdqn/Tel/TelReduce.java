package com.njbdqn.Tel;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class TelReduce extends Reducer<Text,TelBean,Text,TelBean> {
    @Override
    protected void reduce(Text key, Iterable<TelBean> values, Context context) throws IOException, InterruptedException {
        int countUp =0;
        int countDown = 0;
        for(TelBean tb : values){
            countUp+=tb.getUp();
            countDown+=tb.getDown();
        }
        context.write(key,new TelBean(countUp,countDown,countUp+countDown));
    }
}
