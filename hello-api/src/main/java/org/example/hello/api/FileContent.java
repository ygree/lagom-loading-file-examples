package org.example.hello.api;

import akka.util.ByteString;

public class FileContent {
    private final ByteString content;

    public FileContent(ByteString content) {
        this.content = content;
    }

    public ByteString getContent() {
        return content;
    }
}
