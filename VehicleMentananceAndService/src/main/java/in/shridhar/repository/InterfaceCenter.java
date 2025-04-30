package in.shridhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.shridhar.entity.Center;

public interface InterfaceCenter extends JpaRepository<Center, Integer>{
	
	Center findByCmail(String cmail);
	
	@Query(value = "SELECT cname FROM Center WHERE cid = :cid", nativeQuery = true)
	String findByCname(@Param("cid") int cid);


}
