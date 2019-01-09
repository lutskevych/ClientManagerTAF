package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReqSpecification {
    private static Properties properties = loadPropertiesFile("app.properties");

    public static final RequestSpecification reqSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(properties.getProperty("url"))
            .setPort(Integer.parseInt(properties.getProperty("port")))
            .setBasePath(properties.getProperty("endpoint"))
            .build();

    private static Properties loadPropertiesFile(String filePath) {

        Properties prop = new Properties();

        try (InputStream resourceAsStream = ReqSpecification.class.getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to load properties file : " + filePath);
        }

        return prop;

    }
}
