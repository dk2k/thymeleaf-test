package ru.outofrange;

import java.io.IOException;

import org.hibernate.MappingException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateSchemaGeneration {
	
    public static void main(String[] args) throws MappingException, IOException {
        ServiceRegistry serviceRegistry = buildCfg();
        MetadataImplementor metadata = (MetadataImplementor) new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport(metadata);
        schemaExport.setOutputFile("hbm2schema.sql");
        schemaExport.create(true, true);
        ( (StandardServiceRegistryImpl) serviceRegistry ).destroy();
    }
     
    public static StandardServiceRegistryImpl buildCfg() {
        return (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
    }
}
