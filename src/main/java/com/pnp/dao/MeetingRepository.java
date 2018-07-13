package com.pnp.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pnp.model.Meeting;
import com.pnp.model.User;

public interface MeetingRepository extends JpaRepository<Meeting, Serializable>{
	
	@Query(value = "Select u from Meeting m join m.userMeetingSet um inner join um.user u where m.id = :id")
	List<User> findUserFromMeeting(@Param("id") Long id);
	

	
}
