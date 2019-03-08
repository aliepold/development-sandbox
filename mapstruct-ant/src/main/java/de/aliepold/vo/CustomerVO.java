package de.aliepold.vo;

public class CustomerVO {

	private Integer id;
	
	private String _customerNumber;
	
	private DeveloperVO devVO;

	public DeveloperVO getDevVO() {
		return devVO;
	}

	public void setDevVO(DeveloperVO devVO) {
		this.devVO = devVO;
	}

	public CustomerVO() {
		super();
	}

	public CustomerVO(Integer id, String customerNumber, String fullName, String title) {
		this();
		this.id = id;
		this._customerNumber = customerNumber;
		this.fullName = fullName;
		this.title = title;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getCustomerNumber() {
		return _customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		_customerNumber = customerNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String fullName;
	
	private String title;
}
