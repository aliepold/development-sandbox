package de.aliepold.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import de.aliepold.jpa.entities.Customer;
import de.aliepold.vo.CustomerVO;

@Mapper
public interface CustomerMapper {
	
	/**
	 * Eine Instanz der Schnittstellenimplementierung kann aus der Mappers-Klasse abgerufen werden. 
	 * Per Konvention deklariert die Schnittstelle einen Member INSTANCE,
	 * der Clients Zugriff auf die Mapper-Implementierung bietet.
	 */
	CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

	// Mapping von id nicht exlizit angegeben, da automatisch durch gleiche Namen gemappt werden
	@Mapping(source = "customerId", target = "customerNumber")
	@Mapping(source = "name", target = "fullName")
	CustomerVO customer2VO(Customer customer);

}
