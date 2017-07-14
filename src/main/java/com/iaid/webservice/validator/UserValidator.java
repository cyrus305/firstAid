package com.iaid.webservice.validator;

import com.iaid.webservice.dao.UsersDao;
import com.iaid.webservice.dto.Users;
import com.iaid.webservice.exception.DoiValidationException;
import com.iaid.webservice.general.FirstAidChecker;
import com.iaid.webservice.general.Messages;
import org.eclipse.jetty.server.Authentication;

import java.util.List;

/**
 * Created by Crawlers on 4/26/2016.
 */
public class UserValidator {
  public static void validateUsers(UsersDao userDao, Users user) {
    Users newUsers = new Users();

    nullCheck(user);

    emptyCheck(user);

    //Check if email is already registered.
    newUsers.setEmail(user.getEmail());
    if (isEmailRegistered(userDao, newUsers)) {
      throw new DoiValidationException(Messages.EMAIL_ALREADY_REGISTERED.getMessage());
    }

        /*System.out.println("check for phone");
        //Check if phone is already registered.
        newUsers = new Users();
        newUsers.setPhone(user.getPhone());
        if(isUsersRegistered(userDao,newUsers)){
            throw new DoiValidationException(Messages.PHONE_ALREADY_REGISTERED.getMessage());
        }

        System.out.println("check for citizenship or passport");
        //Check if citizenship is already registered.
        newUsers = new Users();
        newUsers.setCitizenship_or_passport_no(user.getCitizenship_or_passport_no());
        if(isUsersRegistered(userDao,newUsers)){
            throw new DoiValidationException(Messages.CITIZENSHIP_ALREADY_REGISTERED.getMessage());
        }*/
  }

  /**
   * Check user is valid for update
   *
   * @param userDao
   * @param user
   */
  public static void userValidateForUpdate(UsersDao userDao, Users user) {
    nullCheck(user);
    if (!isUsersRegistered(userDao, user)) {
      throw new DoiValidationException(Messages.USER_IS_NOT_REGISTERED.getMessage());
    }
  }

  /**
   * Check if needed value of Users is null.
   *
   * @param user
   */
  private static void nullCheck(Users user) {
       /* if (FirstAidChecker.isNull(user.getRegistration_number(), user.getContact_person(), user.getEmail(), user.getPhone(), user.getGenderId(), user.getIntermediary(), user.getCitizenship_or_passport_no())) {
            throw new DoiValidationException(Messages.ONE_OR_MORE_FIELDS_ARE_NULL.getMessage());
        }*/
    if (FirstAidChecker.isNull(user.getEmail(), user.getPassword())) {
      throw new DoiValidationException(Messages.ONE_OR_MORE_FIELDS_ARE_NULL.getMessage());
    }
  }

  /**
   * Check if needed value of Users is empty.
   *
   * @param user
   */
  private static void emptyCheck(Users user) {
    /*    if (FirstAidChecker.isEmpty(user.getContact_person(), user.getEmail(), user.getGenderId(), user.getCitizenship_or_passport_no())) {
            throw new DoiValidationException(Messages.ONE_OR_MORE_FIELDS_ARE_EMPTY.getMessage());
        }*/
    if (FirstAidChecker.isEmpty(user.getEmail(), user.getPassword())) {
      throw new DoiValidationException(Messages.ONE_OR_MORE_FIELDS_ARE_NULL.getMessage());
    }
  }

  /**
   * Checks if the user is already registered.
   *
   * @param userDao
   * @param user    to be checked
   * @return
   */
  public static boolean isUsersRegistered(UsersDao userDao, Users user) {
    //write query later
    List<Users> users = userDao.getUser("");
    System.out.println("Size of data:" + users);
    if (userDao.getUser("").size() > 0) {
      return true;
    }
    return false;
  }

  /**
   * Checks if the user email is already registered.
   *
   * @param userDao
   * @param user    to be checked
   * @return
   */
  public static boolean isEmailRegistered(UsersDao userDao, Users user) {
    //Custom Query later
    List<Users> users = userDao.getUser("");
    System.out.println("Size of data:" + users);
    if (userDao.getUser("").size() > 0) {
      return true;
    }
    return false;
  }
}
