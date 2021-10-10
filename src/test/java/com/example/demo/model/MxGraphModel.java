package com.example.demo.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


@XmlRootElement(name = "mxGraphModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class MxGraphModel implements Serializable {
	@XmlElement(name = "root")
    public Root root;
	@XmlAttribute(name = "dx")
	public String dx;
	@XmlAttribute(name = "dy")
	public String dy;
	@XmlAttribute(name = "grid")
	public String grid;
	@XmlAttribute(name = "gridSize")
	public String gridSize;
	@XmlAttribute(name = "guides")
	public String guides;
	@XmlAttribute(name = "tooltips")
	public String tooltips;
	@XmlAttribute(name = "connect")
	public String connect;
	@XmlAttribute(name = "arrows")
	public String arrows;
	@XmlAttribute(name = "fold")
	public String fold;
	@XmlAttribute(name = "page")
	public String page;
	@XmlAttribute(name = "pageScale")
	public String pageScale;
	@XmlAttribute(name = "pageWidth")
	public String pageWidth;
	@XmlAttribute(name = "pageHeight")
	public String pageHeight;
	@XmlAttribute(name = "math")
	public String math;
	@XmlAttribute(name = "shadow")
	public String shadow;
}