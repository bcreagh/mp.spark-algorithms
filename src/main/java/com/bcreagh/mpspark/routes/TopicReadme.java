package com.bcreagh.mpspark.routes;

import com.bcreagh.mpspark.mp.domain.Readme;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.routes.routeutils.SimpleRoute;
import com.bcreagh.mpspark.services.FileService;

import java.io.IOException;

import static spark.Spark.get;

@MpRoute
public class TopicReadme extends BaseRoute {

    private static final Readme readme = new Readme();

    @SimpleRoute
    public static void readme() throws IOException {
        loadReadme();
        get(String.format("/%s/readme", SERVICE_NAME), (request, response) -> {
            return jsonResponse(readme, response);
        });
    }

    private static void loadReadme() throws IOException {
        String readmeAsString = FileService.readFileFromResources("README.md", "UTF-8");
        readme.setData(readmeAsString);
    }
}
