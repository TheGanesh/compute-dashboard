package com.ganesh.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AmazonEC2Service {

    public List<EC2Instance> fetchAmazonEC2InstancesDetails() {

        EC2Instance instance1 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance2 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance3 = new EC2Instance("x", "w", "c", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance4 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");

        EC2Instance instance5 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance6 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance7 = new EC2Instance("x", "w", "c", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance8 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");


        EC2Instance instance9 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance10 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance11 = new EC2Instance("x", "w", "c", "running", "auri", "124.2123.21", "28346.234.d");
        EC2Instance instance12 = new EC2Instance("ganesh", "id1", "load", "running", "auri", "124.2123.21", "28346.234.d");


        List<EC2Instance> returnList = new ArrayList<EC2Instance>();

        returnList.add(instance1);
        returnList.add(instance2);
        returnList.add(instance3);
        returnList.add(instance4);
        returnList.add(instance5);
        returnList.add(instance6);
        returnList.add(instance7);
        returnList.add(instance8);
        returnList.add(instance9);
        returnList.add(instance10);
        returnList.add(instance11);
        returnList.add(instance12);

        return returnList;
    }
}
