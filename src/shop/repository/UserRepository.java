package shop.repository;

import myboard.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: bungubbang
 * Date: 13. 3. 7.
 * Time: 오후 7:53
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository {

    User findById(String id);

    void addUser(User user);
}
