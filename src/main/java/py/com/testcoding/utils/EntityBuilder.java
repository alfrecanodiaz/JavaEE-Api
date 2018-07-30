package py.com.testcoding.utils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.net.URI;

import static javax.json.Json.createObjectBuilder;

/**
 * Responsible build Json Object from Entity
 *
 * @author Alfredo Cano
 * @since 1.0.0
 */
public class EntityBuilder {

    public JsonObject buildIndex(URI self, URI message) {
        final JsonObjectBuilder builder = createObjectBuilder();

        builder.add("_links", Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("rel", "self")
                        .add("href", self.toString())
                        .add("method", "GET")
                )
                .add(Json.createObjectBuilder()
                        .add("rel", "message")
                        .add("href", message.toString())
                        .add("method", "GET")
                )
        );

        return builder.build();
    }
}