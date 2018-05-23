package com.dotsite.modules.conf.repository;

import com.dotsite.modules.conf.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author: QNS
 * @Description: BaseService
 * @Data: 2017/11/20 17:02
 **/
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {



}
