package com.example.demo.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


public class MxCell {
	@XmlAttribute(name = "id")
    public String id;
	@XmlAttribute(name = "parent")
    public String parent;
	@XmlElement(name = "mxGeometry")
    public MxGeometry mxGeometry;
	@XmlAttribute(name = "style")
    public String style;
	@XmlAttribute(name = "edge")
    public String edge;
	@XmlAttribute(name = "source")
    public String source;
	@XmlAttribute(name = "target")
    public String target;
	@XmlAttribute(name = "vertex")
    public String vertex;
}