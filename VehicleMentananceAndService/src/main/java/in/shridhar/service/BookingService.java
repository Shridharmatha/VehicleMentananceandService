package in.shridhar.service;

import java.util.List;

import in.shridhar.entity.Booking;

public interface BookingService {
	
	Integer register(Booking entity);
	
	List<Booking> getallbooking();
	
	List<Booking> getBookingsByCustomerName(String customerName);
	
	int acceptbooking(int sid);
	
	int rejectbooking(int sid);
	
	int completebooking(int sid);

}
