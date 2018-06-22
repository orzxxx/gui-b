package test.boot.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import test.boot.dto.RedisConnectionDTO;
import test.boot.entity.tables.pojos.RedisConnection;
import test.boot.entity.tables.records.RedisConnectionRecord;

@Mapper(componentModel = "spring")
public interface RedisConnectionMapper {
    
	RedisConnectionRecord toEntity(RedisConnectionDTO dto);
	
    List<RedisConnectionDTO> toDTO(List<RedisConnection> records);
    
    RedisConnectionDTO toDTO(RedisConnection record);
    
}