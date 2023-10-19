package ro.mihai.position;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PositionMapper {

  PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

  PositionDTO mapPositionEtyToPositionDto(Position position);
}
