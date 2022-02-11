package de.quoss.example.quarkus.main;

import io.agroal.api.AgroalDataSource;
import io.quarkus.arc.All;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.List;

public class App implements QuarkusApplication {

    @Inject
    @All
    List<InstanceHandle<AgroalDataSource>> dataSourceHandles;

    public int run(String[] args) {
        System.out.println("Do startup logic here");
        System.out.println("Data source handles list size: " + dataSourceHandles.size());
        if (dataSourceHandles.isEmpty()) {
            // do nothing
        } else {
            for (InstanceHandle<AgroalDataSource> handle : dataSourceHandles) {
                String name = handle.getBean().getName();
                System.out.format("Name: %s%n", name);
            }
        }
        Quarkus.waitForExit();
        return 0;
    }

}
