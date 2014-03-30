package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemValidation;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemService {

    private final SqlSession sqlSession;
    private final ItemMapper itemMapper;

    public ItemService() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    protected ItemService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.itemMapper = sqlSession.getMapper(ItemMapper.class);
    }

    public Item get(Long item_id) {
        return itemMapper.get(item_id);
    }

    public Item getByName(String name) {
        return itemMapper.getByName(name);
    }

    public void delete(Item item) {
        itemMapper.delete(item);
        sqlSession.commit();
    }

    public List<Item> findAll() {
        sqlSession.clearCache();
        return itemMapper.findAll();
    }

    public List<Item> getItemsWithNonZeroQuantity() {
        sqlSession.clearCache();
        return itemMapper.findAvailable();
    }

    public void saveAll(List<Item> items) {
        for (Item item : items) {
            insertOrUpdate(item);
            sqlSession.commit();
        }
    }

    public void refreshItemList(Item item) {
        List<Item> allItems = findAll();
        allItems.add(item);
    }

    public void deleteItems(List<Item> items) {
        for (Item item : items) {
            delete(item);
        }
    }

    public void decreaseQuantityByOne(Item item) {
        item.setQuantity(item.getQuantity() - 1);
        itemMapper.update(item);
        sqlSession.commit();
    }


    public ServiceResult<Item> saveItem(Item item) {
        Map<String,String> errors = new ItemValidation().validate(item);

        if (errors.isEmpty()) {
            insertOrUpdate(item);
            sqlSession.commit();
        }
        return new ServiceResult<Item>(errors, item);
    }

    private void insertOrUpdate(Item item) {
        if (item.getItemId() == null) {
            itemMapper.insert(item);
        } else {
            itemMapper.update(item);
        }
    }
}
