package br.com.housecode.store.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5004493416161670602L;
	
	private BigDecimal value;
	private BookType bookType;

	@Column(scale = 2)
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

}
