package es.devcircus.spring_examples.spring_rest_examples.restful_mvc_crud_example_json.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@XmlRootElement
@JsonAutoDetect
@XmlSeeAlso({FooBar.class})
public class FooBarSet extends HashSet<FooBar> {

    public FooBarSet() {
        super();
    }

    @XmlElement(name = "FooBar")
    public Set<FooBar> getFooBarSet() {
        return this;
    }
}
