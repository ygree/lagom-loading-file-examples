package org.example.hello.api;


import akka.util.ByteString;
import com.lightbend.lagom.javadsl.api.deser.StrictMessageSerializer;
import com.lightbend.lagom.javadsl.api.transport.MessageProtocol;
import com.lightbend.lagom.javadsl.api.transport.NotAcceptable;
import com.lightbend.lagom.javadsl.api.transport.UnsupportedMediaType;

import java.util.List;

public class FileContentSerializer implements StrictMessageSerializer<FileContent> {

    @Override
    public NegotiatedSerializer<FileContent, ByteString> serializerForRequest() {
        return FileContent::getContent;
    }

    @Override
    public NegotiatedDeserializer<FileContent, ByteString> deserializer(MessageProtocol protocol) throws UnsupportedMediaType {
        return FileContent::new;
    }

    @Override
    public NegotiatedSerializer<FileContent, ByteString> serializerForResponse(List<MessageProtocol> acceptedMessageProtocols) throws NotAcceptable {
        return FileContent::getContent;
    }
}
