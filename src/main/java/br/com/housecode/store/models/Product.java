package br.com.housecode.store.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String description;
	private int pages;
	private List<Price> prices = new ArrayList<>();
	private Calendar releaseDate;
	private String summary;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Min(30)
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@ElementCollection
	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	@DateTimeFormat(iso = ISO.DATE)
	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSummaryPath() {
		return summary;
	}

	public void setSummaryPath(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		sb.append("Title=").append(getTitle()).append("; ").append("Description=").append(getDescription()).append("; ")
				.append("Pages=").append(getPages()).append("; ").append("ReleaseDate=").append(getReleaseDate())
				.append("]");
		return sb.toString();
	}

	public BigDecimal priceFor(BookType bookType) {
		return prices.stream().filter(price -> price.getBookType().equals(bookType)).findFirst().get().getValue();
	}
}
