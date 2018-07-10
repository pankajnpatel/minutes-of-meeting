package com.pnp.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pnp.model.UserMeeting;

public interface UserMeetingRepository extends JpaRepository<UserMeeting, Long>{
	
	List<UserMeeting> findByUserId(Long id);
	UserMeeting findByIdAndUserId(Long id , Long userId);
		
}
