package org.javaee7.wildfly.samples.services.fixed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.javaee7.wildfly.samples.services.FixedServices;
import org.javaee7.wildfly.samples.services.registration.ServiceRegistry;

/**
 * @author arungupta
 */
@FixedServices
@ApplicationScoped
public class FixedServiceRegistry implements ServiceRegistry {

    ConcurrentHashMap<String, String> serviceNameToURI = new ConcurrentHashMap<>();
    Path path;

    @Inject
    public FixedServiceRegistry() {
    }

    @PostConstruct
    public void init() {
        try {
            path = Paths.get(System.getProperty("java.io.tmpdir") + "service_registry");
            System.out.println("#####################: " + path);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            // Workaround until https://github.com/arun-gupta/microservices/issues/31 is fixed
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//        serviceNameToURI.put("catalog", "http://localhost:8080/catalog/resources/catalog");
//        serviceNameToURI.put("user", "http://localhost:8080/user/resources/user");
//        serviceNameToURI.put("order", "http://localhost:8080/order/resources/order");
    }

    @Override
    public void registerService(String name, String uri) {
        reloadMap();
        serviceNameToURI.put(name, uri);
        writeMap();
    }

    @Override
    public void unregisterService(String name, String uri) {
        if (serviceNameToURI.values().contains(name)) {
            serviceNameToURI.remove(name);
        }
        writeMap();
    }

    private void reloadMap() {
        if (!Files.exists(path)) {
            return;
        }

        try {
            Files.readAllLines(path).stream().forEach((line) -> {
                System.out.println("Adding: " + line.split("=")[0] + line.split("=")[1]);
                serviceNameToURI.put(line.split("=")[0], line.split("=")[1]);
            });
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("******************************");
        serviceNameToURI.forEach((k, v) -> System.out.println(k + "=" + v));
        System.out.println("******************************");
    }

    @Override
    public String discoverServiceURI(String name) {
        if (serviceNameToURI.keySet().contains(name)) {
            return serviceNameToURI.get(name);
        }

        throw new RuntimeException("Service " + name + " not found");
    }

    private void writeMap() {
//        Files.write(path, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        serviceNameToURI.forEach((k, v) -> {
            try {
                Files.write(path, (k + "=" + v + "\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
