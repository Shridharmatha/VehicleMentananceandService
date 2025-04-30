package in.shridhar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shridhar.entity.Booking;
import in.shridhar.repository.InterfaceBooking;
import in.shridhar.service.BookingService;


@Service
public class BookingServiceImpl implements BookingService{
	
	
	@Autowired
	private InterfaceBooking book;

	@Override
	public Integer register(Booking entity) {
		
		return book.save(entity).getSid();
	}

	@Override
	public List<Booking> getallbooking() {
		
		return book.findAll();
	}

	@Override
	public int acceptbooking(int sid) {
		
		return book.updateaccept(sid);
	}

	@Override
	public int rejectbooking(int sid) {
		
		return book.updatereject(sid);
	}

	@Override
	public int completebooking(int sid) {
		
		return book.upcomplete(sid);
	}

	@Override
	public List<Booking> getBookingsByCustomerName(String customerName) {
		
		List<Booking> list=book.findByCustomerName(customerName);
		
		return list;
	}

}
