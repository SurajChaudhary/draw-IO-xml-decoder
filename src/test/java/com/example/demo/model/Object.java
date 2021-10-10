package com.example.demo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/*@Data
@NoArgsConstructor
@AllArgsConstructor*/
@XmlAccessorType(XmlAccessType.FIELD)
public class Object implements Serializable {
    @XmlAttribute(name = "label")
    public String label;
    @XmlAttribute(name = "serviceName")
    public String serviceName;
    @XmlAttribute(name = "id")
    public String id;
    @XmlAttribute(name = "isLoadBalanced")
    public boolean isLoadBalanced;
}