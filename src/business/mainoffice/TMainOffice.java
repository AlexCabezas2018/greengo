package business.mainoffice;

public class TMainOffice {

	private Integer id;
	private String city;
	private String address;
	private Boolean active;

	public TMainOffice(){}

	public TMainOffice(Integer id, String city, String address, Boolean active) {
		this.id = id;
		this.city = city;
		this.address = address;
		this.active = active;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAdress(String address) {
		this.address = address;
	}
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

    @Override
    public String toString(){
        String ret = "";
        Integer addressLength = address.length();
        if(addressLength <= 8) addressLength = 9;
        String regex = "%-" + addressLength + "s %" + addressLength + "s %n";
        ret += String.format(regex, "Id: ", id);
        ret += String.format(regex, "City: ", city);
        ret += String.format(regex, "Address: ", address);
        ret += String.format(regex, "Active: ", active);

        return ret;
    }
}
