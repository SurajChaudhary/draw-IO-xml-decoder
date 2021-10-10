package com.example.demo.model;

import javax.xml.bind.annotation.XmlAttribute;


public class MxGeometry {
    @XmlAttribute(name = "relative")
    public String relative;
    @XmlAttribute(name = "as")
    public String as;
    @XmlAttribute(name = "x")
    public String x;
    @XmlAttribute(name = "y")
    public String y;
    @XmlAttribute(name = "width")
    public String width;
    @XmlAttribute(name = "height")
    public String height;
}
