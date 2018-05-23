package com.dotsite.modules.sys.service;

import com.dotsite.modules.conf.service.BaseService;
import com.dotsite.modules.sys.dao.SysUserRepository;
import com.dotsite.modules.sys.entity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysUserService extends BaseService<SysUserRepository, SysUser, String> {


}
