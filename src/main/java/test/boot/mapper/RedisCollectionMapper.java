package test.boot.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import test.boot.dto.RedisCollectionDTO;
import test.boot.entity.tables.pojos.RedisCollection;
import test.boot.entity.tables.records.RedisCollectionRecord;

@Mapper(componentModel = "spring")
public interface RedisCollectionMapper {

    RedisCollectionRecord toEntity(RedisCollectionDTO dto);
    
    List<RedisCollectionDTO> toDTO(List<RedisCollection> records);
    
    RedisCollectionDTO toDTO(RedisCollection record);
    
}