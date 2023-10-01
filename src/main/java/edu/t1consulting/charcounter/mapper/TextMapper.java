package edu.t1consulting.charcounter.mapper;

import edu.t1consulting.charcounter.dto.TextDto;
import edu.t1consulting.charcounter.model.Text;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TextMapper {

    Text textDtoToText(TextDto textDto);
}
