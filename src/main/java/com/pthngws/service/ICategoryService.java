package com.pthngws.service;

import com.pthngws.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    long count();

    void deleteById(Long aLong);


    Page<Category> findAll(Pageable pageable);

    List<Category> findAll(Sort sort);


    List<Category> findAll();

    Optional<Category> findByName(String name);

    Page<Category> findByNameContaining(String name, Pageable pageable);
    List<Category> findByNameContaining(String name);
    <S extends Category> S save(S entity);

    Optional<Category> findById(Long id);
}
