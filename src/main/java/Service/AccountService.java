package Service;
import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;
    
    public AccountService() {
        accountDAO = new AccountDAO();
    }
    
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account) {
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return null;
        }
        
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }
         
        if (accountDAO.usernameExists(account.getUsername())) {
            return null;
        }        
        return accountDAO.insertAccount(account);
    }

    public Account login(Account account) {
        if (account.getUsername() == null || account.getPassword() == null) {
            return null;
        }
        return accountDAO.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    public boolean accountExists(int accountId) {
        return accountDAO.accountExists(accountId);
    }
}
