package com.pthngws.repository;

import com.pthngws.entity.Category;
import com.pthngws.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> getAllCategories() {
        String query = "SELECT c FROM Category c";
        return entityManager.createQuery(query, Category.class).getResultList();
    }

    @Override
    public List<Category> getCategoriesByPage(int page, int pageSize) {
        if (page < 1) {
            page = 1; // Đặt lại page về 1 nếu page nhỏ hơn 1
        }

        String query = "SELECT c FROM Category c";
        TypedQuery<Category> typedQuery = entityManager.createQuery(query, Category.class);
        typedQuery.setFirstResult((page - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }


    @Override
    public Category getCategoryById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public Category getCategoryByName(String name) {
        String query = "SELECT c FROM Category c WHERE c.name = :name";
        TypedQuery<Category> typedQuery = entityManager.createQuery(query, Category.class);
        typedQuery.setParameter("name", name);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Category> searchCategoriesByName(String keyword) {
        String query = "SELECT c FROM Category c WHERE c.name LIKE :keyword";
        TypedQuery<Category> typedQuery = entityManager.createQuery(query, Category.class);
        typedQuery.setParameter("keyword", "%" + keyword + "%");
        return typedQuery.getResultList();
    }

    @Override
    public void saveCategory(Category category) {
        entityManager.persist(category);
    }

    @Override
    public void updateCategory(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void deleteCategoryById(int id) throws Exception {
        Category category = getCategoryById(id);
        if (category != null) {
            entityManager.remove(category);
        } else {
            throw new Exception("Category not found with ID: " + id);
        }
    }

    @Override
    public int countCategories() {
        String query = "SELECT COUNT(c) FROM Category c";
        return ((Number) entityManager.createQuery(query).getSingleResult()).intValue();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Category> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Category> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Category getOne(Long aLong) {
        return null;
    }

    @Override
    public Category getById(Long aLong) {
        return null;
    }

    @Override
    public Category getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Category> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Category> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Category> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public List<Category> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Category entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Category> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }
}
