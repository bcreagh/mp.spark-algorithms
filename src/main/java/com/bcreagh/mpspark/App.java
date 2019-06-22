package com.bcreagh.mpspark;

import com.bcreagh.mpspark.exceptions.InitializationException;
import com.bcreagh.mpspark.mp.utilities.logger.Logger;
import com.bcreagh.mpspark.routes.routeutils.TypedRoute;
import com.bcreagh.mpspark.routes.routeutils.MpRoute;
import com.bcreagh.mpspark.routes.routeutils.SimpleRoute;
import com.bcreagh.mpspark.services.ActionService;
import com.bcreagh.mpspark.services.ConfigService;
import org.reflections.Reflections;
import spark.Filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
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

    @SuppressWarnings("unchecked")
    private static void initializeRouteClasses() {
        Reflections reflections = new Reflections("com.bcreagh.mpspark.routes");
        Set<Class<?>> routeClasses = (Set<Class<?>>) reflections.getTypesAnnotatedWith(MpRoute.class);
        for (Class<?> route : routeClasses) {
            initializeRoutes(route);
        }
    }

    private static void initializeRoutes(Class<?> klass) {
        try {
            List<Method> simpleRouteMethods = getMethodsAnnotatedMethods(SimpleRoute.class, klass);
            for (Method method : simpleRouteMethods) {
                Logger.log("Initializing simple route: " + method.getName());
                method.invoke(klass);
            }
            List<Method> algoRouteMethods = getMethodsAnnotatedMethods(TypedRoute.class, klass);
            for (Method method : algoRouteMethods) {
                Logger.log("Initializing algo route: " + method.getName());
                method.invoke(klass, klass);
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            throw new InitializationException("There was a problem initializing the routes", ex);
        }

    }

    public static List<Method> getMethodsAnnotatedMethods(final Class<? extends Annotation> annotation, Class<?> type) {
        final List<Method> methods = new ArrayList<>();
        Class<?> klass = type;
        while (klass != Object.class) {
            final List<Method> allMethods = new ArrayList<>(Arrays.asList(klass.getDeclaredMethods()));
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }
            klass = klass.getSuperclass();
        }
        return methods;
    }
}
