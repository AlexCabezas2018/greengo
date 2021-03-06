package business.city.as.imp;

import business.ASException;
import business.IncorrectInputException;
import business.city.TCity;
import business.city.as.ASCity;
import business.client.TClient;
import business.vehicle.TVehicle;
import integration.DAOException;
import integration.transaction.Transaction;
import integration.TransactionException;
import integration.city.factory.DAOCityFactory;
import integration.transactionManager.TransactionManager;
import integration.vehicle.factory.DAOVehicleFactory;

import java.util.Collection;

public class ASCityImp implements ASCity {

    @Override
    public Integer create(TCity city) throws ASException, IncorrectInputException {
        Integer idc = null;

        if(!city.getName().equals("") && city.getId()==null) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                int a =0;
                if (tr != null) {
                    tr.start();
                    idc = DAOCityFactory.getInstance().generateDAOCity().create(city);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else
                    throw new ASException("ERROR: transaction creation failed\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else if (city.getId() != null)
            throw new IncorrectInputException("ERROR: Id it's generated by database,\n");
        else
            throw new ASException("ERROR: The data of city isn't insert correctly.\n");
        return idc;
    }

    @Override
    public Integer drop(Integer id) throws ASException, IncorrectInputException {
        Integer idc =null;

        if(id != null && id > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(id);
                    if (tc != null && tc.isActive()) {//the city exists and is active
                        Collection<TVehicle> vehiclesList = DAOVehicleFactory.getInstance().generateDAOVehicle().readVehiclesByCity(id);
                        for (TVehicle tv : vehiclesList) // If exists vehicles actives not update
                            if (tv.isActive()) {
                                throw  new ASException("ERROR: There are vehicles actived");
                            }
                            tc.setActive(false);
                            idc = DAOCityFactory.getInstance().generateDAOCity().update(tc);
                            tr.commit();
                            TransactionManager.getInstance().removeTransaction();

                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if (tc == null) throw new ASException("ERROR: The city doesn't exist");
                        else if (!tc.isActive()) throw new ASException("ERROR: The city is already disabled");
                    }
                } else
                    throw new ASException("ERROR: transaction drop failed\n");
            } catch (DAOException | ASException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id must be positive \n");

        return idc;
    }

    @Override
    public Integer update(TCity city) throws ASException, IncorrectInputException {
        Integer idc = null;

        if( city.getId()!= null && city.getId() > 0 && !city.getName().equals("")) {
            try {
             Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TCity tc = DAOCityFactory.getInstance().generateDAOCity().readById(city.getId());

                    if (tc != null && city.isActive()) {//the city exists
                        idc = DAOCityFactory.getInstance().generateDAOCity().update(city);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                    } else {
                        tr.rollback();
                        TransactionManager.getInstance().removeTransaction();
                        if ( tc == null )throw new ASException("ERROR: The city doesn't exist");
                        else throw new ASException("ERROR: The city active field is disabled, you must use Drop operation in order to disabled it");
                    }
                }
                else
                    throw new ASException("ERROR: transaction update failed\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }
        else if (city.getId() == null )
            throw new IncorrectInputException("ERROR: Id mustn't be empty \n");
        else
             throw new IncorrectInputException("ERROR: Id must be positive and name not empty \n");

        return idc;
    }

    @Override
    public TCity show(Integer id) throws ASException, IncorrectInputException {
        TCity city = null;

        if(id > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                        tr.start();
                        city = DAOCityFactory.getInstance().generateDAOCity().readById(id);
                        tr.commit();
                        TransactionManager.getInstance().removeTransaction();
                        if (city == null) throw new ASException("ERROR: The city doesn't exists");
                } else
                    throw new ASException("ERROR: transaction show failed\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id must be positive \n");

        return city;
    }

    @Override
    public Collection<TCity> showAll() throws ASException {
        Collection<TCity> cityList = null;
        try {
            Transaction tr = TransactionManager.getInstance().createTransaction();
            if(tr!=null) {
                tr.start();
                cityList = DAOCityFactory.getInstance().generateDAOCity().readAll();
                tr.commit();
                TransactionManager.getInstance().removeTransaction();
            }else
                throw new ASException("ERROR: transaction showALL failed\n");
        }catch (DAOException | TransactionException e) {
            throw new ASException(e.getMessage());
        }
        return cityList;
    }

    @Override
    public Collection<TClient> showClientsByCity(Integer idCity) throws ASException, IncorrectInputException {
        Collection<TClient> clientList = null;

        if(idCity!= null && idCity > 0) {
            try {
                Transaction tr = TransactionManager.getInstance().createTransaction();
                if (tr != null) {
                    tr.start();
                    TCity city = DAOCityFactory.getInstance().generateDAOCity().readById(idCity);
                    if(city==null) throw new ASException("ERROR: The city doesn`t exist");
                    clientList = DAOCityFactory.getInstance().generateDAOCity().readClientsByCity(idCity);
                    tr.commit();
                    TransactionManager.getInstance().removeTransaction();
                } else
                    throw new ASException("ERROR: transaction readClientsByCity failed\n");
            } catch (DAOException | TransactionException e) {
                throw new ASException(e.getMessage());
            }
        }else
            throw new IncorrectInputException("ERROR: Id must be positive \n");

        return clientList;
    }
}
