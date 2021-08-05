package com.cg.ja18.onlinepizzaapp.repository;

//import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ja18.onlinepizzaapp.entity.User;



@Repository
public interface ILoginRepository extends JpaRepository<User, Long> {

	
	  User getByMobileAndPassword(Long mobile, String password);
	  
	 // public User findByUserNameIgnoreCase(String userName);
	  
	  //public User findByUserId(Integer userId);
	 
	
	
	/*
	 * @Query("select u from User u where user_id=?1 and password=?2") public User
	 * signIn(Integer userId, String password);
	 */

}
