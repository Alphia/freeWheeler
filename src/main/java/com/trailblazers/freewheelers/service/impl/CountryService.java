package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.CountryMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryMapper countryMapper;

    public CountryService() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public CountryService(SqlSession sqlSession) {
        this.countryMapper = sqlSession.getMapper(CountryMapper.class);
    }

    public List<Country> findAll() {
        return countryMapper.findAll();
    }

    public Country get(long country_id) {
        return countryMapper.getById(country_id);
    }
}
