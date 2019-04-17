package com.bcreagh.mpspark;

import com.bcreagh.mpspark.exceptions.InitializationException;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.BaseRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.ConfigService;
import org.reflections.Reflections;
import spark.Filter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static spark.Spark.*;


public class App 
{
    public static void main( String[] args ) throws IOException {
        initializeServices();
        port(ConfigService.getPort());
        enableCors();
        handleHttpOptions();
        initializeRouteClasses();
    }

    private static void initializeServices() throws IOException {
        ActionService.init();
        ConfigService.init();
    }

    private static void enableCors() {
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });
    }

    private static void handleHttpOptions() {
        options("/*", (request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Headers", "content-type");
            return "[{\"Allow\": \"POST\"}, 200, {\"Access-Control-Allow-Origin\": \"*\", \"Access-Control-Allow-Methods\": \"PUT,GET\"}]";
        });
    }

    private static void initializeRouteClasses() {
        Reflections reflections = new Reflections("com.bcreagh.mpspark.routes");
        Set<Class<? extends BaseRoute>> routeClasses = reflections.getSubTypesOf(BaseRoute.class);
        for (Class<? extends BaseRoute> route : routeClasses) {
            initializeRoutes(route);
        }
    }

    private static void initializeRoutes(Class<? extends BaseRoute> klass) {
        try {
            List<Method> methods = getMethodsAnnotatedWithRoute(klass);
            for (Method method : methods) {
                Logger.log("Initializing route: " + method.getName());
                method.invoke(klass);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new InitializationException("There was a problem initializing the routes", ex);
        }

    }

    public static List<Method> getMethodsAnnotatedWithRoute(final Class<?> type) {
        final List<Method> methods = new ArrayList<>();
        Class<?> klass = type;
        while (klass != Object.class) {
            final List<Method> allMethods = new ArrayList<>(Arrays.asList(klass.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(MpRoute.class)) {
                    methods.add(method);
                }
            }
            klass = klass.getSuperclass();
        }
        return methods;
    }
}
