package com.njbdqn.ebtity;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CustomersOrders implements Writable {
    private String customerName;
    private String customerId;
    private String orderStatus;
    private String orderId;
    private int flag;

    @Override
    public String toString() {
        return orderId+"\t"+customerName+"\t"+orderStatus;
    }

    public CustomersOrders(String customerName, String customerId, String orderStatus, String orderId, int flag) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.orderId = orderId;
        this.flag = flag;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(customerName);
        dataOutput.writeUTF(customerId);
        dataOutput.writeUTF(orderStatus);
        dataOutput.writeInt(flag);
        dataOutput.writeUTF(orderId);
    }

    public CustomersOrders( ) {
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.customerName = dataInput.readUTF();
        this.customerId = dataInput.readUTF();
        this.orderStatus = dataInput.readUTF();
        this.flag = dataInput.readInt();
        this.orderId = dataInput.readUTF();
    }
}
