package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.dao.ManufacturerDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private static final Logger logger = LogManager.getLogger(ManufacturerServiceImpl.class);
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        logger.info("Create manufacturer method was called. Manufacturer: {}", manufacturer);
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        logger.info("Get manufacturer method was called. Manufacturer ID: {}", id);
        return manufacturerDao.get(id).orElseThrow(() -> {
            logger.warn("Can't find manufacturer by id: {}", id);
            return new NoSuchElementException("Can't get manufacturer by id: " + id);
        });
    }

    @Override
    public List<Manufacturer> getAll() {
        logger.info("GetAll manufacturer method was called");
        return manufacturerDao.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        logger.info("Update manufacturer method was called. Manufacturer ID: {}",
                manufacturer.getId());
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete manufacturer method was called. Manufacturer ID: {}", id);
        return manufacturerDao.delete(id);
    }
}
