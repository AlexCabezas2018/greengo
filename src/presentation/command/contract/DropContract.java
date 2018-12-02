package presentation.command.contract;

import business.ASException;
import business.IncorrectInputException;
import business.contract.TContract;
import business.contract.factory.ASContractFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropContract implements Command {
	@Override
	public LightContext execute(LightContext in)  throws ASException, IncorrectInputException {
		Integer ret = ASContractFactory.getInstance().generateASContract().drop(((TContract)in.getData()).getId());
		return new LightContext(Event.DROP_CONTRACT, ret);
	}
}
