package jdbc_product_service;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jdbc_product.dao.SaleDao;

public interface MyDocumentListener extends DocumentListener{
	

	@Override
	default void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		msg();
	}

	@Override
	default void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		msg();
	}

	@Override
	default void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		msg();
	}

	public abstract void msg();
}
