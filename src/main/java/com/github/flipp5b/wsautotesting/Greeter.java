package com.github.flipp5b.wsautotesting;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Greeter", serviceName = "Greeter", portName = "Greeter", targetNamespace = "my/namespace")
public class Greeter {
    @WebMethod
    public String hello(String name) {
        return "Hello, " + name;
    }
}
