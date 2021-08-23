/**
 * 
 */
package com.cg.aps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aps.entities.DeliveryEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.DeliveryService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Vishal Rana
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController // Combines @Controller & @ResponseBody (detects custom beans, marks business/presentation
				//  layer & converts return value into appropriate format [JSON/XML] based on request.
@RequestMapping("/delivery") // map a request url to a class(root-mapping)/method.
public class DeliveryController {
	@Autowired // Inject object dependencies implicitly (can be applied over setter, property, constructor)
	DeliveryService service;

	/**
	 * @param Delivery
	 * @return
	 * @throws DuplicateRecordException
	 */
	// Returns meaningful description of operations
	@ApiOperation(value = "Add delivery", response = DeliveryEntity.class, tags = "add-Delivery", httpMethod = "POST")
	@PostMapping("/addDelivery") // Shortcut of @RequestMapping(method = RequestMethod. POST) , used to add data
	public ResponseEntity<DeliveryEntity> addDelivery(@RequestBody DeliveryEntity delivery)
	// @RequestBody retrieves the request’s body, automatically convert it to Java Object & bind to method parameter
			throws DuplicateRecordException {
		DeliveryEntity addDelivery = service.add(delivery);
		return new ResponseEntity<DeliveryEntity>(addDelivery, HttpStatus.OK);
		// ResponseEntity is used to fully configure the HTTP response
	}

	/**
	 * @param delivery
	 * @return
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Update delivery", response = DeliveryEntity.class, tags = "update-Delivery", httpMethod = "PUT")
	@PutMapping("/updateDelivery/{id}")
	public ResponseEntity<DeliveryEntity> updateDeliveryEntity(@PathVariable("id") long id,
			@RequestBody DeliveryEntity delivery) throws RecordNotFoundException {
		delivery.setOwnerName(delivery.getOwnerName());
		delivery.setTime(delivery.getTime());
		delivery.setStatus(delivery.getStatus());
		DeliveryEntity updateDelivery = service.update(delivery);
		return new ResponseEntity<DeliveryEntity>(updateDelivery, HttpStatus.OK);
	}

	/**
	 * @param deliveryId
	 * @return
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Delete delivery details by Id", response = String.class, tags = "delete-Delivery", httpMethod = "DELETE")
	@DeleteMapping("/deleteDelivery/{id}")
	public ResponseEntity<String> deleteDeliveryTraining(@PathVariable("id") long id) throws RecordNotFoundException {
		service.delete(id);
		return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
	}

	/**
	 * @param onwerName
	 * @return
	 * @throws RecordNotFoundException
	 */

	@ApiOperation(value = "Get delivery using onwerName", response = DeliveryEntity.class, tags = "get-Delivery-Using-OnwerName", httpMethod = "GET")
	@GetMapping("/getName/{onwerName}")
	public ResponseEntity<List<DeliveryEntity>> getByOnwerName(@PathVariable("onwerName") String onwerName)

			throws RecordNotFoundException {
		List<DeliveryEntity> getOwnerName = service.findByOwnerName(onwerName);
		return new ResponseEntity<List<DeliveryEntity>>(getOwnerName, HttpStatus.OK);
	}

	/**
	 * @param deliveryId
	 * @return
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Get delivery details by deliveryId", response = DeliveryEntity.class, tags = "get-Delivery-Using-DeliveryId", httpMethod = "GET")
	@GetMapping("/getByPk/{deliveryId}")
	public ResponseEntity<DeliveryEntity> getByPk(@PathVariable("deliveryId") long deliveryId)
			throws RecordNotFoundException {
		DeliveryEntity getByDeliveryId = service.findByPk(deliveryId);
		return new ResponseEntity<DeliveryEntity>(getByDeliveryId, HttpStatus.OK);
	}

	/**
	 * @return
	 * @throws DatabaseException
	 */
	@ApiOperation(value = "Get all deliveries", response = DeliveryEntity.class, tags = "get-All-Deliveries", httpMethod = "GET")
	@GetMapping("/getAll")
	public ResponseEntity<List<DeliveryEntity>> searchDeliveries() throws DatabaseException {
		List<DeliveryEntity> getAllDeliveries = service.search();
		return new ResponseEntity<List<DeliveryEntity>>(getAllDeliveries, HttpStatus.OK);
	}
	// http://localhost:5050/swagger-ui.html
}
