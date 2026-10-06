//package com.ui_demo.service;
//
//import io.micrometer.common.KeyValues;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Stream;
//
//public class SearchService<ENTITY,DTO> {
//    private final EntityManager entityManager;
//    private final Class<ENTITY> entityClass;
//    private final Function<ENTITY, DTO> toDTO;
//    private final List<String> excludedFields = new ArrayList<>();
//
//    public SearchService(EntityManager entityManager, Class<ENTITY> entityClass, Class<DTO> dtoClass, EntityManager entityManager1, Class<ENTITY> entityClass1, Class<DTO> dtoClass1, Function<ENTITY, DTO> dtoClass2) {
//        this.entityManager = entityManager1;
//        this.entityClass = entityClass1;
//        this.toDTO = dtoClass2;
//    }
//
//    public List<DTO> search(String search) {
//        List<Field> fieldList = Arrays.stream(entityClass.getDeclaredFields())
//                .filter(this::checkExcluded)
//                .toList();
//        return SearchAllFields(fieldList, search).stream().map(toDTO).toList();
//    }
//
//    private List<ENTITY> SearchAllFields(List<Field> fieldList, String search)
//        {
//            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//            CriteriaQuery<ENTITY> query = criteriaBuilder.createQuery(entityClass);
//            Root<ENTITY> root = query.from(entityClass);
//           String searchtext= "%" +search.toLowerCase()+ "%";
//           query.select(root);
//            query.where(
//                    criteriaBuilder.or(
//                            fieldList.stream()
//                                    .flatMap(f -> getCriteriaBuilderQuery(f, root, criteriaBuilder, searchtext))
//                                    .toList()
//                    )
//            );
//
//            return List.of();
//        }
//
//    private Stream<Predicate> getCriteriaBuilderQuery(Field f, Root<ENTITY> root, CriteriaBuilder criteriaBuilder, String searchtext)   {
//        Type genericType = f.getGenericType();
//        if(f.getType().getClassLoader()!=null){
//            Class entityClass = f.getType();
//            return Arrays.stream(entityClass.getDeclaredFields())
//                    .flatMap(field->getCriteriaBuilderQuery(field, root.get(f.getName()), criteriaBuilder, searchtext));
//        }
//    }
//
//
//    private boolean checkExcluded (Field f){
//            return excludedFields.stream().noneMatch(s -> s.equals(f.getName()));
//        }
//
//}
