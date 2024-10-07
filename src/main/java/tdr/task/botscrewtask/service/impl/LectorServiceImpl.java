package tdr.task.botscrewtask.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdr.task.botscrewtask.model.dto.LectorDto;
import tdr.task.botscrewtask.model.mapper.LectorMapper;
import tdr.task.botscrewtask.repository.LectorRepository;
import tdr.task.botscrewtask.service.LectorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LectorServiceImpl implements LectorService {

    private final LectorRepository lectorRepository;
    private final LectorMapper lectorMapper;

    @Override
    public String globalSearchByValue(String value) {
        List<LectorDto> lectorDtoList = lectorRepository.globalSearchByValue(value).stream()
                .map(lectorMapper::toDto)
                .toList();
        if (lectorDtoList.isEmpty()) {
            return "No matching lectors found.";
        }
        return lectorDtoList.stream()
                .map(LectorDto::fullName)
                .collect(Collectors.joining(", "));
    }
}
