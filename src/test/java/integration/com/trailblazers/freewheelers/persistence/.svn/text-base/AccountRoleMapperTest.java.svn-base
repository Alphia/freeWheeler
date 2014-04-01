package integration.com.trailblazers.freewheelers.persistence;

import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.AccountRole;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountRoleMapperTest extends MapperTestBase {

    private AccountRoleMapper accountRoleMapper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        accountRoleMapper = getSqlSession().getMapper(AccountRoleMapper.class);
    }

    @Test
    public void shouldInsertAnAccountRole() throws Exception {
        AccountRole accountRole = new AccountRole("Some Name","Some Role");

        accountRoleMapper.insert(accountRole);

        assertThat(accountRole.getRole_id(), is(not(nullValue())));
    }

    @Test
    public void shouldSelectAccountRoleByName() throws Exception {
        AccountRole accountRole = new AccountRole("Greg","ROLE_USER");

        accountRoleMapper.insert(accountRole);
        AccountRole fetchedFromDB = accountRoleMapper.getByAccountName("Greg");

        assertThat(fetchedFromDB.getRole(), is("ROLE_USER"));
    }

    @Test
    public void shouldComplainIfAccountNameDoesNotExist() throws Exception {
        AccountRole accountRole = new AccountRole("Greg","ROLE_USER");
        accountRoleMapper.insert(accountRole);
        AccountRole fetchedFromDB = accountRoleMapper.getByAccountName("NotAName");

        assertNull(fetchedFromDB);
    }

    @Test(expected = TooManyResultsException.class)
    public void shouldComplainIfDuplicateAccountNamesExist() throws Exception {
        AccountRole accountRole = new AccountRole("Greg","ROLE_USER");
        AccountRole accountRoleDuplicate = new AccountRole("Greg","ROLE_USER");
        accountRoleMapper.insert(accountRole);
        accountRoleMapper.insert(accountRoleDuplicate);

        accountRoleMapper.getByAccountName("Greg");
    }
}
