package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tables")
public class Tables implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private int tableId;
    private int nbChair;
    private boolean isEmpty;
    private boolean isBooked;
	
    public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public int getNbChair() {
		return nbChair;
	}

	public void setNbChair(int nbChair) {
		this.nbChair = nbChair;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}    
}
