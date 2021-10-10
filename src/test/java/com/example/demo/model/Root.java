package com.example.demo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class Root implements Serializable {
    @XmlElement(name = "mxCell")
    public List<MxCell> mxCell;
    @XmlElement(name = "object")
    public List<Object> object;
}