package com.repository;

import com.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{


}
