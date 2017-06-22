package bz.sunlight.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bz.sunlight.constant.HttpConstant;
import bz.sunlight.dao.AccountMapper;
import bz.sunlight.dao.EnterpriseAccountMapper;
import bz.sunlight.dao.EnterpriseMapper;
import bz.sunlight.dao.UserInfoMapper;
import bz.sunlight.entity.Account;
import bz.sunlight.entity.AccountExample;
import bz.sunlight.entity.Enterprise;
import bz.sunlight.entity.EnterpriseAccount;
import bz.sunlight.entity.EnterpriseAccountExample;
import bz.sunlight.entity.UserInfo;
import bz.sunlight.entity.UserInfoBO;
import bz.sunlight.service.AccountService;
import bz.sunlight.util.Md5Util;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	@Autowired
	private EnterpriseAccountMapper enterpriseAccountMapper;

	@Override
	public Account doLogin(String userName, String password) {
		AccountExample accountExample = new AccountExample();
		String hashPw = Md5Util.parseStrToMd5L16(password + HttpConstant.PASSWORD_SALT + userName);
		accountExample.createCriteria().andAccountEqualTo(userName).andPasswordEqualTo(hashPw);
		List<Account> accounts = accountMapper.selectByExample(accountExample);
		if (accounts != null && accounts.size() != 0) {
			return accounts.get(0);
		} else {
			return null;
		}
	}

	@Override
	public UserInfoBO getAccount(String userId) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		if (userInfo != null) {
			// 如用户存在，查询相关企业信息
			Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userInfo.getEnterpriseId());
			if (enterprise != null) {
				// 查帐户余额
				EnterpriseAccountExample enterpriseAccountExample = new EnterpriseAccountExample();
				enterpriseAccountExample.createCriteria().andEnterpirseIdEqualTo(enterprise.getId());
				List<EnterpriseAccount> enterpriseAccounts = enterpriseAccountMapper.selectByExample(enterpriseAccountExample);
				if (enterpriseAccounts != null && enterpriseAccounts.size() != 0) {
					// 录入企业帐户余额
					EnterpriseAccount enterpriseAccount = enterpriseAccounts.get(0);
					UserInfoBO userInfoBO = new UserInfoBO();
					userInfoBO.setAccountBalance(enterpriseAccount.getAccountBalance());

					// 录入企业基本信息
					userInfoBO.setEnterpriseId(enterprise.getId());
					userInfoBO.setEnterpriseName(enterprise.getName());
					userInfoBO.setIndustry(enterprise.getIndustry());
					
					// 录入帐户名
					AccountExample accountExample = new AccountExample();
					accountExample.createCriteria().andUserInfoIdEqualTo(userId);
					List<Account> accounts = accountMapper.selectByExample(accountExample);
					userInfoBO.setAccount(accounts.get(0).getAccount());
					
					// 录入用户信息
					userInfoBO.setUserInfoId(userInfo.getId());
					userInfoBO.setUserInfoName(userInfo.getName());
					userInfoBO.setUserInfoGender(userInfo.getGender());
					userInfoBO.setUserInfoNumber(userInfo.getJobNumber());
					
					return userInfoBO;
				}
				
			}
			
		}
		
		return null;
	}

	@Override
	public UserInfoBO getAccountInfoByUserId(String userId) {
		return userInfoMapper.getAccountInfo(userId);
	}

	
	@Override
	public boolean changePassword(String userName, String password,
			String newPassword) {
		AccountExample accountExample = new AccountExample();
		String hashPw = Md5Util.parseStrToMd5L16(password + HttpConstant.PASSWORD_SALT + userName);
		accountExample.createCriteria().andAccountEqualTo(userName).andPasswordEqualTo(hashPw);
		List<Account> accounts = accountMapper.selectByExample(accountExample);
		if (accounts.size() != 0) {
			Account account = accounts.get(0);
			String newHashPw = Md5Util.parseStrToMd5L16(newPassword + HttpConstant.PASSWORD_SALT + userName);
			account.setPassword(newHashPw);
			int updateResult = accountMapper.updateByPrimaryKey(account);
			if (updateResult != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Enterprise> getEnterprise() {
		List<Enterprise> enterprise = enterpriseMapper.selectByExample(null);
		return enterprise;
	}

	@Override
	public UserInfo getUserInfo(String userId) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public Enterprise getEnterpriseById(String enterpriseId) {
		return enterpriseMapper.selectByPrimaryKey(enterpriseId);
	}

	@Override
	public Account getAccountByName(String userName) {
		AccountExample accountExample = new AccountExample();
		accountExample.createCriteria().andAccountEqualTo(userName);
		List<Account> accounts = accountMapper.selectByExample(accountExample);
		if (accounts != null && accounts.size() != 0) {
			return accounts.get(0);
		} else {
			return null;
		}
	}

    @Override
    public EnterpriseAccount getEnterpriseAccount(String enterpriseId) {
        EnterpriseAccountExample example = new EnterpriseAccountExample();
        example.createCriteria().andEnterpirseIdEqualTo(enterpriseId);
        List<EnterpriseAccount> accounts = enterpriseAccountMapper.selectByExample(example);
        if (accounts.size() != 0) {
            return accounts.get(0);
        } else {
            return null;
        }
    }

    @Override
    public synchronized int putEnterpriseAccount(EnterpriseAccount enterpriseAccount) {
        return enterpriseAccountMapper.updateByPrimaryKeySelective(enterpriseAccount);
    }

}
