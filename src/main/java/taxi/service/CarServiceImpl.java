package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.dao.CarDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Car;
import taxi.model.Driver;

@Service
public class CarServiceImpl implements CarService {
    private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);
    @Inject
    private CarDao carDao;

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        logger.info("AddDriverToCar method was called. Driver ID: {}, Car ID: {}",
                driver.getId(), car.getId());
        car.getDrivers().add(driver);
        carDao.update(car);
        logger.info("Successful add driver to car. Driver ID: {}, Car ID: {}",
                driver.getId(), car.getId());
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        logger.info("RemoveDriverFromCar method was called. Driver ID: {}, Car ID: {}",
                driver.getId(), car.getId());
        car.getDrivers().remove(driver);
        carDao.update(car);
        logger.info("Successful remove driver from car. Driver ID: {}, Car ID: {}",
                driver.getId(), car.getId());
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        logger.info("GetAllByDriver method was called. Driver ID: {}", driverId);
        return carDao.getAllByDriver(driverId);
    }

    @Override
    public Car create(Car car) {
        logger.info("Create car method was called. Car: {}", car);
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        logger.info("Get car method was called. Car ID: {}", id);
        return carDao.get(id).orElseThrow(() -> {
            logger.warn("Can't find car by id: {}", id);
            return new NoSuchElementException("Can't get car by id: " + id);
        });
    }

    @Override
    public List<Car> getAll() {
        logger.info("GetAllByDriver method was called.");
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        logger.info("Update car method was called. Car: {}", car);
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete car method was called. Car ID : {}", id);
        return carDao.delete(id);
    }
}
