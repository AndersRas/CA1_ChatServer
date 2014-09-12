/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anders
 */
public class ChatClientTest
{ 
    
    public ChatClientTest()
    {
    }
    
    @Before
    public void setUp() throws IOException
    {
        ChatClient cc = new ChatClient(); 
        cc.registerEchoListener(new ChatListener() {

            @Override
            public void messageArrived(String data)
            {
                System.out.println("message arrived " + data); 
            }
        });
        cc.connect("localhost", 9090); 
    }
    
    @After
    public void tearDown()
    {
        
    }

    @Test
    public void testConnect() throws Exception
    {
        System.out.println("connect");
        String address = "";
        int port = 0;
        ChatClient instance = new ChatClient();
        instance.connect(address, port);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRegisterEchoListener()
    {
        System.out.println("registerEchoListener");
        ChatListener l = null;
        ChatClient instance = new ChatClient();
        instance.registerEchoListener(l);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUnRegisterEchoListener()
    {
        System.out.println("unRegisterEchoListener");
        ChatListener l = null;
        ChatClient instance = new ChatClient();
        instance.unRegisterEchoListener(l);
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testSend()
    {
        System.out.println("send");
        String msg = "";
        ChatClient instance = new ChatClient();
        instance.send(msg);
        fail("The test case is a prototype.");
    }

    @Test
    public void testClose() throws Exception
    {
        System.out.println("close");
        ChatClient instance = new ChatClient();
        instance.close();
        fail("The test case is a prototype.");
    }
    @Test
    public void testReceive()
    {
        System.out.println("receive"); 
        ChatClient instance = new ChatClient();
        String expResult = ""; 
        String result = instance.receive();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        ChatClient.main(args);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRun()
    {
        System.out.println("run");
        ChatClient instance = new ChatClient();
        instance.run();
        fail("The test case is a prototype.");
    }
    
}
