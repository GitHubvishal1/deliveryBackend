/**
 * 
 */
package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aps.entities.DeliveryEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.DeliveryDao;

/**
 * @author Vishal Rana
 *
 */
@Service("DeliveryService") // specializations of @Component, marks service layer
@Transactional // Inject transactional logic for starting and committing the transaction using
				// proxies
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired // Inject object dependencies implicitly (can be applied over setter, property,
				// constructor)
	DeliveryDao dao;

	@Override
	public DeliveryEntity add(DeliveryEntity bean) throws DuplicateRecordException {
		Optional<DeliveryEntity> getDeliveryId = dao.findByDeliveryId(bean.getDeliveryId());
		if (getDeliveryId.isPresent()) {
			throw new DuplicateRecordException("The DeliveryId is already added");
		} else {
			return dao.save(bean);
		}
	}

	@Override
	public DeliveryEntity update(DeliveryEntity bean) throws RecordNotFoundException {
			return dao.save(bean);
	}

	@Override
	public DeliveryEntity delete(long id) throws RecordNotFoundException {
		Optional<DeliveryEntity> delivery = dao.findById(id);
		if (!delivery.isPresent()) {
			throw new RecordNotFoundException("Id Not Found");
		} else {
			return dao.deleteById(id);
		}
	}

	@Override
	public List<DeliveryEntity> findByOwnerName(String name) throws RecordNotFoundException {
		List<DeliveryEntity> delivery = dao.findByOwnerName(name);
		if (delivery.isEmpty()) {
			throw new RecordNotFoundException("Name not found");
		} else {
			return dao.findByOwnerName(name);
		}
	}

	@Override
	public DeliveryEntity findByPk(long deliveryId) throws RecordNotFoundException {
		Optional<DeliveryEntity> optional = dao.findByDeliveryId(deliveryId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new RecordNotFoundException("Invalid id");
		}
	}

	@Override
	public List<DeliveryEntity> search() throws DatabaseException {
		return dao.findAll();
	}
}
