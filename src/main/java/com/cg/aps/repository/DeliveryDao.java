/**
 * 
 */
package com.cg.aps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entities.DeliveryEntity;

/**
 * @author Vishal Rana
 *
 */
@Repository // specializations of @Component, marks dao layer

//JpaRepository extends PagingAndSorting extends CrudRepository extends Repository
public interface DeliveryDao extends JpaRepository<DeliveryEntity, Long> { 
	/**
	 * @param name
	 * @return
	 */
	// Generate a query using method name strategy.
	List<DeliveryEntity> findByOwnerName(String name); 

	/**
	 * @param deliveryId
	 * @return
	 */
	// Generate a query using method name strategy.
	Optional<DeliveryEntity> findByDeliveryId(long deliveryId);
	/**
	 * @param id
	 * @return
	 */
	// Generate a query using method name strategy.
	DeliveryEntity deleteById(long id);
}
