package ejbs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.*;
import dtos.*;
import ejbs.*;

/**
 * Session Bean implementation class TableManagerStateless
 */
@Stateless
@LocalBean
public class BillManagerStateless implements BillManagerStatelessRemote {

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public BillManagerStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public BillDto addBill(BillDto billDto) {
		Bill bill = new Bill();
		em.persist(bill);	
		em.flush();
		return billDto;
	}

	@Override
	public BillDto getBill(int billId) {
		Bill bill = em.find(Bill.class, billId);
		OrderDto orderDto = new OrderDto();
		orderDto.setOrderId(bill.getOrder().getOrderId());
		orderDto.setOrderAmount(bill.getOrder().getOrderAmount());
		StateDto stateDto = new StateDto();
		stateDto.setStateId(bill.getOrder().getOrderState().getStateId());
		stateDto.setStateName(bill.getOrder().getOrderState().getStateName());
		orderDto.setOrderState(stateDto);	
		List<DrinkDto> drinksDto = new ArrayList<DrinkDto>();
		for(Drink d : bill.getOrder().getDrinksOfOrder()) {
			DrinkDto drink = new DrinkDto();
			drink.setDrinkId(d.getDrinkId());
			drink.setDrinkName(d.getDrinkName());
			drink.setDrinkPrice(d.getDrinkPrice());
			drink.setDrinkQuantity(d.getDrinkQuantity());
			drinksDto.add(drink);
		}
		BillDto dto = new BillDto();
		dto.setBillId(bill.getBillId());
		dto.setBillAmount(bill.getBillAmount());
		dto.setOrder(orderDto);
		return dto;
	}

	@Override
	public Collection<BillDto> getBillsList() {
		return em.createQuery("SELECT b FROM Bills b").getResultList();
	}

	@Override
	public BillDto editBill(BillDto bill) {
		em.merge(bill);
		em.flush();
		return bill;		
	}

	@Override
	public BillDto getBillByOrderId(int orderId) {
		return null; //(Bill)em.createQuery("SELECT b FROM Bills b WHERE orderId=" + orderId + "").getResultList().get(0);
	}
}
