package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.model.AccountValidation;
import com.trailblazers.freewheelers.service.ServiceResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountService {

    public static final String USER = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";

    private final AccountRoleMapper accountRoleMapper;
    private SqlSession sqlSession;
    private AccountMapper accountMapper;

    public AccountService() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public AccountService(SqlSession sqlSession) {
        this.sqlSession= sqlSession;
        this.accountMapper = sqlSession.getMapper(AccountMapper.class);
        this.accountRoleMapper = sqlSession.getMapper(AccountRoleMapper.class);
    }

    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    public Account getAccountIdByName(String userName) {
        return accountMapper.getByName(userName);
    }

    public Account get(Long account_id) {
        return accountMapper.getById(account_id);
    }

    public void delete(Account account) {
        accountMapper.delete(account);
        sqlSession.commit();
    }

    public void createAdmin(Account account) {
        create(account, ADMIN);
    }

    public ServiceResult<Account> createAccount(Account account) {
        AccountValidation accountValidation = new AccountValidation();
        HashMap errors = accountValidation.verifyInputs(account);

        if(errors.isEmpty()) {
            create(account, USER);
        }

        return new ServiceResult(errors, account);
    }

    private void create(Account account, String role) {
        accountMapper.insert(account);
        accountRoleMapper.insert(roleFor(account, role));
        sqlSession.commit();
    }

    private AccountRole roleFor(Account account, String role) {
        return new AccountRole()
                .setAccount_name(account.getAccount_name())
                .setRole(role);
    }
}
