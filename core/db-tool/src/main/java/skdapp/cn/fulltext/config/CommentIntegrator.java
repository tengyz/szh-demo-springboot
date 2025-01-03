package skdapp.cn.fulltext.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CommentIntegrator implements Integrator {
    public static final CommentIntegrator INSTANCE = new CommentIntegrator();

    public CommentIntegrator() {
        super();
    }

    /**
     * Perform comment integration.
     *
     * @param metadata        The "compiled" representation of the mapping information
     * @param sessionFactory  The session factory being created
     * @param serviceRegistry The session factory's service registry
     */
    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        processComment(metadata);
    }

    /**
     * Not used.
     *
     * @param sessionFactoryImplementor     The session factory being closed.
     * @param sessionFactoryServiceRegistry That session factory's service registry
     */
    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactoryImplementor, SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {
    }

    /**
     * Process comment annotation.
     *
     * @param metadata process annotation of this {@code Metadata}.
     */
    private void processComment(Metadata metadata) {
        for (PersistentClass persistentClass : metadata.getEntityBindings()) {
            // Process the Comment annotation is applied to Class
            Class<?> clz = persistentClass.getMappedClass();
            if (clz.isAnnotationPresent(TableComment.class)) {
                TableComment tableComment = clz.getAnnotation(TableComment.class);
                persistentClass.getTable().setComment(tableComment.value());
            }
            fieldComment(persistentClass);


//             Process Comment annotations of identifier.
            Property identifierProperty = persistentClass.getIdentifierProperty();
            if (identifierProperty != null) {
                fieldComment(persistentClass, identifierProperty.getName());
            }
            else {
                org.hibernate.mapping.Component component = persistentClass.getIdentifierMapper();
                if (component != null) {
                    //noinspection unchecked
                    Iterator<Property> iterator = component.getPropertyIterator();
                    while (iterator.hasNext()) {
                        fieldComment(persistentClass, iterator.next().getName());
                    }
                }
            }
            // Process fields with Comment annotation.
            //noinspection unchecked
            Iterator<Property> iterator = persistentClass.getPropertyIterator();
            while (iterator.hasNext()) {
                fieldComment(persistentClass, iterator.next().getName());
            }
        }
    }
    private void fieldComment(PersistentClass persistentClass, String columnName) {
        try {
            Field field =null;
            try {
                field = persistentClass.getMappedClass().getDeclaredField(columnName);
            }  catch (NoSuchFieldException | SecurityException ignored) {
                try {
                    field = persistentClass.getMappedClass().getSuperclass().getDeclaredField(columnName);
                }catch (NoSuchFieldException | SecurityException e) {
                    field = persistentClass.getMappedClass().getSuperclass().getSuperclass().getDeclaredField(columnName);
                }

            }

            if (field.isAnnotationPresent(TableComment.class)) {
                String comment = field.getAnnotation(TableComment.class).value();
                String sqlColumnName = persistentClass.getProperty(columnName).getValue().getColumnIterator().next().getText();
                comment(columnName,comment,persistentClass);
            }
        } catch (NoSuchFieldException | SecurityException ignored) {
        }
    }
    /**
     * Process @{code comment} annotation of field.
     *
     * @param persistentClass Hibernate {@code PersistentClass}
     */
    private void fieldComment(PersistentClass persistentClass ) {

        List<Method> methods = Arrays.stream(persistentClass.getMappedClass().getDeclaredMethods()).collect(Collectors.toList());
        Class<?>[] interfaceLit = persistentClass.getMappedClass().getInterfaces();
        if (Optional.ofNullable(interfaceLit).isPresent() && interfaceLit.length > 0) {
            List<Method> interfaceMethods = Arrays.stream(interfaceLit).flatMap(v -> Arrays.stream(v.getDeclaredMethods())).collect(Collectors.toList());
            methods.addAll(interfaceMethods);
        }
        methods=  methods.stream().filter(v -> {
            return v.isAnnotationPresent(TableComment.class) ;
        }).collect(Collectors.toList());
        methods.forEach(v -> {
            String columnName=StringUtils.uncapitalize(StringUtils.replace(v.getName(),"get",""));
            String comment = v.getAnnotation(TableComment.class).value();
            comment(columnName,comment,persistentClass);
        });
    }

    private void comment(String columnName, String comment, PersistentClass persistentClass) {
        String sqlColumnName = persistentClass.getProperty(columnName).getValue().getColumnIterator().next().getText();
        Iterator<org.hibernate.mapping.Column> columnIterator = persistentClass.getTable().getColumnIterator();
        while (columnIterator.hasNext()) {
            org.hibernate.mapping.Column column = columnIterator.next();
            if (sqlColumnName.equalsIgnoreCase(column.getName())) {
                column.setComment(comment);
                break;
            }
        }
    }
}
