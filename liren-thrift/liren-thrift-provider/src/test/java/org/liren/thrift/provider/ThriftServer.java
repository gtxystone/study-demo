package org.liren.thrift.provider;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TCompactProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.liren.thrift.service.ContactService;
import org.liren.thrift.service.ContactService.Iface; 

public class ThriftServer {  
  
    public static void main(String[] args) throws Exception{  
        TServerSocket serverSocket = new TServerSocket(8111);  
        ContactService.Processor<Iface> processor = new ContactService.Processor<Iface>(new ContactServiceImpl());  
        Factory factory = new TCompactProtocol.Factory();  
        Args ag = new Args(serverSocket);  
        ag.outputProtocolFactory(factory);  
        ag.inputProtocolFactory(factory);  
        ag.processor(processor);  
        TServer server = new TSimpleServer(ag);  
        server.serve();  
    }  
      
}  