package com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import com.trailblazers.freewheelers.service.validation.AccountValidation;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountService {

    public static final String USER = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";

    private final AccountRoleMapper accountRoleMapper;
    private final SqlSession sqlSession;
    private final AccountMapper accountMapper;
    private final AccountValidation accountValidation;

    @Autowired
    public AccountService(AccountValidation accountValidation) {
        this(MyBatisUtil.getSqlSessionFactory().openSession(), accountValidation);
    }

    public AccountService(SqlSession sqlSession, AccountValidation accountValidation) {
        this.sqlSession= sqlSession;
        this.accountMapper = sqlSession.getMapper(AccountMapper.class);
        this.accountRoleMapper = sqlSession.getMapper(AccountRoleMapper.class);
        this.accountValidation = accountValidation;
    }

    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    public Account getAccountByName(String userName) {
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

    public AccountRole getAccountRoleFor(Account account) {
        return accountRoleMapper.getByAccountName(account.getAccount_name());
    }
}
