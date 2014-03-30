package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.CountryMapper;
import com.trailblazers.freewheelers.service.CountryService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CountryServiceTest {

    private CountryService countryService;
    private SqlSession sqlSession;
    private CountryMapper countryMapperMock;

    @Before
    public void setUp() throws Exception {
        this.sqlSession = mock(SqlSession.class);
        this.countryMapperMock = mock(CountryMapper.class);
        when(sqlSession.getMapper(CountryMapper.class)).thenReturn(countryMapperMock);
        this.countryService = new CountryService(sqlSession);
    }

    @Test
    public void shouldSetCountryMapperAsMapper() throws Exception {
        verify(sqlSession).getMapper(CountryMapper.class);
    }

    @Test
    public void shouldReturnListOfCountries() throws Exception {
        countryService.findAll();
        verify(countryMapperMock).findAll();
    }

    @Test
    public void shouldReturnCountry() throws Exception {
        countryService.get(1);
        verify(countryMapperMock).getById(1);
    }
}
