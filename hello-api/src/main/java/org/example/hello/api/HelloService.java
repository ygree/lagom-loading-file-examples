package org.example.hello.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import static com.lightbend.lagom.javadsl.api.Service.topic;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;
import com.lightbend.lagom.javadsl.api.deser.PathParamSerializer;
import com.lightbend.lagom.javadsl.api.deser.PathParamSerializers;

/**
 * The Hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the Hello.
 */
public interface HelloService extends Service {

    /**
     * Example: curl -F 'data=@file.txt' localhost:9000/api/file/upload
     */
    ServiceCall<FileContent, Done> upload();

    /**
     * Example: curl localhost:9000/api/file/download
     */
    ServiceCall<NotUsed, FileContent> download();

    @Override
    default Descriptor descriptor() {
        // @formatter:off
        return named("hello").withCalls(
                pathCall("/api/file/upload", this::upload),
                pathCall("/api/file/download", this::download)
        ).withAutoAcl(true)
                .withMessageSerializer(FileContent.class, new FileContentSerializer());
        // @formatter:on
    }
}
