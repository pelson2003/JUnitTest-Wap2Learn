package br.com.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import br.com.bo.exception.BOException;
import br.com.dao.OrderDAO;
import br.com.dto.Order;

public class OrderBOImplTest {

	public int order_ID = 123;
	
	@Mock
	OrderDAO dao;
	private OrderBOImpl bo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
	}

	@Test
	public void placeOrder_Should_Create_An_Order() throws SQLException, BOException {

		Order order = new Order();
		// MOCKITO - Dizendo que quando o método for chamado (WHEN) 
		// Faremos uma ação (THENRETURN)
		when(dao.create(any(Order.class))).thenReturn(new Integer(1));
		boolean result = bo.placeOrder(order);
		
		//JUNIT - Validando se o resultado foi true, se a ordem foi chamada
			assertTrue(result);
		//MOCKITO - Validando se o método está sendo chamado
			verify(dao).create(order); 
			// verify(dao, times(2)).create(order);  
			// verify(dao, atLeast(2)).create(order);
	}
	
	// Outro exemplo de Matcher usado onde substituimos order por
	// any(Order.class)

	
	
	
	@Test
	public void placeOrder_Should_NOT_Create_An_Order() throws SQLException, BOException {

		Order order = new Order();
		// MOCKITO - Dizendo que quando o método for chamado (WHEN) 
		// Faremos uma ação (THENRETURN)
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);
		
		//JUNIT - Validando se o resultado foi true, se a ordem foi chamada
			assertFalse(result);
		//MOCKITO - Validando se o método está sendo chamado
			verify(dao).create(order); 
	}
	
	
	
	
	
	// Nesta anotação mostramos que o teste vai esperar uma Excessão
	
	@Test(expected = BOException.class)
	public void placeOrder_Should_Throw_BO_Exception() throws SQLException, BOException {

		Order order = new Order();
		
		// MOCKITO - Dizendo que quando o método for chamado (WHEN) 
		// Lançaremos uma ThrowException através de (THENTHROW)
		
		when(dao.create(order)).thenThrow(SQLException.class);
		
		// Chamamos o método.
		
		boolean result = bo.placeOrder(order);
		
	}
	
	
	
	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(order_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(123);
		assertTrue(result);
		verify(dao).read(123); 
		verify(dao).update(order);
	}
	
	@Test
	public void cancelOrder_Should_NOT_Cancel_The_Order() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(order_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(order_ID);
		assertFalse(result);
		verify(dao).read(order_ID); 
		verify(dao).update(order);
	}
	
	
	@Test(expected = BOException.class)
	public void cancelOrder_Should_ThrownaBOExceptiononRead() throws SQLException, BOException{
		when(dao.read(anyInt())).thenThrow(SQLException.class);
		bo.cancelOrder(order_ID);
	}	
	//Uso do matchers, onde transformar order_id por anyInt();
	
	
	
	
	@Test(expected = BOException.class)
	public void cancelOrder_Should_ThrownaBOExceptiononUpdate() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(order_ID)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(order_ID);
	}
	
	
	@Test
	public void deleteOrder_Deletes_tHEoRDER() throws SQLException, BOException {
		when(dao.delete(order_ID)).thenReturn(1);
		boolean result = bo.deleteOrder(order_ID);
		assertTrue(result);
		verify(dao).delete(order_ID); 
	}
	
	
	
	
	
}







