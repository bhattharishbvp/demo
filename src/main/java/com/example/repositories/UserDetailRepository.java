package com.example.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.UserDetail;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Integer> {

	public List<UserDetail> findByAddressContaining(final String address);
}
