package presentation.command.city;

import business.ASException;
import business.IncorrectInputException;
import business.city.TCity;
import business.city.factory.ASCityFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateCity implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Integer ret = ASCityFactory.getInstance().generateASCity().update(((TCity)in.getData()));
		return new LightContext(Event.UPDATE_CITY, ret);
	}
}
