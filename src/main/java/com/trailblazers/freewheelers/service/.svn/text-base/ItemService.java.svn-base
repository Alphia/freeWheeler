package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.ItemMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.service.validation.ItemValidation;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemService {

    private final SqlSession sqlSession;
    private final ItemMapper itemMapper;
    private final ItemValidation itemValidation;

    @Autowired
    public ItemService(ItemValidation itemValidation) {
        this(MyBatisUtil.getSqlSessionFactory().openSession(), itemValidation);
    }

    protected ItemService(SqlSession sqlSession, ItemValidation itemValidation) {
        this.sqlSession = sqlSession;
        this.itemMapper = sqlSession.getMapper(ItemMapper.class);
        this.itemValidation = itemValidation;
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


    public ServiceResult<Item> saveItem(Item item) {
        Map<String,String> errors = itemValidation.validate(item);

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

    public void decreaseQuantity(Item item, int quantity) {
        item.setQuantity(item.getQuantity() - quantity);
        itemMapper.update(item);
        sqlSession.commit();
    }
}
