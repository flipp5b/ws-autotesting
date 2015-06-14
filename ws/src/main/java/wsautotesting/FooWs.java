package wsautotesting;

import org.jboss.ws.api.annotation.WebContext;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        name = "FooWs",
        serviceName = "FooWs",
        portName = "FooWs",
        targetNamespace = "my/namespace"
)
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
@WebContext(contextRoot = "/foo")
public class FooWs {
    @WebMethod
    public String hello(String name) {
        return "hello " + name;
    }
}
