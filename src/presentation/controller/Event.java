package presentation.controller;

public enum Event {
	//vehicle
	CREATE_VEHICLE, DROP_VEHICLE, UPDATE_VEHICLE, SHOW_VEHICLE, SHOWALL_VEHICLE, SHOWALL_ACTIVE_VEHICLE,

	//service
	CREATE_SERVCE, DROP_SERVICE, UPDATE_SERVICE, SHOW_SERVICE, SHOWALL_SERVICE, SHOW_SERVICE_BY_LEVEL,

	//rental
	CREATE_RENTAL, DROP_RENTAL, UPDATE_RENTAL, SHOW_RENTAL, SHOWALLRENTAL,

	//main office
	CREATE_MAIN_OFFICE, DROP_MAIN_OFFICE, UPDATE_MAIN_OFFICE, SHOW_MAIN_OFFICE, SHOWALL_MAIN_OFFICE, TOTAL_SALARY_MAIN_OFFICE,

	//employee
	CREATE_EMPLOYEE, DROP_EMPLOYEE, UPDATE_EMPLOYEE, SHOW_EMPLOYEE, SHOWALL_EMPLOYEE, SET_SALARY_EMPLOYEE,

	//contract
	CREATE_CONTRACT, DROP_CONTRACT, UPDATE_CONTRACT, SHOW_CONTRACT, SHOWALL_CONTRACT,

	//client
	CREATE_CLIENT, DROP_CLIENT, UPDATE_CLIENT, SHOW_CLIENT, SHOWALL_CLIENT, SHOW_CLIENTS_N_RENTAL_CLIENT,

	//city
	CREATE_CITY, DROP_CITY, UPDATE_CITY, SHOW_CITY, SHOWALL_CITY, SHOW_CLIENTS_FROM_CITY
}
