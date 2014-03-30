package com.trailblazers.freewheelers.service.impl;


import com.trailblazers.freewheelers.mappers.ReserveOrderMapper;
import com.trailblazers.freewheelers.model.ReserveOrder;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReserveOrderServiceTest {
    @Mock
    private ReserveOrder reserveOrderMock;
    @Mock
    private ReserveOrderMapper reserveOrderMapperMock;
    @Mock
    private SqlSession sqlSessionMock;
    private ReserveOrderService reserveOrderService;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.reserveOrderService = new ReserveOrderService();
    }

    @Test
    public void shouldSaveReserveOrderIfNew() throws Exception {

        reserveOrderService.save(reserveOrderMock);

        verify(reserveOrderMapperMock).save(reserveOrderMock);
    }

    @Test
    public void shouldInsertReserveOrderIfNotNew() throws Exception {
        when(reserveOrderMock.getOrder_id()).thenReturn(null);

        reserveOrderService.save(reserveOrderMock);

        verify(reserveOrderMapperMock).insert(reserveOrderMock);
    }

    @Test
    public void shouldCommitTheSQLSessionWhenSaving() throws Exception {
        reserveOrderService.save(reserveOrderMock);

        verify(sqlSessionMock).commit();
    }

    @Test
    public void shouldFindAllOrdersByAccountId() {
        long anyAccountId = 1L;

        reserveOrderService.findAllOrdersByAccountId(anyAccountId);

        verify(sqlSessionMock).clearCache();
        verify(reserveOrderMapperMock).findAllFor(1L);
    }

    @Test
    public void shouldGetAllOrdersByAccount() {
        reserveOrderService.getAllOrdersByAccount();
        verify(sqlSessionMock).clearCache();
        verify(reserveOrderMapperMock).findAll();
    }
}
