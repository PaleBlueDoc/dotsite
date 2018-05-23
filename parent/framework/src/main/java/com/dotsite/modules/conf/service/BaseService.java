package com.dotsite.modules.conf.service;

import com.dotsite.modules.conf.entity.BaseEntity;
import com.dotsite.modules.conf.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @Author: QNS
 * @Description: BaseService
 * @Data: 2017/11/20 17:02
 **/
@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseRepository<T, ID>, T extends BaseEntity, ID extends Serializable> {

    @Autowired
    protected D dao;

    public List<T> findAll() {
        return dao.findAll();
    }

    public List<T> findAll(Sort sort) {
        return dao.findAll(sort);
    }

    public List<T> findAll(Example<T> example) {
        return dao.findAll(example);
    }

    public List<T> findAll(Example<T> example, Sort sort) {
        return dao.findAll(example, sort);
    }

    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return dao.findAll(example, pageable);
    }

    public Page<T> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public Optional<T> findById(ID id) {
        return dao.findById(id);
    }

    public Optional<T> findOne(Example<T> example) {
        return dao.findOne(example);
    }

    public boolean existsById(ID id) {
        return dao.existsById(id);
    }

    public long count() {
        return dao.count();
    }

    @Transactional(readOnly = false)
    public T insert(T entity) throws Exception {
        entity.preInsert();
        return dao.save(entity);
    }

    @Transactional(readOnly = false)
    public T update(T entity) throws Exception {
        entity.preUpdate();
        return dao.save(entity);
    }

    @Transactional(readOnly = false)
    public void delete(T entity) throws Exception {
        dao.delete(entity);
    }

    @Transactional(readOnly = false)
    public void deleteById(ID id) throws Exception {
        dao.deleteById(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = false)
    public T clearUpdate(T entity) {
        entityManager.clear();
        return entityManager.merge(entity);
    }

}
