package fudan.se.lab2.service.impl;

import fudan.se.lab2.constant.FileConstant;
import fudan.se.lab2.constant.InfoConstant;
import fudan.se.lab2.entity.User;
import fudan.se.lab2.repository.impl.UserRepositoryImpl;
import fudan.se.lab2.service.AccountService;
import fudan.se.lab2.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.MessageFormat;
import java.lang.*;


public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private boolean status = false;

    @Override
    public boolean login(User user) {
        String username=user.getName();

        //用户名存在
        if (usernameCheck(username )) {

            //用户名与密码相匹配
            if (passwordCheck(user)) {
                logger.info(MessageFormat.format(InfoConstant.FINISH_LOGIN,user.getName()));
                status = true;
                return true;
            }

            //用户名与密码不匹配
            else{
                logger.info(InfoConstant.FALSE_PASSWORD);
                throw new RuntimeException();
            }
        }
        //用户名不存在
        else {

//            throw new RuntimeException(InfoConstant.USERNAME_DOESNOT_EXSIT + username);

            logger.info(MessageFormat.format(InfoConstant.USERNAME_DOESNOT_EXSIT , username));
            throw new RuntimeException();

        }
    }

    @Override
    public boolean signup(User user) {
        String username=user.getName();

        //用户名存在
        if (usernameCheck(username)) {

//            throw new RuntimeException(MessageFormat.format(InfoConstant.USERNAME_EXIST, user.getName()));
            logger.info(InfoConstant.FAILED_TO_SIGNUP);
            throw new RuntimeException();
        }

        //用户名不存在
        else{
            logger.info(MessageFormat.format(InfoConstant.SUCCESS_TO_SIGNUP,username));
            (new UserRepositoryImpl()).createUser(user);
            return true;
        }
    }

    @Override
    public boolean checkStatus() {
        //登陆成功
        if(status){
            logger.info(InfoConstant.SUCCESS_TO_LOGIN);
            return true;
        }
        //登录不成功
        else {
            logger.info(InfoConstant.FAILED_TO_LOGIN);
            return false;
        }
    }

    //检查用户名是否存在
    public boolean usernameCheck(String username){

        //用户名为空
        if(!isValid(username)) {
            logger.info(MessageFormat.format(InfoConstant.INVALID_USERNAME, username));
            throw new RuntimeException();
        }

        //用户名不为空
        if(FileUtil.exist(username,FileConstant.USER_CSV))
            return true;
        else
            return false;

    }

    //检查密码是否匹配
    public boolean passwordCheck(User user){
        String username=user.getName();
        String password=user.getPassword();

        //密码为空
        if(!isValid(password)) {
            logger.info(InfoConstant.INVALID_PASSWORD);
            throw new RuntimeException();
        }

        //密码不为空
        if(password.equals(FileUtil.readByName(username, FileConstant.USER_CSV)[1]))
            return true;
        else
            return false;
    }

    //检查字符是否合法
    private boolean isValid(String s) {

        //判断字符是否为空
        if ("".equals(s))
            return false;
        return true;
    }
}
