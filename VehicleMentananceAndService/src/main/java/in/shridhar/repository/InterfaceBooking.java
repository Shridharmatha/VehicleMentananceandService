package in.shridhar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.shridhar.entity.Booking;
import jakarta.transaction.Transactional;

public interface InterfaceBooking extends JpaRepository<Booking, Integer>{
	
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE Booking SET status='Accepted' WHERE sid = :sid", nativeQuery = true)
	int updateaccept(@Param("sid") int sid);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE Booking SET status='Rejected' WHERE sid = :sid", nativeQuery = true)
	int updatereject(@Param("sid") int sid);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE Booking SET status='Completed' WHERE sid = :sid", nativeQuery = true)
	int upcomplete(@Param("sid") int sid);
	
	List<Booking> findByCustomerName(String customerName);
	
	Booking findById(int id);

}
