package org.example.hello.impl;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.example.hello.api.FileContent;
import org.example.hello.api.HelloService;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the HelloService.
 */
public class HelloServiceImpl implements HelloService {

    private FileContent content;

    @Override
    public ServiceCall<FileContent, Done> upload() {
        return fileContent -> {
            System.out.println("Uploading Data");
            this.content = fileContent;
            return CompletableFuture.completedFuture(Done.getInstance());
        };
    }

    @Override
    public ServiceCall<NotUsed, FileContent> download() {
        return notUsed -> {
            System.out.println("Downloading Data");
            return CompletableFuture.completedFuture(this.content);
        };
    }
}
