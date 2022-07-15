package com.njbdqn.Tel;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 每行数据 你需要的数据
 */
public class TelBean implements Writable {
    //序列化
    @Override
    public void write(DataOutput dataOutput) throws IOException {
//        dataOutput.writeBytes(this.mark);
        dataOutput.writeLong(this.up);
        dataOutput.writeLong(this.down);
        dataOutput.writeLong(this.countflow);
    }
//反序列化
    @Override
    public void readFields(DataInput dataInput) throws IOException {
//     this.mark = dataInput.readLine();
     this.up = dataInput.readLong();
     this.down = dataInput.readLong();
     this.countflow = dataInput.readLong();
    }

//    private String mark;
    private long up;
    private long down;
    private long countflow;

    public TelBean( long up, long down, long countflow) {
//        this.mark = mark;
        this.up = up;
        this.down = down;
        this.countflow = countflow;
    }

    public TelBean() {

    }

//    public String getMark() {
//        return mark;
//    }
//
//    public void setMark(String mark) {
//        this.mark = mark;
//    }

    public long getUp() {
        return up;
    }

    public void setUp(long up) {
        this.up = up;
    }

    public long getDown() {
        return down;
    }

    public void setDown(long down) {
        this.down = down;
    }

    public long getCountflow() {
        return countflow;
    }

    public void setCountflow(long countflow) {
        this.countflow = countflow;
    }

    @Override
    public String toString() {
        return "\t"+up+"\t"+down+"\t"+countflow;
    }
}
