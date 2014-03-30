package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.UserDetailsImpl;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jpramos on 3/28/14.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountMapper accountMapper;

    public UserDetailsServiceImpl(SqlSession sqlSession) {
        this.accountMapper = sqlSession.getMapper(AccountMapper.class);
    }

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountMapper.getByName(accountName);

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetails userDetails = new UserDetailsImpl(account);
        return userDetails;
    }
}
