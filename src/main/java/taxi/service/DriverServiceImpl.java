package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.dao.DriverDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LogManager.getLogger(DriverServiceImpl.class);
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        logger.info("Create driver method was called. Driver: {}", driver);
        return driverDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        logger.info("Get driver method was called. Driver ID: {}", id);
        return driverDao.get(id).orElseThrow(() -> {
            logger.warn("Can't find driver by id: {}", id);
            return new NoSuchElementException("Can't get driver by id: " + id);
        });
    }

    @Override
    public List<Driver> getAll() {
        logger.info("GetAll drivers method was called.");
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        logger.info("Update driver method was called. Driver ID: {}", driver.getId());
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete driver method was called. Driver ID: {}", id);
        return driverDao.delete(id);
    }

    @Override
    public Optional<Driver> findByLogin(String username) {
        logger.info("FindByLogin method was called. Username: {}", username);
        return driverDao.findByLogin(username);
    }
}
