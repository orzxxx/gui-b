package test.boot.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import test.boot.dto.RedisNodeDTO;
import test.boot.entity.tables.pojos.RedisNode;
import test.boot.entity.tables.records.RedisNodeRecord;

@Mapper(componentModel = "spring")
public interface RedisNodeMapper {
    
	RedisNodeRecord toEntity(RedisNodeDTO dto);
	
    List<RedisNodeDTO> toDTO(List<RedisNode> records);
    
    RedisNodeDTO toDTO(RedisNode record);
    
}