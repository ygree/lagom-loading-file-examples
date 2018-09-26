package org.example.hello.impl;

import akka.util.ByteString;
import com.lightbend.lagom.javadsl.testkit.ServiceTest;
import org.example.hello.api.FileContent;
import org.example.hello.api.HelloService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class HelloServiceTest {


    private static ServiceTest.TestServer server;
    static HelloService service;

    @BeforeClass
    public static void setUp() throws Exception {
        final ServiceTest.Setup setup = defaultSetup();
        server = ServiceTest.startServer(setup.withPersistence(false));
        service = server.client(HelloService.class);
    }

    @AfterClass
    public static void tearDown() {
        if (server != null) {
            server.stop();
            server = null;
        }
    }

    @Test
    public void shouldUploadDownload() throws Exception {
        String input = "U#HR@)FNIOLKsjgklJGJ";

        FileContent inputContent = new FileContent(ByteString.fromString(input));

        service.upload().invoke(inputContent).toCompletableFuture().get(5, SECONDS);

        FileContent outputContent = service.download().invoke().toCompletableFuture().get(5, SECONDS);

        assertEquals(input, new String(outputContent.getContent().toArray()));
    }

}
