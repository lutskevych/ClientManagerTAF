package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


public class ReqSpecification {
    private static ResourceBundle propertiesFile;

    static {
        try {
            propertiesFile = new PropertyResourceBundle(ReqSpecification.class.getClassLoader()
                    .getResourceAsStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final RequestSpecification reqSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(propertiesFile.getString("url"))
            .setPort(Integer.parseInt(propertiesFile.getString("port")))
            .setBasePath(propertiesFile.getString("endpoint"))
            .build();

}
