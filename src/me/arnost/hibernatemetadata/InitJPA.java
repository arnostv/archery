package me.arnost.hibernatemetadata;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class InitJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");

        final EntityManager entityManager = emf.createEntityManager();

        final Metamodel metamodel = entityManager.getMetamodel();
        for (EntityType<?> entityType : metamodel.getEntities()) {
            describeEntityType(entityType);
        }



        entityManager.close();
        emf.close();

    }

    private static void describeEntityType(EntityType<?> entityType) {
        final Class<?> javaType = entityType.getJavaType();
        System.out.println(javaType);
        for (Attribute<?, ?> attribute : entityType.getDeclaredAttributes()) {
            System.out.println("   " + attribute.getName() + " " + attribute.getJavaType() + " " + attribute.getJavaMember());
        }
        ;
    }
}
